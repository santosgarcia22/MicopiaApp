package com.example.airsec.network;

import com.example.airsec.model.Acceso;
import com.example.airsec.model.Vuelo;
import retrofit2.Call;
import java.util.List;
import com.example.airsec.model.Acceso;
import com.example.airsec.network.ApiResponseSingle;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Multipart;
import retrofit2.http.Part;
import retrofit2.http.POST;
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


}
