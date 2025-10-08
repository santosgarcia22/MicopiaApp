
package com.example.airsec.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.airsec.FlightEvent;

import java.util.List;

@Dao
public interface FlightEventDao {
    @Insert long insert(FlightEvent e);
    @Query("SELECT * FROM flight_events WHERE flightId=:flightId ORDER BY timestamp ASC")
    List<FlightEvent> eventsFor(long flightId);
    @Query("SELECT * FROM flight_events WHERE flightId=:flightId AND tipo=:tipo LIMIT 1")
    FlightEvent firstOfType(long flightId, String tipo);
}
