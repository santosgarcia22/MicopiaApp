package com.example.airsec.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.Index;
import androidx.room.ColumnInfo;

@Entity(
        tableName = "control_aeronave_operadores",
        indices = { @Index(value = {"codigo"}, unique = true), @Index("nombre") }
)
public class Operador {
    @PrimaryKey(autoGenerate = true) public long id;

    public String codigo; // varchar(10) UNIQUE
    public String nombre; // varchar(100)

    @ColumnInfo(name = "created_at") public String createdAt;
    @ColumnInfo(name = "updated_at") public String updatedAt;
}
