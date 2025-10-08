
package com.example.airsec;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "flight_events",
    foreignKeys = @ForeignKey(entity = Flight.class, parentColumns = "id", childColumns = "flightId", onDelete = ForeignKey.CASCADE),
    indices = {@Index("flightId"), @Index("tipo")})
public class FlightEvent {
    @PrimaryKey(autoGenerate = true) public long id;
    public long flightId;
    @NonNull public String tipo = "";
    @NonNull public String timestamp = "";
    public String nota;
}
