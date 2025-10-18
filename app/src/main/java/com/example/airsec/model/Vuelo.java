package com.example.airsec.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@Entity(
        tableName = "control_aeronave_vuelos",
        indices = {
                @Index("fecha"),
                @Index("app_cerrado"),
                @Index("created_by_user_id")
        }
)
public class Vuelo implements Serializable {
    @SerializedName("id")
    @PrimaryKey(autoGenerate = true)
    public long id;

    public String fecha;                 // yyyy-MM-dd
    public String origen;
    public String destino;

//    @ColumnInfo(name = "numero_vuelo_llegando")
    @SerializedName("numero_vuelo_llegando")
    public String numeroVueloLlegando;

//    @ColumnInfo(name = "numero_vuelo_saliendo")
    @SerializedName("numero_vuelo_saliendo")
    public String numeroVueloSaliendo;

    @SerializedName("matricula")
    public String matricula;

//    @ColumnInfo(name = "operador_id")

    @SerializedName("operador_id")
    public Long operadorId;

//    @ColumnInfo(name = "posicion_llegada")
    @SerializedName("posicion_llegada")
    public String posicionLlegada;
//    @ColumnInfo(name = "hora_llegada_real")

    @SerializedName("hora_llegada_real")
    public String horaLlegadaReal;

//    @ColumnInfo(name = "hora_salida_itinerario")
    @SerializedName("hora_salida_itinerario")
    public String horaSalidaItinerario;

//    @ColumnInfo(name = "hora_salida_pushback")
    @SerializedName("hora_salida_pushback")
    public String horaSalidaPushback;

//    @ColumnInfo(name = "total_pax")
    @SerializedName("total_pax") public Integer totalPax;

//    @ColumnInfo(name = "coordinador_id")
    @SerializedName("coordinador_id") public Long coordinadorId;


//    @ColumnInfo(name = "lider_vuelo_id")
    @SerializedName("lider_vuelo_id")
    public Long liderVueloId;

    // Flags locales
    @ColumnInfo(name = "app_bloqueado") public boolean appBloqueado;
    @ColumnInfo(name = "app_cerrado") public boolean appCerrado;
    @ColumnInfo(name = "app_cerrado_at") public String appCerradoAt;

    // Multiusuario / auditoría
    @ColumnInfo(name = "created_by_user_id") public Long createdByUserId;

    @ColumnInfo(name = "created_at") public String createdAt;
    @ColumnInfo(name = "updated_at") public String updatedAt;
}
