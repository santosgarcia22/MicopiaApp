package com.example.airsec.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.airsec.model.Operador;

import java.util.List;

@Dao
public interface OperadorDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long upsert(Operador o);

    @Query("SELECT * FROM control_aeronave_operadores ORDER BY nombre ASC")
    List<Operador> all();
}
