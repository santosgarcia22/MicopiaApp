package com.example.airsec.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.airsec.model.Vuelo;

import java.util.List;

@Dao
public interface VueloDao {

    @Insert
    long insert(Vuelo v);

    @Update
    int update(Vuelo v);

    @Delete
    int delete(Vuelo v);

    @Query("SELECT * FROM control_aeronave_vuelos WHERE id=:id LIMIT 1")
    Vuelo get(long id);

    @Query("SELECT * FROM control_aeronave_vuelos ORDER BY created_at DESC")
    List<Vuelo> list();

    @Query("SELECT id FROM control_aeronave_vuelos ORDER BY id DESC LIMIT 1")
    Long lastId();

    @Query("SELECT * FROM control_aeronave_vuelos " +
            "WHERE origen LIKE :q OR destino LIKE :q OR matricula LIKE :q " +
            "ORDER BY created_at DESC")
    List<Vuelo> search(String q);

    @Query("UPDATE control_aeronave_vuelos SET app_bloqueado=1, app_cerrado=1, updated_at=:ts WHERE id=:id")
    void cerrarVuelo(long id, String ts);

    @Query("SELECT app_cerrado FROM control_aeronave_vuelos WHERE id=:id")
    boolean isCerrado(long id);
}
