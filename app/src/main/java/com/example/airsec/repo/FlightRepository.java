package com.example.airsec.repo;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

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
import com.example.airsec.network.ApiResponseSingle;
import com.example.airsec.network.ApiService;
import com.example.airsec.network.ApiClient;
import com.example.airsec.network.ApiResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import org.json.JSONObject;
import java.util.List;
import retrofit2.Call;
import com.example.airsec.network.ApiResponseList;
import com.example.airsec.network.DemoraRequest;
import retrofit2.Callback;
import retrofit2.Response;
import android.view.View;
import android.widget.Toast;

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

    public Vuelo obtenerVuelo(long id) {
        // 1) intento traer desde API (bloqueante -> OK porque lo llamás desde background thread)
        try {
            ApiService api = ApiClient.getClient().create(ApiService.class);
            retrofit2.Response<ApiResponseSingle<Vuelo>> resp = api.getVueloById(id).execute();

            if (resp.isSuccessful() && resp.body() != null && resp.body().data != null) {
                Vuelo remoto = resp.body().data;
                // guarda local para uso offline (si tu DAO inserta/actualiza)
                try {
                    vueloDao.insert(remoto);
                } catch (Exception ignored) { /* si da conflicto, lo ignoramos */ }
                return remoto;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 2) fallback: si la API falla o no hay datos -> devuelve la copia local
        return vueloDao.get(id);
    }

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

    private static String nowTimeHHmm() {
        return new java.text.SimpleDateFormat("HH:mm", java.util.Locale.getDefault())
                .format(new java.util.Date());
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
        com.example.airsec.model.Acceso a = accesoDao.byVueloAndDoc(vueloId, doc);
        String now = nowISO();

        if (a == null) {
            a = new com.example.airsec.model.Acceso();
            a.vueloId = vueloId;
            a.nombre = nombre;
            a.identificacion = doc;
            a.empresa = empresa;
            a.herramientas = herramientas;
            a.motivoEntrada = motivo;
            a.horaEntrada = now;
            a.createdAt = now;
            a.updatedAt = now;
            accesoDao.insert(a);
        } else {
            if (a.horaEntrada == null) {
                a.horaEntrada = now;
            } else if (a.horaSalida != null && a.horaEntrada1 == null) {
                a.horaEntrada1 = now;
            }
            a.updatedAt = now;
            accesoDao.update(a);
        }

        final Acceso accesoFinal = a;

        // --- Enviar al servidor PHP (en background)
        new Thread(() -> {
            try {
                String BASE_URL = "http://10.0.2.2:8000/api/v1/";
                java.net.URL url = new java.net.URL(BASE_URL + "vuelos/" + vueloId + "/accesos");
                java.net.HttpURLConnection conn = (java.net.HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
                conn.setDoOutput(true);
                conn.setConnectTimeout(10000);
                conn.setReadTimeout(10000);

                // helper para sacar HH:mm de varios formatos posibles
                java.util.function.Function<String, String> timeHHmm = s -> {
                    if (s == null || s.trim().isEmpty()) return null;
                    try {
                        // si viene como yyyy-MM-dd'T'HH:mm:ss
                        if (s.contains("T")) {
                            String part = s.split("T")[1];
                            if (part.length() >= 5) return part.substring(0,5);
                            return part;
                        }
                        // si viene como yyyy-MM-dd HH:mm:ss
                        if (s.contains(" ")) {
                            String part = s.split(" ")[1];
                            if (part.length() >= 5) return part.substring(0,5);
                            return part;
                        }
                        // si viene como HH:mm:ss
                        if (s.length() >= 5 && s.contains(":")) return s.substring(0,5);
                        // si ya es HH:mm
                        return s;
                    } catch (Exception ex) { return null; }
                };

                org.json.JSONObject json = new org.json.JSONObject();
                json.put("nombre", accesoFinal.nombre == null ? "" : accesoFinal.nombre);
                json.put("identificacion", accesoFinal.identificacion == null ? JSONObject.NULL : accesoFinal.identificacion);
                json.put("empresa", accesoFinal.empresa == null ? JSONObject.NULL : accesoFinal.empresa);
                // herramientas: asegurar integer 0/1
                int herramientasVal = 0;
                try { herramientasVal = accesoFinal.herramientas; } catch (Exception ignored) {}
                json.put("herramientas", herramientasVal);

                // nombre de campo esperado por tu API: motivo_entrada
                json.put("motivo_entrada", accesoFinal.motivoEntrada == null ? JSONObject.NULL : accesoFinal.motivoEntrada);

                // horas en formato HH:mm (o null)
                String he = timeHHmm.apply(accesoFinal.horaEntrada);


                if (he != null) json.put("hora_entrada", he);
                else json.put("hora_entrada", JSONObject.NULL);

                // log para depurar antes de enviar
                android.util.Log.d("API_ACCESS_JSON", json.toString());

                java.io.OutputStream os = conn.getOutputStream();
                os.write(json.toString().getBytes("UTF-8"));
                os.close();

                int code = conn.getResponseCode();

                // leer respuesta (útil para depurar errores 500)
                java.io.InputStream is = (code < java.net.HttpURLConnection.HTTP_BAD_REQUEST) ? conn.getInputStream() : conn.getErrorStream();
                java.io.BufferedReader br = new java.io.BufferedReader(new java.io.InputStreamReader(is));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) response.append(line);
                br.close();

                if (code == java.net.HttpURLConnection.HTTP_CREATED || code == java.net.HttpURLConnection.HTTP_OK) {
                    android.util.Log.i("API_ACCESS", "✅ Acceso enviado correctamente al servidor: " + response);
                } else {
                    android.util.Log.e("API_ACCESS", "⚠️ Error al enviar acceso al servidor. Código HTTP: " + code + " -> " + response);
                    // opcional: mostrar toast en la UI
                    android.os.Handler h = new android.os.Handler(appContext.getMainLooper());
                    h.post(() -> Toast.makeText(appContext, "Error al enviar acceso (servidor) " + code, Toast.LENGTH_SHORT).show());
                }

                conn.disconnect();

            } catch (Exception e) {
                e.printStackTrace();
                android.os.Handler handler = new android.os.Handler(appContext.getMainLooper());
                handler.post(() -> Toast.makeText(appContext, "⚠️ No se pudo enviar el acceso al servidor", Toast.LENGTH_SHORT).show());
            }
        }).start();

    }

    public void actualizarTiemposAccesoEnServidor(long vueloId, Acceso acceso) {
        new Thread(() -> {
            try {
                String BASE_URL = "http://10.0.2.2:8000/api/v1/";
                java.net.URL url = new java.net.URL(BASE_URL + "vuelos/" + vueloId + "/accesos/" + acceso.id);
                java.net.HttpURLConnection conn = (java.net.HttpURLConnection) url.openConnection();
                conn.setRequestMethod("PUT");
                conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
                conn.setDoOutput(true);

                JSONObject json = new JSONObject();
                json.put("hora_salida", extraerHora(acceso.horaSalida));
                json.put("hora_entrada1", extraerHora(acceso.horaEntrada1));
                json.put("hora_salida2", extraerHora(acceso.horaSalida2));

                Log.d("API_ACCESS", "📤 JSON a enviar: " + json.toString());

                java.io.OutputStream os = conn.getOutputStream();
                os.write(json.toString().getBytes("UTF-8"));
                os.close();

                int code = conn.getResponseCode();
                Log.d("API_ACCESS", "📡 Código HTTP: " + code);

                java.io.InputStream is = (code < 400)
                        ? conn.getInputStream()
                        : conn.getErrorStream();

                String response = new java.util.Scanner(is).useDelimiter("\\A").next();
                Log.d("API_ACCESS", "📥 Respuesta del servidor: " + response);

                conn.disconnect();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }



    public Acceso obtenerAccesoPorDoc(long vueloId, String doc) {
        return AppDb.get(appContext).accesoDao().byVueloAndDoc(vueloId, doc);
    }

    private String extraerHora(String valor) {
        if (valor == null || valor.isEmpty()) return null;

        // Si viene como "2025-10-20T19:44:51"
        if (valor.contains("T")) {
            try {
                return valor.split("T")[1].substring(0, 5); // "19:44"
            } catch (Exception e) {
                return valor;
            }
        }

        // Si ya viene en formato "19:44" lo deja igual
        if (valor.matches("\\d{2}:\\d{2}")) {
            return valor;
        }

        return valor;
    }


    //   CALLBACKS SIMPLES PARA LA DEMORA

    public interface OnOk { void run(); }
    public interface OnError { void onError(String msg); }


    //  PARA EL REGISTRO DE DEMORA LOCAL PRIMERO + SYNC

    /** Guarda local y sincroniza con servidor (POST o PUT según exista). */
    public void guardarDemoraYEnviar(long vueloId,
                                     String motivo,
                                     int minutos,
                                     @Nullable Long agenteId,
                                     OnOk onOk,
                                     @Nullable OnError onError) {

        // 1) Guardar local (no perder datos si falla red)
        new Thread(() -> {
            try {
                guardarDemora(vueloId, motivo, minutos, agenteId);
            } catch (Exception e) {
                if (onError != null) onError.onError("Error guardando local: " + e.getMessage());
                return;
            }

            // 2) Consultar servidor para decidir POST/PUT
            ApiService api = ApiClient.getClient().create(ApiService.class);
            api.listarDemoras(vueloId).enqueue(new Callback<ApiResponseList<Demora>>() {
                @Override
                public void onResponse(Call<ApiResponseList<Demora>> call,
                                       Response<ApiResponseList<Demora>> respList) {
                    boolean existeRemota = false;
                    long remotaId = 0;

                    if (respList.isSuccessful() && respList.body() != null && respList.body().ok) {
                        // Tu wrapper: body().data  -> DataWrapper<Demora>
                        // Lista real: body().data.data -> List<Demora>
                        java.util.List<Demora> lista = (respList.body().data != null) ? respList.body().data.data : null;
                        if (lista != null && !lista.isEmpty()) {
                            existeRemota = true;
                            remotaId = lista.get(0).id; // 1 demora por vuelo
                        }
                    }

                    DemoraRequest body = new DemoraRequest(motivo, minutos, agenteId);

                    if (!existeRemota) {
                        // POST crear
                        api.crearDemora(vueloId, body).enqueue(new Callback<ApiResponseSingle<Demora>>() {
                            @Override
                            public void onResponse(Call<ApiResponseSingle<Demora>> call,
                                                   Response<ApiResponseSingle<Demora>> resp) {
                                if (!resp.isSuccessful() || resp.body() == null || !resp.body().ok) {
                                    String msg = "HTTP " + resp.code();
                                    try { if (resp.errorBody()!=null) msg += " - " + resp.errorBody().string(); } catch (Exception ignored) {}
                                    if (onError != null) onError.onError("No se pudo crear en servidor: " + msg);
                                    return;
                                }
                                Demora dSrv = resp.body().data;
                                new Thread(() -> {
                                    demoraDao.upsert(dSrv);
                                    if (onOk != null) onOk.run();
                                }).start();
                            }

                            @Override
                            public void onFailure(Call<ApiResponseSingle<Demora>> call, Throwable t) {
                                if (onError != null) onError.onError("Error de red (crear): " + t.getMessage());
                            }
                        });

                    } else {
                        // PUT actualizar
                        api.actualizarDemora(vueloId, remotaId, body).enqueue(new Callback<ApiResponseSingle<Demora>>() {
                            @Override
                            public void onResponse(Call<ApiResponseSingle<Demora>> call,
                                                   Response<ApiResponseSingle<Demora>> resp) {
                                if (!resp.isSuccessful() || resp.body() == null || !resp.body().ok) {
                                    String msg = "HTTP " + resp.code();
                                    try { if (resp.errorBody()!=null) msg += " - " + resp.errorBody().string(); } catch (Exception ignored) {}
                                    if (onError != null) onError.onError("No se pudo actualizar en servidor: " + msg);
                                    return;
                                }
                                Demora dSrv = resp.body().data;
                                new Thread(() -> {
                                    demoraDao.upsert(dSrv);
                                    if (onOk != null) onOk.run();
                                }).start();
                            }

                            @Override
                            public void onFailure(Call<ApiResponseSingle<Demora>> call, Throwable t) {
                                if (onError != null) onError.onError("Error de red (actualizar): " + t.getMessage());
                            }
                        });
                    }
                }

                @Override
                public void onFailure(Call<ApiResponseList<Demora>> call, Throwable t) {
                    if (onError != null) onError.onError("No se pudo verificar demoras en servidor: " + t.getMessage());
                }
            });

        }).start();
    }

    public void sincronizarDemora(long vueloId, OnOk onOk, @Nullable OnError onError) {
        ApiService api = ApiClient.getClient().create(ApiService.class);
        api.listarDemoras(vueloId).enqueue(new Callback<ApiResponseList<Demora>>() {
            @Override
            public void onResponse(Call<ApiResponseList<Demora>> call,
                                   Response<ApiResponseList<Demora>> resp) {
                if (!resp.isSuccessful() || resp.body()==null || !resp.body().ok) {
                    if (onError != null) onError.onError("No se pudo sincronizar demoras");
                    return;
                }
                new Thread(() -> {
                    java.util.List<Demora> remotas = (resp.body().data != null) ? resp.body().data.data : null;
                    if (remotas != null) {
                        for (Demora d : remotas) demoraDao.upsert(d);
                    }
                    if (onOk != null) onOk.run();
                }).start();
            }

            @Override
            public void onFailure(Call<ApiResponseList<Demora>> call, Throwable t) {
                if (onError != null) onError.onError("Error de red: " + t.getMessage());
            }
        });

    }


    // Crear una interfaz simple para callback
    public interface OnResult<T> { void onResult(@Nullable T value); }

    // Usar un executor simple
    private static final java.util.concurrent.Executor DB_IO =
            java.util.concurrent.Executors.newSingleThreadExecutor();

    // Obtener demora en background y devolver por callback
    public void obtenerDemoraAsync(long vueloId, OnResult<Demora> cb) {
        DB_IO.execute(() -> {
            Demora d = demoraDao.byVuelo(vueloId);   // ← ahora SÍ fuera del UI
            new android.os.Handler(android.os.Looper.getMainLooper())
                    .post(() -> cb.onResult(d));     // devuelves en el hilo principal
        });
    }

    // (Opcional) también haz async el eliminar:
    public void eliminarDemoraAsync(long vueloId, OnOk onOk) {
        DB_IO.execute(() -> {
            demoraDao.deleteByVuelo(vueloId);
            new android.os.Handler(android.os.Looper.getMainLooper()).post(onOk::run);
        });
    }



}
