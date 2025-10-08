package com.example.airsec.repo;

import android.content.Context;

import androidx.annotation.NonNull;

import com.example.airsec.dao.*;
import com.example.airsec.database.AppDb;
import com.example.airsec.model.*;
import com.example.airsec.util.AppExecutors;

import java.util.List;
import java.util.concurrent.Executor;

/**
 * Repositorio único para Vuelos, Tiempos, Accesos, Demoras y Operadores.
 * - Todas las operaciones se ejecutan en hilo de I/O (no bloquea UI).
 * - Usa callbacks simples para devolver resultados o estado.
 */
public class DataRepository {

    // --- Callbacks básicos ---
    public interface Callback<T> {
        void onResult(T data);
        default void onError(Throwable t) { t.printStackTrace(); }
    }
    public interface SimpleCallback {
        void onComplete();
        default void onError(Throwable t) { t.printStackTrace(); }
    }

    private static volatile DataRepository INSTANCE;

    private final AppDb db;
    private final VueloDao vueloDao;
    private final TiemposOperativosDao tiemposDao;
    private final AccesoDao accesoDao;
    private final DemoraDao demoraDao;
    private final OperadorDao operadorDao;
    private final Executor io;

    private DataRepository(Context ctx) {
        this.db = AppDb.get(ctx.getApplicationContext());
        this.vueloDao = db.vueloDao();
        this.tiemposDao = db.tiemposOperativosDao();
        this.accesoDao = db.accesoDao();
        this.demoraDao = db.demoraDao();
        this.operadorDao = db.operadorDao();
        this.io = AppExecutors.get().io();
    }

    public static DataRepository get(Context ctx) {
        if (INSTANCE == null) {
            synchronized (DataRepository.class) {
                if (INSTANCE == null) INSTANCE = new DataRepository(ctx);
            }
        }
        return INSTANCE;
    }

    // =========================================================
    //                         VUELOS
    // =========================================================

    public void listarVuelos(@NonNull Callback<List<Vuelo>> cb) {
        io.execute(() -> {
            try { cb.onResult(vueloDao.list()); }
            catch (Throwable t) { cb.onError(t); }
        });
    }

    public void obtenerVuelo(long id, @NonNull Callback<Vuelo> cb) {
        io.execute(() -> {
            try { cb.onResult(vueloDao.get(id)); }
            catch (Throwable t) { cb.onError(t); }
        });
    }

    public void crearVuelo(@NonNull Vuelo v, @NonNull Callback<Long> cb) {
        io.execute(() -> {
            try {
                long id = vueloDao.insert(v);
                cb.onResult(id);
            } catch (Throwable t) { cb.onError(t); }
        });
    }

    public void actualizarVuelo(@NonNull Vuelo v, @NonNull SimpleCallback cb) {
        io.execute(() -> {
            try {
                vueloDao.update(v);
                cb.onComplete();
            } catch (Throwable t) { cb.onError(t); }
        });
    }

    // =========================================================
    //                  TIEMPOS OPERATIVOS (1:1)
    // =========================================================

    public void obtenerTiempos(long vueloId, @NonNull Callback<TiemposOperativos> cb) {
        io.execute(() -> {
            try { cb.onResult(tiemposDao.byVuelo(vueloId)); }
            catch (Throwable t) { cb.onError(t); }
        });
    }

    /** Upsert: crea o reemplaza la fila de tiempos del vuelo. */
    public void guardarTiempos(@NonNull TiemposOperativos t, @NonNull Callback<Long> cb) {
        io.execute(() -> {
            try {
                long id = tiemposDao.upsert(t);
                cb.onResult(id);
            } catch (Throwable th) { cb.onError(th); }
        });
    }

    // =========================================================
    //                          ACCESOS
    // =========================================================

    public void listarAccesos(long vueloId, @NonNull Callback<List<Acceso>> cb) {
        io.execute(() -> {
            try { cb.onResult(accesoDao.byVuelo(vueloId)); }
            catch (Throwable t) { cb.onError(t); }
        });
    }

    public void crearAcceso(@NonNull Acceso a, @NonNull Callback<Long> cb) {
        io.execute(() -> {
            try {
                long id = accesoDao.insert(a);
                cb.onResult(id);
            } catch (Throwable t) { cb.onError(t); }
        });
    }

    public void actualizarAcceso(@NonNull Acceso a, @NonNull SimpleCallback cb) {
        io.execute(() -> {
            try { accesoDao.update(a); cb.onComplete(); }
            catch (Throwable t) { cb.onError(t); }
        });
    }

    public void eliminarAcceso(@NonNull Acceso a, @NonNull SimpleCallback cb) {
        io.execute(() -> {
            try { accesoDao.delete(a); cb.onComplete(); }
            catch (Throwable t) { cb.onError(t); }
        });
    }

    // =========================================================
    //                          DEMORAS
    // =========================================================

    public void obtenerDemora(long vueloId, @NonNull Callback<Demora> cb) {
        io.execute(() -> {
            try { cb.onResult(demoraDao.byVuelo(vueloId)); }
            catch (Throwable t) { cb.onError(t); }
        });
    }

    public void guardarDemora(@NonNull Demora d, @NonNull Callback<Long> cb) {
        io.execute(() -> {
            try { cb.onResult(demoraDao.upsert(d)); }
            catch (Throwable t) { cb.onError(t); }
        });
    }

    public void eliminarDemoraPorVuelo(long vueloId, @NonNull SimpleCallback cb) {
        io.execute(() -> {
            try { demoraDao.deleteByVuelo(vueloId); cb.onComplete(); }
            catch (Throwable t) { cb.onError(t); }
        });
    }

    // =========================================================
    //                        OPERADORES
    // =========================================================

    public void listarOperadores(@NonNull Callback<List<Operador>> cb) {
        io.execute(() -> {
            try { cb.onResult(operadorDao.all()); }
            catch (Throwable t) { cb.onError(t); }
        });
    }

    public void upsertOperador(@NonNull Operador o, @NonNull Callback<Long> cb) {
        io.execute(() -> {
            try { cb.onResult(operadorDao.upsert(o)); }
            catch (Throwable t) { cb.onError(t); }
        });
    }
}
