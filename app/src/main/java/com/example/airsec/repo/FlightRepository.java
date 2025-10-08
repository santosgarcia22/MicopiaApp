package com.example.airsec.repo;

import android.content.Context;

import androidx.annotation.Nullable;

import com.example.airsec.dao.AccesoDao;
import com.example.airsec.dao.DemoraDao;
import com.example.airsec.dao.OperadorDao;
import com.example.airsec.dao.TiemposOperativosDao;
import com.example.airsec.dao.VueloDao;
import com.example.airsec.database.AppDb;
import com.example.airsec.model.Acceso;
import com.example.airsec.model.Demora;
import com.example.airsec.model.TiemposOperativos;
import com.example.airsec.model.Vuelo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class FlightRepository {

    private final VueloDao vueloDao;
    private final TiemposOperativosDao tiemposDao;
    private final AccesoDao accesoDao;
    private final DemoraDao demoraDao;
    private final OperadorDao operadorDao;

    private final Context appContext;


    public FlightRepository(Context ctx) {
        this.appContext = ctx.getApplicationContext();
        AppDb db = AppDb.get(ctx);
        vueloDao   = db.vueloDao();
        tiemposDao = db.tiemposOperativosDao();
        accesoDao  = db.accesoDao();
        demoraDao  = db.demoraDao();
        operadorDao= db.operadorDao();
    }

    private static String nowISO() {
        return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault()).format(new Date());
    }

    private static String todayISO() {
        return new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
    }

    // --------- VUELOS ---------

    /** Crea un vuelo mínimo y devuelve su id. */
    public long crearVueloInicial(@Nullable Long createdByUserId) {
        Vuelo v = new Vuelo();
        v.fecha = todayISO();
        v.appBloqueado = false;
        v.appCerrado = false;
        v.createdByUserId = createdByUserId;
        v.createdAt = nowISO();
        v.updatedAt = v.createdAt;
        return vueloDao.insert(v);
    }

    public Vuelo obtenerVuelo(long id) { return vueloDao.get(id); }

    public List<Vuelo> listarVuelos() { return vueloDao.list(); }

    public List<Vuelo> buscarVuelos(String q) { return vueloDao.search("%"+q+"%"); }

    public void actualizarVuelo(Vuelo v) {
        v.updatedAt = nowISO();
        vueloDao.update(v);
    }

    public void bloquearVuelo(long id) {
        Vuelo v = vueloDao.get(id);
        if (v != null) {
            v.appBloqueado = true;
            v.updatedAt = nowISO();
            vueloDao.update(v);
        }
    }

    public void cerrarVuelo(long id) {
        vueloDao.cerrarVuelo(id, nowISO());
    }

    // --------- TIEMPOS ---------

    public TiemposOperativos obtenerTiempos(long vueloId) {
        return tiemposDao.byVuelo(vueloId);
    }

    /** Crea o actualiza el registro de tiempos de un vuelo. */
    public long guardarTiempos(TiemposOperativos t) {
        if (t.id == 0) return tiemposDao.insert(t);
        return tiemposDao.upsert(t);
    }

    // --------- ACCESOS ---------

    public List<Acceso> listarAccesos(long vueloId) { return accesoDao.byVuelo(vueloId); }

    public long registrarEntrada(long vueloId, String nombre, String doc, String empresa,
                                 Byte herramientas, String motivo) {
        Acceso a = new Acceso();
        a.vueloId = vueloId;
        a.nombre = nombre;
        a.identificacion = doc;
        a.empresa = empresa;
        a.herramientas = herramientas;
        a.motivoEntrada = motivo;
        a.horaEntrada = nowISO();
        a.createdAt = nowISO();
        a.updatedAt = a.createdAt;
        return accesoDao.insert(a);
    }


    // --------- DEMORA (única por vuelo) ---------

    public Demora obtenerDemora(long vueloId) { return demoraDao.byVuelo(vueloId); }

    public long guardarDemora(long vueloId, String motivo, Integer minutos, @Nullable Long agenteId) {
        Demora d = demoraDao.byVuelo(vueloId);
        if (d == null) {
            d = new Demora();
            d.vueloId = vueloId;
        }
        d.motivo = motivo;
        d.minutos = minutos;
        d.agenteId = agenteId;
        d.updatedAt = nowISO();
        if (d.id == 0) {
            d.createdAt = d.updatedAt;
        }
        return demoraDao.upsert(d);
    }

    /** Elimina la demora del vuelo (si existe) */
    public void eliminarDemora(long vueloId) {
         demoraDao.deleteByVuelo(vueloId);
    }


    /** Marca la siguiente "entrada" disponible: entrada -> entrada1 */
    public void registrarEntradaPorDoc(long vueloId, String doc) {
        Acceso a = AppDb.get(appContext).accesoDao().byVueloAndDoc(vueloId, doc);
        String now = nowISO();
        if (a == null) {
            // Si no existe, creamos el registro mínimo con la primera entrada
            a = new Acceso();
            a.vueloId = vueloId;
            a.identificacion = doc;
            a.horaEntrada = now;
            AppDb.get(appContext).accesoDao().insert(a);
            return;
        }
        if (a.horaEntrada == null) {
            a.horaEntrada = now;
            AppDb.get(appContext).accesoDao().update(a);
        } else if (a.horaSalida != null && a.horaEntrada1 == null) {
            a.horaEntrada1 = now;
            AppDb.get(appContext).accesoDao().update(a);
        }
        // si ya tiene entrada1 y salida2 no nulos, no hace nada (completado)
    }

    // Marca la siguiente "salida" disponible: salida -> salida2
    public void registrarSalidaPorDoc(long vueloId, String doc) {
        Acceso a = AppDb.get(appContext).accesoDao().byVueloAndDoc(vueloId, doc);
        if (a == null) return;
        String now = nowISO();
        if (a.horaEntrada != null && a.horaSalida == null) {
            a.horaSalida = now;
            AppDb.get(appContext).accesoDao().update(a);
        } else if (a.horaEntrada1 != null && a.horaSalida2 == null) {
            a.horaSalida2 = now;
            AppDb.get(appContext).accesoDao().update(a);
        }
    }

    /** Crea (o actualiza) el registro de acceso para el doc y marca la primera entrada. */
    public void crearOActualizarAccesoYPrimeraEntrada(
            long vueloId,
            String nombre,
            String doc,
            String empresa,
            byte herramientas,
            String motivo
    ) {
        // Busca por vuelo + documento
        com.example.airsec.model.Acceso a = accesoDao.byVueloAndDoc(vueloId, doc);

        String now = nowISO();
        if (a == null) {
            // Crear registro mínimo y marcar E1
            a = new com.example.airsec.model.Acceso();
            a.vueloId        = vueloId;
            a.nombre         = nombre;
            a.identificacion = doc;      // <-- si tu campo se llama distinto (idDocumento), cámbialo aquí
            a.empresa        = empresa;
            a.herramientas   = herramientas;
            a.motivoEntrada  = motivo;
            a.horaEntrada    = now;      // primera entrada
            a.createdAt      = now;
            a.updatedAt      = now;
            accesoDao.insert(a);
        } else {
            // Ya existe: si no tiene E1, márcala; si ya tiene E1 y S1, abre E2
            if (a.horaEntrada == null) {
                a.horaEntrada = now;
            } else if (a.horaSalida != null && a.horaEntrada1 == null) {
                a.horaEntrada1 = now; // segunda entrada
            }
            a.updatedAt = now;
            accesoDao.update(a);
        }
    }



}
