package com.example.airsec.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.airsec.model.TiemposOperativos;

@Dao
public interface TiemposOperativosDao {

    @Query("SELECT * FROM control_aeronave_tiempos_operativos WHERE vuelo_id=:vueloId LIMIT 1")
    TiemposOperativos byVuelo(long vueloId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long upsert(TiemposOperativos t); // requiere que 'id' esté seteado para reemplazar

    @Update
    int update(TiemposOperativos t);

    @Insert
    long insert(TiemposOperativos t);
}
