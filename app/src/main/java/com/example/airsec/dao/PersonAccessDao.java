
package com.example.airsec.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.airsec.PersonAccess;

import java.util.List;

@Dao
public interface PersonAccessDao {
    @Insert long insert(PersonAccess p);
    @Update int update(PersonAccess p);
    @Query("SELECT * FROM person_access WHERE flightId=:flightId ORDER BY nombreCompleto ASC")
    List<PersonAccess> listForFlight(long flightId);
    @Query("SELECT * FROM person_access WHERE flightId=:flightId AND idDocumento=:doc LIMIT 1")
    PersonAccess findByDoc(long flightId, String doc);
}
