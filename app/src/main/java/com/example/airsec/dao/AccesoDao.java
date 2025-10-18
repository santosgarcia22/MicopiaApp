package com.example.airsec.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import androidx.room.OnConflictStrategy;

import com.example.airsec.model.Acceso;

import java.util.List;

@Dao
public interface AccesoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(Acceso a);

    @Update
    int update(Acceso a);

    @Delete
    void delete(Acceso a);

    // 🔹 Todos los accesos de un vuelo
    @Query("SELECT * FROM accesos WHERE vuelo_id = :vueloId ORDER BY nombre ASC")
    List<Acceso> byVuelo(long vueloId);

    // 🔹 Buscar acceso por vuelo y documento (identificación)
    @Query("SELECT * FROM accesos WHERE vuelo_id = :vueloId AND identificacion = :doc LIMIT 1")
    Acceso findByDoc(long vueloId, String doc);

    // 🔹 Alias del mismo (si quieres mantener compatibilidad)
    @Query("SELECT * FROM accesos WHERE vuelo_id = :vueloId AND identificacion = :doc LIMIT 1")
    Acceso byVueloAndDoc(long vueloId, String doc);
}

