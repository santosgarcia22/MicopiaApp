package com.example.airsec.network;

import com.example.airsec.model.Acceso;
import com.example.airsec.model.Demora;
import com.example.airsec.model.Vuelo;
import retrofit2.Call;
import java.util.List;
import com.example.airsec.network.ApiResponseSingle;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Multipart;
import retrofit2.http.Part;
import retrofit2.http.POST;
import com.example.airsec.network.DemoraRequest;
import com.example.airsec.network.ApiResponseList;
import com.google.gson.JsonElement;

public interface ApiService {

    // Obtiene lista de vuelos (paginada)
    @GET("vuelos")
    Call<ApiResponseList<Vuelo>> getVuelos(
            @Query("q") String q,
            @Query("fecha") String fecha,
            @Query("page") Integer page
    );

    // Obtiene un vuelo por ID
    @GET("vuelos/{id}")
    Call<ApiResponseSingle<Vuelo>> getVueloById(@Path("id") long id);

    // 🔹 Registrar nuevo acceso (sin imagen)
    @POST("vuelos/{vueloId}/accesos")
    Call<ApiResponseSingle<Acceso>> crearAcceso(
            @Path("vueloId") long vueloId,
            @Body Acceso acceso
    );

    // 🔹 Registrar acceso con firma (imagen)
    @Multipart
    @POST("vuelos/{vueloId}/accesos")
    Call<ApiResponseSingle<Acceso>> crearAccesoConFirma(
            @Path("vueloId") long vueloId,
            @Part("nombre") RequestBody nombre,
            @Part("identificacion") RequestBody identificacion,  // ← antes decía "id"
            @Part("empresa") RequestBody empresa,
            @Part("herramientas") RequestBody herramientas,
            @Part("motivo_entrada") RequestBody motivoEntrada,   // ← antes decía "motivo"
            @Part("hora_entrada") RequestBody horaEntrada,
            @Part("hora_salida") RequestBody horaSalida,
            @Part("hora_entrada1") RequestBody horaEntrada1,
            @Part("hora_salida2") RequestBody horaSalida2,
            @Part MultipartBody.Part firma
    );


    // --- DEMORAS ---
    @POST("vuelos/{vueloId}/demoras")
    Call<ApiResponseSingle<Demora>> crearDemora(
            @Path("vueloId") long vueloId,
            @Body DemoraRequest body
    );

    @PUT("vuelos/{vueloId}/demoras/{id}")
    Call<ApiResponseSingle<Demora>> actualizarDemora(
            @Path("vueloId") long vueloId,
            @Path("id") long demoraId,
            @Body DemoraRequest body
    );

    @GET("vuelos/{vueloId}/demoras")
    Call<ApiResponseList<Demora>> listarDemoras(
            @Path("vueloId") long vueloId
    );

    @GET("vuelos/{vueloId}/demoras/{id}")
    Call<ApiResponseSingle<Demora>> getDemora(
            @Path("vueloId") long vueloId,
            @Path("id") long demoraId
    );

    @DELETE("vuelos/{vueloId}/demoras/{id}")
    Call<ApiResponseSingle<Object>> eliminarDemora(
            @Path("vueloId") long vueloId,
            @Path("id") long demoraId
    );

    //PRUEBA DE RAW PARA LAS DEMORAS
    @Headers("Accept: application/json")
    @GET("vuelos/{vueloId}/demoras")
    Call<JsonElement> listarDemorasRaw(@Path("vueloId") long vueloId);

    @Headers("Accept: application/json")
    @POST("vuelos/{vueloId}/demoras")
    Call<JsonElement> crearDemoraRaw(@Path("vueloId") long vueloId, @Body DemoraRequest body);

    @Headers("Accept: application/json")
    @PUT("vuelos/{vueloId}/demoras/{id}")
    Call<JsonElement> actualizarDemoraRaw(@Path("vueloId") long vueloId, @Path("id") long demoraId, @Body DemoraRequest body);

    @Headers("Accept: application/json")
    @DELETE("vuelos/{vueloId}/demoras/{id}")
    Call<JsonElement> eliminarDemoraRaw(@Path("vueloId") long vueloId, @Path("id") long demoraId);
}
