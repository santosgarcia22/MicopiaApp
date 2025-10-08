
package com.example.airsec.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.airsec.Flight;

import java.util.List;

@Dao
public interface FlightDao {
    @Insert long insert(Flight f);
    @Update int update(Flight f);

    @Query("SELECT * FROM flights WHERE id=:id")
    Flight get(long id);

    @Query("SELECT * FROM flights ORDER BY fecha DESC, id DESC")
    List<Flight> list();

    @Query("SELECT * FROM flights WHERE codigo=:codigo LIMIT 1")
    Flight findByCodigo(String codigo);

    @Query("SELECT COUNT(*) FROM flights WHERE codigo=:codigo")
    int countByCodigo(String codigo);

    @Query("UPDATE flights SET bloqueado=1, cerrado=1, cerradoAt=:ts WHERE id=:id")
    void cerrarVuelo(long id, String ts);

    @Query("SELECT cerrado FROM flights WHERE id=:id")
    boolean isCerrado(long id);
}
