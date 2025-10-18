package com.example.airsec.model;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.ColumnInfo;

@Entity(
        tableName = "accesos",
        foreignKeys = @ForeignKey(
                entity = Vuelo.class,
                parentColumns = "id",
                childColumns = "vuelo_id",
                onDelete = ForeignKey.CASCADE
        ),
        indices = { @Index("vuelo_id"), @Index("identificacion"), @Index("empresa") }
)
public class Acceso {

    @PrimaryKey(autoGenerate = true) public long id;

    @ColumnInfo(name = "vuelo_id") public long vueloId;

    public String nombre;             // varchar(120)
    public String identificacion;     // varchar(50)
    public String empresa;            // varchar(120)
    public Byte herramientas;         // 0/1

    @ColumnInfo(name = "motivo_entrada") public String motivoEntrada;

    @ColumnInfo(name = "hora_entrada")  public String horaEntrada;
    @ColumnInfo(name = "hora_salida")   public String horaSalida;
    @ColumnInfo(name = "hora_entrada1") public String horaEntrada1;
    @ColumnInfo(name = "hora_salida2")  public String horaSalida2;

    @ColumnInfo(name = "firma_path") public String firmaPath;

    @ColumnInfo(name = "created_at") public String createdAt;
    @ColumnInfo(name = "updated_at") public String updatedAt;

}
