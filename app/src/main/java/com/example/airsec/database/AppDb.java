package com.example.airsec.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

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
        version = 6,               // <-- súbelo si ya existía la DB en el emulador
        exportSchema = false       // evita el warning de export schema
)
public abstract class AppDb extends RoomDatabase {

    private static volatile AppDb INSTANCE;

    public abstract VueloDao vueloDao();
    public abstract TiemposOperativosDao tiemposOperativosDao();
    public abstract AccesoDao accesoDao();
    public abstract DemoraDao demoraDao();
    public abstract OperadorDao operadorDao();


    // 4 → 5: renombrar tabla 'accesos' a 'control_aeronave_accesos'
    private static final Migration MIGRATION_4_5 = new Migration(4, 5) {
        @Override public void migrate(@NonNull SupportSQLiteDatabase db) {
            // Si existía 'accesos', la renombramos.
            // (Si ya estaba renombrada, esta sentencia fallaría; si te ocurre, desinstala la app o usa wipe data.)
            db.execSQL("ALTER TABLE accesos RENAME TO control_aeronave_accesos");
        }
    };

    // 5 → 6: agregar columna server_id (nullable)
    private static final Migration MIGRATION_5_6 = new Migration(5, 6) {
        @Override public void migrate(@NonNull SupportSQLiteDatabase db) {
            db.execSQL("ALTER TABLE control_aeronave_accesos ADD COLUMN server_id INTEGER");
        }
    };

    public static AppDb get(Context ctx) {
        if (INSTANCE == null) {
            synchronized (AppDb.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                                    ctx.getApplicationContext(),
                                    AppDb.class,
                                    "control-aeronave-db"
                            )
                            .addMigrations(MIGRATION_4_5, MIGRATION_5_6) // ⬅️ registra ambas

                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
