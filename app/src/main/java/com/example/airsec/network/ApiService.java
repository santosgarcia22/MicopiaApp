package com.example.airsec.network;

import com.example.airsec.model.Vuelo;
import retrofit2.Call;
import java.util.List;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

    // Obtiene lista de vuelos (paginada)
    @GET("vuelos")
    Call<ApiResponse<Vuelo>> getVuelos(
            @Query("q") String q,
            @Query("fecha") String fecha,
            @Query("page") Integer page
    );

    // Obtiene un vuelo por ID
    @GET("vuelos/{id}")
    Call<ApiResponse<Vuelo>> getVueloById(@Path("id") long id);
}
