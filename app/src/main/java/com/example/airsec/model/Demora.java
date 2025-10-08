package com.example.airsec.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.ColumnInfo;

@Entity(
        tableName = "control_aeronave_demoras",
        foreignKeys = @ForeignKey(
                entity = Vuelo.class,
                parentColumns = "id",
                childColumns = "vuelo_id",
                onDelete = ForeignKey.CASCADE
        ),
        indices = {
                @Index(value = {"vuelo_id"}, unique = true) // <-- ÚNICO: una demora por vuelo
        }
)
public class Demora {
    @PrimaryKey(autoGenerate = true) public long id;

    @ColumnInfo(name = "vuelo_id") public long vueloId;

    public String motivo;     // varchar(200)
    public Integer minutos;   // int

    @ColumnInfo(name = "agente_id") public Long agenteId;

    @ColumnInfo(name = "created_at") public String createdAt;
    @ColumnInfo(name = "updated_at") public String updatedAt;
}
