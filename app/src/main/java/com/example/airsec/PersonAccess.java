
package com.example.airsec;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "person_access",
    foreignKeys = @ForeignKey(entity = Flight.class, parentColumns = "id", childColumns = "flightId", onDelete = ForeignKey.CASCADE),
    indices = {@Index("flightId"), @Index("idDocumento")})
public class PersonAccess {
    @PrimaryKey(autoGenerate = true) public long id;
    public long flightId;
    @NonNull public String nombreCompleto = "";
    @NonNull public String idDocumento = "";
    public String empresaArea;
    public String herramientas;
    public String motivoEntrada;
    public String horaEntrada;
    public String horaSalida;
    public String firmaPath;
}
