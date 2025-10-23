package com.example.airsec.dao;

import android.database.Cursor;
import androidx.annotation.NonNull;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.example.airsec.model.TiemposOperativos;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.Collections;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class TiemposOperativosDao_Impl implements TiemposOperativosDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<TiemposOperativos> __insertionAdapterOfTiemposOperativos;

  private final EntityInsertionAdapter<TiemposOperativos> __insertionAdapterOfTiemposOperativos_1;

  private final EntityDeletionOrUpdateAdapter<TiemposOperativos> __updateAdapterOfTiemposOperativos;

  public TiemposOperativosDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfTiemposOperativos = new EntityInsertionAdapter<TiemposOperativos>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `control_aeronave_tiempos_operativos` (`id`,`vuelo_id`,`desabordaje_inicio`,`desabordaje_fin`,`abordaje_inicio`,`abordaje_fin`,`inspeccion_cabina_inicio`,`inspeccion_cabina_fin`,`aseo_ingreso`,`aseo_salida`,`tripulacion_ingreso`,`cierre_puerta`,`created_at`,`updated_at`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          final TiemposOperativos entity) {
        statement.bindLong(1, entity.id);
        statement.bindLong(2, entity.vueloId);
        if (entity.desabordajeInicio == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.desabordajeInicio);
        }
        if (entity.desabordajeFin == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.desabordajeFin);
        }
        if (entity.abordajeInicio == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.abordajeInicio);
        }
        if (entity.abordajeFin == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.abordajeFin);
        }
        if (entity.inspeccionCabinaInicio == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.inspeccionCabinaInicio);
        }
        if (entity.inspeccionCabinaFin == null) {
          statement.bindNull(8);
        } else {
          statement.bindString(8, entity.inspeccionCabinaFin);
        }
        if (entity.aseoIngreso == null) {
          statement.bindNull(9);
        } else {
          statement.bindString(9, entity.aseoIngreso);
        }
        if (entity.aseoSalida == null) {
          statement.bindNull(10);
        } else {
          statement.bindString(10, entity.aseoSalida);
        }
        if (entity.tripulacionIngreso == null) {
          statement.bindNull(11);
        } else {
          statement.bindString(11, entity.tripulacionIngreso);
        }
        if (entity.cierrePuerta == null) {
          statement.bindNull(12);
        } else {
          statement.bindString(12, entity.cierrePuerta);
        }
        if (entity.createdAt == null) {
          statement.bindNull(13);
        } else {
          statement.bindString(13, entity.createdAt);
        }
        if (entity.updatedAt == null) {
          statement.bindNull(14);
        } else {
          statement.bindString(14, entity.updatedAt);
        }
      }
    };
    this.__insertionAdapterOfTiemposOperativos_1 = new EntityInsertionAdapter<TiemposOperativos>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR ABORT INTO `control_aeronave_tiempos_operativos` (`id`,`vuelo_id`,`desabordaje_inicio`,`desabordaje_fin`,`abordaje_inicio`,`abordaje_fin`,`inspeccion_cabina_inicio`,`inspeccion_cabina_fin`,`aseo_ingreso`,`aseo_salida`,`tripulacion_ingreso`,`cierre_puerta`,`created_at`,`updated_at`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          final TiemposOperativos entity) {
        statement.bindLong(1, entity.id);
        statement.bindLong(2, entity.vueloId);
        if (entity.desabordajeInicio == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.desabordajeInicio);
        }
        if (entity.desabordajeFin == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.desabordajeFin);
        }
        if (entity.abordajeInicio == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.abordajeInicio);
        }
        if (entity.abordajeFin == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.abordajeFin);
        }
        if (entity.inspeccionCabinaInicio == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.inspeccionCabinaInicio);
        }
        if (entity.inspeccionCabinaFin == null) {
          statement.bindNull(8);
        } else {
          statement.bindString(8, entity.inspeccionCabinaFin);
        }
        if (entity.aseoIngreso == null) {
          statement.bindNull(9);
        } else {
          statement.bindString(9, entity.aseoIngreso);
        }
        if (entity.aseoSalida == null) {
          statement.bindNull(10);
        } else {
          statement.bindString(10, entity.aseoSalida);
        }
        if (entity.tripulacionIngreso == null) {
          statement.bindNull(11);
        } else {
          statement.bindString(11, entity.tripulacionIngreso);
        }
        if (entity.cierrePuerta == null) {
          statement.bindNull(12);
        } else {
          statement.bindString(12, entity.cierrePuerta);
        }
        if (entity.createdAt == null) {
          statement.bindNull(13);
        } else {
          statement.bindString(13, entity.createdAt);
        }
        if (entity.updatedAt == null) {
          statement.bindNull(14);
        } else {
          statement.bindString(14, entity.updatedAt);
        }
      }
    };
    this.__updateAdapterOfTiemposOperativos = new EntityDeletionOrUpdateAdapter<TiemposOperativos>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `control_aeronave_tiempos_operativos` SET `id` = ?,`vuelo_id` = ?,`desabordaje_inicio` = ?,`desabordaje_fin` = ?,`abordaje_inicio` = ?,`abordaje_fin` = ?,`inspeccion_cabina_inicio` = ?,`inspeccion_cabina_fin` = ?,`aseo_ingreso` = ?,`aseo_salida` = ?,`tripulacion_ingreso` = ?,`cierre_puerta` = ?,`created_at` = ?,`updated_at` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          final TiemposOperativos entity) {
        statement.bindLong(1, entity.id);
        statement.bindLong(2, entity.vueloId);
        if (entity.desabordajeInicio == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.desabordajeInicio);
        }
        if (entity.desabordajeFin == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.desabordajeFin);
        }
        if (entity.abordajeInicio == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.abordajeInicio);
        }
        if (entity.abordajeFin == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.abordajeFin);
        }
        if (entity.inspeccionCabinaInicio == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.inspeccionCabinaInicio);
        }
        if (entity.inspeccionCabinaFin == null) {
          statement.bindNull(8);
        } else {
          statement.bindString(8, entity.inspeccionCabinaFin);
        }
        if (entity.aseoIngreso == null) {
          statement.bindNull(9);
        } else {
          statement.bindString(9, entity.aseoIngreso);
        }
        if (entity.aseoSalida == null) {
          statement.bindNull(10);
        } else {
          statement.bindString(10, entity.aseoSalida);
        }
        if (entity.tripulacionIngreso == null) {
          statement.bindNull(11);
        } else {
          statement.bindString(11, entity.tripulacionIngreso);
        }
        if (entity.cierrePuerta == null) {
          statement.bindNull(12);
        } else {
          statement.bindString(12, entity.cierrePuerta);
        }
        if (entity.createdAt == null) {
          statement.bindNull(13);
        } else {
          statement.bindString(13, entity.createdAt);
        }
        if (entity.updatedAt == null) {
          statement.bindNull(14);
        } else {
          statement.bindString(14, entity.updatedAt);
        }
        statement.bindLong(15, entity.id);
      }
    };
  }

  @Override
  public long upsert(final TiemposOperativos t) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      final long _result = __insertionAdapterOfTiemposOperativos.insertAndReturnId(t);
      __db.setTransactionSuccessful();
      return _result;
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public long insert(final TiemposOperativos t) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      final long _result = __insertionAdapterOfTiemposOperativos_1.insertAndReturnId(t);
      __db.setTransactionSuccessful();
      return _result;
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public int update(final TiemposOperativos t) {
    __db.assertNotSuspendingTransaction();
    int _total = 0;
    __db.beginTransaction();
    try {
      _total += __updateAdapterOfTiemposOperativos.handle(t);
      __db.setTransactionSuccessful();
      return _total;
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public TiemposOperativos byVuelo(final long vueloId) {
    final String _sql = "SELECT * FROM control_aeronave_tiempos_operativos WHERE vuelo_id=? LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, vueloId);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfVueloId = CursorUtil.getColumnIndexOrThrow(_cursor, "vuelo_id");
      final int _cursorIndexOfDesabordajeInicio = CursorUtil.getColumnIndexOrThrow(_cursor, "desabordaje_inicio");
      final int _cursorIndexOfDesabordajeFin = CursorUtil.getColumnIndexOrThrow(_cursor, "desabordaje_fin");
      final int _cursorIndexOfAbordajeInicio = CursorUtil.getColumnIndexOrThrow(_cursor, "abordaje_inicio");
      final int _cursorIndexOfAbordajeFin = CursorUtil.getColumnIndexOrThrow(_cursor, "abordaje_fin");
      final int _cursorIndexOfInspeccionCabinaInicio = CursorUtil.getColumnIndexOrThrow(_cursor, "inspeccion_cabina_inicio");
      final int _cursorIndexOfInspeccionCabinaFin = CursorUtil.getColumnIndexOrThrow(_cursor, "inspeccion_cabina_fin");
      final int _cursorIndexOfAseoIngreso = CursorUtil.getColumnIndexOrThrow(_cursor, "aseo_ingreso");
      final int _cursorIndexOfAseoSalida = CursorUtil.getColumnIndexOrThrow(_cursor, "aseo_salida");
      final int _cursorIndexOfTripulacionIngreso = CursorUtil.getColumnIndexOrThrow(_cursor, "tripulacion_ingreso");
      final int _cursorIndexOfCierrePuerta = CursorUtil.getColumnIndexOrThrow(_cursor, "cierre_puerta");
      final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "created_at");
      final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updated_at");
      final TiemposOperativos _result;
      if (_cursor.moveToFirst()) {
        _result = new TiemposOperativos();
        _result.id = _cursor.getLong(_cursorIndexOfId);
        _result.vueloId = _cursor.getLong(_cursorIndexOfVueloId);
        if (_cursor.isNull(_cursorIndexOfDesabordajeInicio)) {
          _result.desabordajeInicio = null;
        } else {
          _result.desabordajeInicio = _cursor.getString(_cursorIndexOfDesabordajeInicio);
        }
        if (_cursor.isNull(_cursorIndexOfDesabordajeFin)) {
          _result.desabordajeFin = null;
        } else {
          _result.desabordajeFin = _cursor.getString(_cursorIndexOfDesabordajeFin);
        }
        if (_cursor.isNull(_cursorIndexOfAbordajeInicio)) {
          _result.abordajeInicio = null;
        } else {
          _result.abordajeInicio = _cursor.getString(_cursorIndexOfAbordajeInicio);
        }
        if (_cursor.isNull(_cursorIndexOfAbordajeFin)) {
          _result.abordajeFin = null;
        } else {
          _result.abordajeFin = _cursor.getString(_cursorIndexOfAbordajeFin);
        }
        if (_cursor.isNull(_cursorIndexOfInspeccionCabinaInicio)) {
          _result.inspeccionCabinaInicio = null;
        } else {
          _result.inspeccionCabinaInicio = _cursor.getString(_cursorIndexOfInspeccionCabinaInicio);
        }
        if (_cursor.isNull(_cursorIndexOfInspeccionCabinaFin)) {
          _result.inspeccionCabinaFin = null;
        } else {
          _result.inspeccionCabinaFin = _cursor.getString(_cursorIndexOfInspeccionCabinaFin);
        }
        if (_cursor.isNull(_cursorIndexOfAseoIngreso)) {
          _result.aseoIngreso = null;
        } else {
          _result.aseoIngreso = _cursor.getString(_cursorIndexOfAseoIngreso);
        }
        if (_cursor.isNull(_cursorIndexOfAseoSalida)) {
          _result.aseoSalida = null;
        } else {
          _result.aseoSalida = _cursor.getString(_cursorIndexOfAseoSalida);
        }
        if (_cursor.isNull(_cursorIndexOfTripulacionIngreso)) {
          _result.tripulacionIngreso = null;
        } else {
          _result.tripulacionIngreso = _cursor.getString(_cursorIndexOfTripulacionIngreso);
        }
        if (_cursor.isNull(_cursorIndexOfCierrePuerta)) {
          _result.cierrePuerta = null;
        } else {
          _result.cierrePuerta = _cursor.getString(_cursorIndexOfCierrePuerta);
        }
        if (_cursor.isNull(_cursorIndexOfCreatedAt)) {
          _result.createdAt = null;
        } else {
          _result.createdAt = _cursor.getString(_cursorIndexOfCreatedAt);
        }
        if (_cursor.isNull(_cursorIndexOfUpdatedAt)) {
          _result.updatedAt = null;
        } else {
          _result.updatedAt = _cursor.getString(_cursorIndexOfUpdatedAt);
        }
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
