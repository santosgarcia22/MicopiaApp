package com.example.airsec;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(
        tableName = "flights",
        indices = {@Index(value = {"codigo"}, unique = true)}
)
public class Flight {
    @PrimaryKey(autoGenerate = true) public long id;

    // Identificador humano único (ej. "2025-08-12-SAL123")
    @NonNull public String codigo = "";

    @NonNull public String fecha = "";
    @NonNull public String origen = "";   // puedes usar "Origen/Nº Vuelo"
    @NonNull public String destino = "";
    public String matricula;
    public String operador;
    public String horaLlegada;
    public String posicionLlegada;
    public String inspeccionCabinaInicio;
    public String inspeccionCabinaFin;
    public String demoraTiempo;
    public String demoraNoVuelo;
    public String demoraMotivo;
    public Integer totalPax;
    public boolean bloqueado = false;
    public boolean cerrado = false;      // true cuando se cierra el vuelo
    public String cerradoAt;             // timestamp ISO cuando se cerró


}
