package com.example.airsec.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.airsec.dao.AccesoDao;
import com.example.airsec.dao.DemoraDao;
import com.example.airsec.dao.OperadorDao;
import com.example.airsec.dao.TiemposOperativosDao;
import com.example.airsec.dao.VueloDao;
import com.example.airsec.model.Acceso;
import com.example.airsec.model.Demora;
import com.example.airsec.model.Operador;
import com.example.airsec.model.TiemposOperativos;
import com.example.airsec.model.Vuelo;

@Database(
        entities = {
                Vuelo.class,
                Demora.class,
                TiemposOperativos.class,
                Acceso.class,
                Operador.class
        },
        version = 3,               // <-- súbelo si ya existía la DB en el emulador
        exportSchema = false       // evita el warning de export schema
)
public abstract class AppDb extends RoomDatabase {

    private static volatile AppDb INSTANCE;

    public abstract VueloDao vueloDao();
    public abstract TiemposOperativosDao tiemposOperativosDao();
    public abstract AccesoDao accesoDao();
    public abstract DemoraDao demoraDao();
    public abstract OperadorDao operadorDao();

    public static AppDb get(Context ctx) {
        if (INSTANCE == null) {
            synchronized (AppDb.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                                    ctx.getApplicationContext(),
                                    AppDb.class,
                                    "control-aeronave-db"
                            )
                            // En desarrollo: para evitar migraciones mientras iteras.
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
