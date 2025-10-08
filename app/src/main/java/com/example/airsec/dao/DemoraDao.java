package com.example.airsec.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.airsec.model.Demora;

@Dao
public interface DemoraDao {

    @Query("SELECT * FROM control_aeronave_demoras WHERE vuelo_id=:vueloId LIMIT 1")
    Demora byVuelo(long vueloId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long upsert(Demora d);

    @Update
    int update(Demora d);

    @Query("DELETE FROM control_aeronave_demoras WHERE vuelo_id=:vueloId")
    int deleteByVuelo(long vueloId);
}
