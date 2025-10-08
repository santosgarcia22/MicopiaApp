package com.example.airsec.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.ColumnInfo;

@Entity(
        tableName = "control_aeronave_tiempos_operativos",
        foreignKeys = @ForeignKey(
                entity = Vuelo.class,
                parentColumns = "id",
                childColumns = "vuelo_id",
                onDelete = ForeignKey.CASCADE
        ),
        indices = { @Index("vuelo_id") }
)
public class TiemposOperativos {
    @PrimaryKey(autoGenerate = true) public long id;

    @ColumnInfo(name = "vuelo_id") public long vueloId;

    @ColumnInfo(name = "desabordaje_inicio")   public String desabordajeInicio;
    @ColumnInfo(name = "desabordaje_fin")      public String desabordajeFin;

    @ColumnInfo(name = "abordaje_inicio")      public String abordajeInicio;
    @ColumnInfo(name = "abordaje_fin")         public String abordajeFin;

    @ColumnInfo(name = "inspeccion_cabina_inicio") public String inspeccionCabinaInicio;
    @ColumnInfo(name = "inspeccion_cabina_fin")    public String inspeccionCabinaFin;

    @ColumnInfo(name = "aseo_ingreso")         public String aseoIngreso;
    @ColumnInfo(name = "aseo_salida")          public String aseoSalida;

    @ColumnInfo(name = "tripulacion_ingreso")  public String tripulacionIngreso;
    @ColumnInfo(name = "cierre_puerta")        public String cierrePuerta;

    @ColumnInfo(name = "created_at") public String createdAt;
    @ColumnInfo(name = "updated_at") public String updatedAt;
}
