package com.example.airsec.database;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomOpenHelper;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import com.example.airsec.dao.AccesoDao;
import com.example.airsec.dao.AccesoDao_Impl;
import com.example.airsec.dao.DemoraDao;
import com.example.airsec.dao.DemoraDao_Impl;
import com.example.airsec.dao.OperadorDao;
import com.example.airsec.dao.OperadorDao_Impl;
import com.example.airsec.dao.TiemposOperativosDao;
import com.example.airsec.dao.TiemposOperativosDao_Impl;
import com.example.airsec.dao.VueloDao;
import com.example.airsec.dao.VueloDao_Impl;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Generated;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class AppDb_Impl extends AppDb {
  private volatile VueloDao _vueloDao;

  private volatile TiemposOperativosDao _tiemposOperativosDao;

  private volatile AccesoDao _accesoDao;

  private volatile DemoraDao _demoraDao;

  private volatile OperadorDao _operadorDao;

  @Override
  @NonNull
  protected SupportSQLiteOpenHelper createOpenHelper(@NonNull final DatabaseConfiguration config) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(config, new RoomOpenHelper.Delegate(6) {
      @Override
      public void createAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS `control_aeronave_vuelos` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `fecha` TEXT, `origen` TEXT, `destino` TEXT, `numeroVueloLlegando` TEXT, `numeroVueloSaliendo` TEXT, `matricula` TEXT, `operadorId` INTEGER, `posicionLlegada` TEXT, `horaLlegadaReal` TEXT, `horaSalidaItinerario` TEXT, `horaSalidaPushback` TEXT, `totalPax` INTEGER, `coordinadorId` INTEGER, `liderVueloId` INTEGER, `app_bloqueado` INTEGER NOT NULL, `app_cerrado` INTEGER NOT NULL, `app_cerrado_at` TEXT, `created_by_user_id` INTEGER, `created_at` TEXT, `updated_at` TEXT)");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_control_aeronave_vuelos_fecha` ON `control_aeronave_vuelos` (`fecha`)");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_control_aeronave_vuelos_app_cerrado` ON `control_aeronave_vuelos` (`app_cerrado`)");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_control_aeronave_vuelos_created_by_user_id` ON `control_aeronave_vuelos` (`created_by_user_id`)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `control_aeronave_demoras` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `vuelo_id` INTEGER NOT NULL, `motivo` TEXT, `minutos` INTEGER, `agente_id` INTEGER, `created_at` TEXT, `updated_at` TEXT, FOREIGN KEY(`vuelo_id`) REFERENCES `control_aeronave_vuelos`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE )");
        db.execSQL("CREATE UNIQUE INDEX IF NOT EXISTS `index_control_aeronave_demoras_vuelo_id` ON `control_aeronave_demoras` (`vuelo_id`)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `control_aeronave_tiempos_operativos` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `vuelo_id` INTEGER NOT NULL, `desabordaje_inicio` TEXT, `desabordaje_fin` TEXT, `abordaje_inicio` TEXT, `abordaje_fin` TEXT, `inspeccion_cabina_inicio` TEXT, `inspeccion_cabina_fin` TEXT, `aseo_ingreso` TEXT, `aseo_salida` TEXT, `tripulacion_ingreso` TEXT, `cierre_puerta` TEXT, `created_at` TEXT, `updated_at` TEXT, FOREIGN KEY(`vuelo_id`) REFERENCES `control_aeronave_vuelos`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE )");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_control_aeronave_tiempos_operativos_vuelo_id` ON `control_aeronave_tiempos_operativos` (`vuelo_id`)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `control_aeronave_accesos` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `server_id` INTEGER, `vuelo_id` INTEGER NOT NULL, `nombre` TEXT, `identificacion` TEXT, `empresa` TEXT, `herramientas` INTEGER, `motivo_entrada` TEXT, `hora_entrada` TEXT, `hora_salida` TEXT, `hora_entrada1` TEXT, `hora_salida2` TEXT, `firma_path` TEXT, `created_at` TEXT, `updated_at` TEXT, FOREIGN KEY(`vuelo_id`) REFERENCES `control_aeronave_vuelos`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE )");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_control_aeronave_accesos_vuelo_id` ON `control_aeronave_accesos` (`vuelo_id`)");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_control_aeronave_accesos_identificacion` ON `control_aeronave_accesos` (`identificacion`)");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_control_aeronave_accesos_empresa` ON `control_aeronave_accesos` (`empresa`)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `control_aeronave_operadores` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `codigo` TEXT, `nombre` TEXT, `created_at` TEXT, `updated_at` TEXT)");
        db.execSQL("CREATE UNIQUE INDEX IF NOT EXISTS `index_control_aeronave_operadores_codigo` ON `control_aeronave_operadores` (`codigo`)");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_control_aeronave_operadores_nombre` ON `control_aeronave_operadores` (`nombre`)");
        db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '86e68c8f3a8b19ab0db2cf321d5a2675')");
      }

      @Override
      public void dropAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS `control_aeronave_vuelos`");
        db.execSQL("DROP TABLE IF EXISTS `control_aeronave_demoras`");
        db.execSQL("DROP TABLE IF EXISTS `control_aeronave_tiempos_operativos`");
        db.execSQL("DROP TABLE IF EXISTS `control_aeronave_accesos`");
        db.execSQL("DROP TABLE IF EXISTS `control_aeronave_operadores`");
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onDestructiveMigration(db);
          }
        }
      }

      @Override
      public void onCreate(@NonNull final SupportSQLiteDatabase db) {
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onCreate(db);
          }
        }
      }

      @Override
      public void onOpen(@NonNull final SupportSQLiteDatabase db) {
        mDatabase = db;
        db.execSQL("PRAGMA foreign_keys = ON");
        internalInitInvalidationTracker(db);
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onOpen(db);
          }
        }
      }

      @Override
      public void onPreMigrate(@NonNull final SupportSQLiteDatabase db) {
        DBUtil.dropFtsSyncTriggers(db);
      }

      @Override
      public void onPostMigrate(@NonNull final SupportSQLiteDatabase db) {
      }

      @Override
      @NonNull
      public RoomOpenHelper.ValidationResult onValidateSchema(
          @NonNull final SupportSQLiteDatabase db) {
        final HashMap<String, TableInfo.Column> _columnsControlAeronaveVuelos = new HashMap<String, TableInfo.Column>(21);
        _columnsControlAeronaveVuelos.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsControlAeronaveVuelos.put("fecha", new TableInfo.Column("fecha", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsControlAeronaveVuelos.put("origen", new TableInfo.Column("origen", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsControlAeronaveVuelos.put("destino", new TableInfo.Column("destino", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsControlAeronaveVuelos.put("numeroVueloLlegando", new TableInfo.Column("numeroVueloLlegando", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsControlAeronaveVuelos.put("numeroVueloSaliendo", new TableInfo.Column("numeroVueloSaliendo", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsControlAeronaveVuelos.put("matricula", new TableInfo.Column("matricula", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsControlAeronaveVuelos.put("operadorId", new TableInfo.Column("operadorId", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsControlAeronaveVuelos.put("posicionLlegada", new TableInfo.Column("posicionLlegada", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsControlAeronaveVuelos.put("horaLlegadaReal", new TableInfo.Column("horaLlegadaReal", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsControlAeronaveVuelos.put("horaSalidaItinerario", new TableInfo.Column("horaSalidaItinerario", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsControlAeronaveVuelos.put("horaSalidaPushback", new TableInfo.Column("horaSalidaPushback", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsControlAeronaveVuelos.put("totalPax", new TableInfo.Column("totalPax", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsControlAeronaveVuelos.put("coordinadorId", new TableInfo.Column("coordinadorId", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsControlAeronaveVuelos.put("liderVueloId", new TableInfo.Column("liderVueloId", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsControlAeronaveVuelos.put("app_bloqueado", new TableInfo.Column("app_bloqueado", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsControlAeronaveVuelos.put("app_cerrado", new TableInfo.Column("app_cerrado", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsControlAeronaveVuelos.put("app_cerrado_at", new TableInfo.Column("app_cerrado_at", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsControlAeronaveVuelos.put("created_by_user_id", new TableInfo.Column("created_by_user_id", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsControlAeronaveVuelos.put("created_at", new TableInfo.Column("created_at", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsControlAeronaveVuelos.put("updated_at", new TableInfo.Column("updated_at", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysControlAeronaveVuelos = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesControlAeronaveVuelos = new HashSet<TableInfo.Index>(3);
        _indicesControlAeronaveVuelos.add(new TableInfo.Index("index_control_aeronave_vuelos_fecha", false, Arrays.asList("fecha"), Arrays.asList("ASC")));
        _indicesControlAeronaveVuelos.add(new TableInfo.Index("index_control_aeronave_vuelos_app_cerrado", false, Arrays.asList("app_cerrado"), Arrays.asList("ASC")));
        _indicesControlAeronaveVuelos.add(new TableInfo.Index("index_control_aeronave_vuelos_created_by_user_id", false, Arrays.asList("created_by_user_id"), Arrays.asList("ASC")));
        final TableInfo _infoControlAeronaveVuelos = new TableInfo("control_aeronave_vuelos", _columnsControlAeronaveVuelos, _foreignKeysControlAeronaveVuelos, _indicesControlAeronaveVuelos);
        final TableInfo _existingControlAeronaveVuelos = TableInfo.read(db, "control_aeronave_vuelos");
        if (!_infoControlAeronaveVuelos.equals(_existingControlAeronaveVuelos)) {
          return new RoomOpenHelper.ValidationResult(false, "control_aeronave_vuelos(com.example.airsec.model.Vuelo).\n"
                  + " Expected:\n" + _infoControlAeronaveVuelos + "\n"
                  + " Found:\n" + _existingControlAeronaveVuelos);
        }
        final HashMap<String, TableInfo.Column> _columnsControlAeronaveDemoras = new HashMap<String, TableInfo.Column>(7);
        _columnsControlAeronaveDemoras.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsControlAeronaveDemoras.put("vuelo_id", new TableInfo.Column("vuelo_id", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsControlAeronaveDemoras.put("motivo", new TableInfo.Column("motivo", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsControlAeronaveDemoras.put("minutos", new TableInfo.Column("minutos", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsControlAeronaveDemoras.put("agente_id", new TableInfo.Column("agente_id", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsControlAeronaveDemoras.put("created_at", new TableInfo.Column("created_at", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsControlAeronaveDemoras.put("updated_at", new TableInfo.Column("updated_at", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysControlAeronaveDemoras = new HashSet<TableInfo.ForeignKey>(1);
        _foreignKeysControlAeronaveDemoras.add(new TableInfo.ForeignKey("control_aeronave_vuelos", "CASCADE", "NO ACTION", Arrays.asList("vuelo_id"), Arrays.asList("id")));
        final HashSet<TableInfo.Index> _indicesControlAeronaveDemoras = new HashSet<TableInfo.Index>(1);
        _indicesControlAeronaveDemoras.add(new TableInfo.Index("index_control_aeronave_demoras_vuelo_id", true, Arrays.asList("vuelo_id"), Arrays.asList("ASC")));
        final TableInfo _infoControlAeronaveDemoras = new TableInfo("control_aeronave_demoras", _columnsControlAeronaveDemoras, _foreignKeysControlAeronaveDemoras, _indicesControlAeronaveDemoras);
        final TableInfo _existingControlAeronaveDemoras = TableInfo.read(db, "control_aeronave_demoras");
        if (!_infoControlAeronaveDemoras.equals(_existingControlAeronaveDemoras)) {
          return new RoomOpenHelper.ValidationResult(false, "control_aeronave_demoras(com.example.airsec.model.Demora).\n"
                  + " Expected:\n" + _infoControlAeronaveDemoras + "\n"
                  + " Found:\n" + _existingControlAeronaveDemoras);
        }
        final HashMap<String, TableInfo.Column> _columnsControlAeronaveTiemposOperativos = new HashMap<String, TableInfo.Column>(14);
        _columnsControlAeronaveTiemposOperativos.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsControlAeronaveTiemposOperativos.put("vuelo_id", new TableInfo.Column("vuelo_id", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsControlAeronaveTiemposOperativos.put("desabordaje_inicio", new TableInfo.Column("desabordaje_inicio", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsControlAeronaveTiemposOperativos.put("desabordaje_fin", new TableInfo.Column("desabordaje_fin", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsControlAeronaveTiemposOperativos.put("abordaje_inicio", new TableInfo.Column("abordaje_inicio", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsControlAeronaveTiemposOperativos.put("abordaje_fin", new TableInfo.Column("abordaje_fin", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsControlAeronaveTiemposOperativos.put("inspeccion_cabina_inicio", new TableInfo.Column("inspeccion_cabina_inicio", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsControlAeronaveTiemposOperativos.put("inspeccion_cabina_fin", new TableInfo.Column("inspeccion_cabina_fin", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsControlAeronaveTiemposOperativos.put("aseo_ingreso", new TableInfo.Column("aseo_ingreso", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsControlAeronaveTiemposOperativos.put("aseo_salida", new TableInfo.Column("aseo_salida", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsControlAeronaveTiemposOperativos.put("tripulacion_ingreso", new TableInfo.Column("tripulacion_ingreso", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsControlAeronaveTiemposOperativos.put("cierre_puerta", new TableInfo.Column("cierre_puerta", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsControlAeronaveTiemposOperativos.put("created_at", new TableInfo.Column("created_at", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsControlAeronaveTiemposOperativos.put("updated_at", new TableInfo.Column("updated_at", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysControlAeronaveTiemposOperativos = new HashSet<TableInfo.ForeignKey>(1);
        _foreignKeysControlAeronaveTiemposOperativos.add(new TableInfo.ForeignKey("control_aeronave_vuelos", "CASCADE", "NO ACTION", Arrays.asList("vuelo_id"), Arrays.asList("id")));
        final HashSet<TableInfo.Index> _indicesControlAeronaveTiemposOperativos = new HashSet<TableInfo.Index>(1);
        _indicesControlAeronaveTiemposOperativos.add(new TableInfo.Index("index_control_aeronave_tiempos_operativos_vuelo_id", false, Arrays.asList("vuelo_id"), Arrays.asList("ASC")));
        final TableInfo _infoControlAeronaveTiemposOperativos = new TableInfo("control_aeronave_tiempos_operativos", _columnsControlAeronaveTiemposOperativos, _foreignKeysControlAeronaveTiemposOperativos, _indicesControlAeronaveTiemposOperativos);
        final TableInfo _existingControlAeronaveTiemposOperativos = TableInfo.read(db, "control_aeronave_tiempos_operativos");
        if (!_infoControlAeronaveTiemposOperativos.equals(_existingControlAeronaveTiemposOperativos)) {
          return new RoomOpenHelper.ValidationResult(false, "control_aeronave_tiempos_operativos(com.example.airsec.model.TiemposOperativos).\n"
                  + " Expected:\n" + _infoControlAeronaveTiemposOperativos + "\n"
                  + " Found:\n" + _existingControlAeronaveTiemposOperativos);
        }
        final HashMap<String, TableInfo.Column> _columnsControlAeronaveAccesos = new HashMap<String, TableInfo.Column>(15);
        _columnsControlAeronaveAccesos.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsControlAeronaveAccesos.put("server_id", new TableInfo.Column("server_id", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsControlAeronaveAccesos.put("vuelo_id", new TableInfo.Column("vuelo_id", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsControlAeronaveAccesos.put("nombre", new TableInfo.Column("nombre", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsControlAeronaveAccesos.put("identificacion", new TableInfo.Column("identificacion", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsControlAeronaveAccesos.put("empresa", new TableInfo.Column("empresa", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsControlAeronaveAccesos.put("herramientas", new TableInfo.Column("herramientas", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsControlAeronaveAccesos.put("motivo_entrada", new TableInfo.Column("motivo_entrada", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsControlAeronaveAccesos.put("hora_entrada", new TableInfo.Column("hora_entrada", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsControlAeronaveAccesos.put("hora_salida", new TableInfo.Column("hora_salida", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsControlAeronaveAccesos.put("hora_entrada1", new TableInfo.Column("hora_entrada1", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsControlAeronaveAccesos.put("hora_salida2", new TableInfo.Column("hora_salida2", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsControlAeronaveAccesos.put("firma_path", new TableInfo.Column("firma_path", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsControlAeronaveAccesos.put("created_at", new TableInfo.Column("created_at", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsControlAeronaveAccesos.put("updated_at", new TableInfo.Column("updated_at", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysControlAeronaveAccesos = new HashSet<TableInfo.ForeignKey>(1);
        _foreignKeysControlAeronaveAccesos.add(new TableInfo.ForeignKey("control_aeronave_vuelos", "CASCADE", "NO ACTION", Arrays.asList("vuelo_id"), Arrays.asList("id")));
        final HashSet<TableInfo.Index> _indicesControlAeronaveAccesos = new HashSet<TableInfo.Index>(3);
        _indicesControlAeronaveAccesos.add(new TableInfo.Index("index_control_aeronave_accesos_vuelo_id", false, Arrays.asList("vuelo_id"), Arrays.asList("ASC")));
        _indicesControlAeronaveAccesos.add(new TableInfo.Index("index_control_aeronave_accesos_identificacion", false, Arrays.asList("identificacion"), Arrays.asList("ASC")));
        _indicesControlAeronaveAccesos.add(new TableInfo.Index("index_control_aeronave_accesos_empresa", false, Arrays.asList("empresa"), Arrays.asList("ASC")));
        final TableInfo _infoControlAeronaveAccesos = new TableInfo("control_aeronave_accesos", _columnsControlAeronaveAccesos, _foreignKeysControlAeronaveAccesos, _indicesControlAeronaveAccesos);
        final TableInfo _existingControlAeronaveAccesos = TableInfo.read(db, "control_aeronave_accesos");
        if (!_infoControlAeronaveAccesos.equals(_existingControlAeronaveAccesos)) {
          return new RoomOpenHelper.ValidationResult(false, "control_aeronave_accesos(com.example.airsec.model.Acceso).\n"
                  + " Expected:\n" + _infoControlAeronaveAccesos + "\n"
                  + " Found:\n" + _existingControlAeronaveAccesos);
        }
        final HashMap<String, TableInfo.Column> _columnsControlAeronaveOperadores = new HashMap<String, TableInfo.Column>(5);
        _columnsControlAeronaveOperadores.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsControlAeronaveOperadores.put("codigo", new TableInfo.Column("codigo", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsControlAeronaveOperadores.put("nombre", new TableInfo.Column("nombre", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsControlAeronaveOperadores.put("created_at", new TableInfo.Column("created_at", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsControlAeronaveOperadores.put("updated_at", new TableInfo.Column("updated_at", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysControlAeronaveOperadores = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesControlAeronaveOperadores = new HashSet<TableInfo.Index>(2);
        _indicesControlAeronaveOperadores.add(new TableInfo.Index("index_control_aeronave_operadores_codigo", true, Arrays.asList("codigo"), Arrays.asList("ASC")));
        _indicesControlAeronaveOperadores.add(new TableInfo.Index("index_control_aeronave_operadores_nombre", false, Arrays.asList("nombre"), Arrays.asList("ASC")));
        final TableInfo _infoControlAeronaveOperadores = new TableInfo("control_aeronave_operadores", _columnsControlAeronaveOperadores, _foreignKeysControlAeronaveOperadores, _indicesControlAeronaveOperadores);
        final TableInfo _existingControlAeronaveOperadores = TableInfo.read(db, "control_aeronave_operadores");
        if (!_infoControlAeronaveOperadores.equals(_existingControlAeronaveOperadores)) {
          return new RoomOpenHelper.ValidationResult(false, "control_aeronave_operadores(com.example.airsec.model.Operador).\n"
                  + " Expected:\n" + _infoControlAeronaveOperadores + "\n"
                  + " Found:\n" + _existingControlAeronaveOperadores);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "86e68c8f3a8b19ab0db2cf321d5a2675", "412a43bd583a1c4b3820572be9ebe0dd");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(config.context).name(config.name).callback(_openCallback).build();
    final SupportSQLiteOpenHelper _helper = config.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  @NonNull
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    final HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "control_aeronave_vuelos","control_aeronave_demoras","control_aeronave_tiempos_operativos","control_aeronave_accesos","control_aeronave_operadores");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    final boolean _supportsDeferForeignKeys = android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP;
    try {
      if (!_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA foreign_keys = FALSE");
      }
      super.beginTransaction();
      if (_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA defer_foreign_keys = TRUE");
      }
      _db.execSQL("DELETE FROM `control_aeronave_vuelos`");
      _db.execSQL("DELETE FROM `control_aeronave_demoras`");
      _db.execSQL("DELETE FROM `control_aeronave_tiempos_operativos`");
      _db.execSQL("DELETE FROM `control_aeronave_accesos`");
      _db.execSQL("DELETE FROM `control_aeronave_operadores`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      if (!_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA foreign_keys = TRUE");
      }
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  @NonNull
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(VueloDao.class, VueloDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(TiemposOperativosDao.class, TiemposOperativosDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(AccesoDao.class, AccesoDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(DemoraDao.class, DemoraDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(OperadorDao.class, OperadorDao_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  @NonNull
  public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
    final HashSet<Class<? extends AutoMigrationSpec>> _autoMigrationSpecsSet = new HashSet<Class<? extends AutoMigrationSpec>>();
    return _autoMigrationSpecsSet;
  }

  @Override
  @NonNull
  public List<Migration> getAutoMigrations(
      @NonNull final Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecs) {
    final List<Migration> _autoMigrations = new ArrayList<Migration>();
    return _autoMigrations;
  }

  @Override
  public VueloDao vueloDao() {
    if (_vueloDao != null) {
      return _vueloDao;
    } else {
      synchronized(this) {
        if(_vueloDao == null) {
          _vueloDao = new VueloDao_Impl(this);
        }
        return _vueloDao;
      }
    }
  }

  @Override
  public TiemposOperativosDao tiemposOperativosDao() {
    if (_tiemposOperativosDao != null) {
      return _tiemposOperativosDao;
    } else {
      synchronized(this) {
        if(_tiemposOperativosDao == null) {
          _tiemposOperativosDao = new TiemposOperativosDao_Impl(this);
        }
        return _tiemposOperativosDao;
      }
    }
  }

  @Override
  public AccesoDao accesoDao() {
    if (_accesoDao != null) {
      return _accesoDao;
    } else {
      synchronized(this) {
        if(_accesoDao == null) {
          _accesoDao = new AccesoDao_Impl(this);
        }
        return _accesoDao;
      }
    }
  }

  @Override
  public DemoraDao demoraDao() {
    if (_demoraDao != null) {
      return _demoraDao;
    } else {
      synchronized(this) {
        if(_demoraDao == null) {
          _demoraDao = new DemoraDao_Impl(this);
        }
        return _demoraDao;
      }
    }
  }

  @Override
  public OperadorDao operadorDao() {
    if (_operadorDao != null) {
      return _operadorDao;
    } else {
      synchronized(this) {
        if(_operadorDao == null) {
          _operadorDao = new OperadorDao_Impl(this);
        }
        return _operadorDao;
      }
    }
  }
}
