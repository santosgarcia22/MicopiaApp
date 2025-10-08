package com.example.airsec.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.airsec.model.Acceso;

import java.util.List;

@Dao
public interface AccesoDao {

    @Insert
    long insert(Acceso a);

    @Update
    int update(Acceso a);

    @Delete
    void delete(Acceso a);

    @Query("SELECT * FROM control_aeronave_accesos WHERE vuelo_id=:vueloId ORDER BY nombre ASC")
    List<Acceso> byVuelo(long vueloId);

    @Query("SELECT * FROM control_aeronave_accesos WHERE vuelo_id=:vueloId AND identificacion=:doc LIMIT 1")
    Acceso findByDoc(long vueloId, String doc);

    @Query("SELECT * FROM control_aeronave_accesos WHERE vuelo_id=:vueloId AND identificacion=:doc LIMIT 1")
    Acceso byVueloAndDoc(long vueloId, String doc);
}
