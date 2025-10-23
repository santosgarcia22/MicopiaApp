package com.example.airsec.dao;

import android.database.Cursor;
import androidx.annotation.NonNull;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.example.airsec.model.Vuelo;
import java.lang.Class;
import java.lang.Long;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class VueloDao_Impl implements VueloDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Vuelo> __insertionAdapterOfVuelo;

  private final EntityDeletionOrUpdateAdapter<Vuelo> __deletionAdapterOfVuelo;

  private final EntityDeletionOrUpdateAdapter<Vuelo> __updateAdapterOfVuelo;

  private final SharedSQLiteStatement __preparedStmtOfCerrarVuelo;

  public VueloDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfVuelo = new EntityInsertionAdapter<Vuelo>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `control_aeronave_vuelos` (`id`,`fecha`,`origen`,`destino`,`numeroVueloLlegando`,`numeroVueloSaliendo`,`matricula`,`operadorId`,`posicionLlegada`,`horaLlegadaReal`,`horaSalidaItinerario`,`horaSalidaPushback`,`totalPax`,`coordinadorId`,`liderVueloId`,`app_bloqueado`,`app_cerrado`,`app_cerrado_at`,`created_by_user_id`,`created_at`,`updated_at`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement, final Vuelo entity) {
        statement.bindLong(1, entity.id);
        if (entity.fecha == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.fecha);
        }
        if (entity.origen == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.origen);
        }
        if (entity.destino == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.destino);
        }
        if (entity.numeroVueloLlegando == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.numeroVueloLlegando);
        }
        if (entity.numeroVueloSaliendo == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.numeroVueloSaliendo);
        }
        if (entity.matricula == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.matricula);
        }
        if (entity.operadorId == null) {
          statement.bindNull(8);
        } else {
          statement.bindLong(8, entity.operadorId);
        }
        if (entity.posicionLlegada == null) {
          statement.bindNull(9);
        } else {
          statement.bindString(9, entity.posicionLlegada);
        }
        if (entity.horaLlegadaReal == null) {
          statement.bindNull(10);
        } else {
          statement.bindString(10, entity.horaLlegadaReal);
        }
        if (entity.horaSalidaItinerario == null) {
          statement.bindNull(11);
        } else {
          statement.bindString(11, entity.horaSalidaItinerario);
        }
        if (entity.horaSalidaPushback == null) {
          statement.bindNull(12);
        } else {
          statement.bindString(12, entity.horaSalidaPushback);
        }
        if (entity.totalPax == null) {
          statement.bindNull(13);
        } else {
          statement.bindLong(13, entity.totalPax);
        }
        if (entity.coordinadorId == null) {
          statement.bindNull(14);
        } else {
          statement.bindLong(14, entity.coordinadorId);
        }
        if (entity.liderVueloId == null) {
          statement.bindNull(15);
        } else {
          statement.bindLong(15, entity.liderVueloId);
        }
        final int _tmp = entity.appBloqueado ? 1 : 0;
        statement.bindLong(16, _tmp);
        final int _tmp_1 = entity.appCerrado ? 1 : 0;
        statement.bindLong(17, _tmp_1);
        if (entity.appCerradoAt == null) {
          statement.bindNull(18);
        } else {
          statement.bindString(18, entity.appCerradoAt);
        }
        if (entity.createdByUserId == null) {
          statement.bindNull(19);
        } else {
          statement.bindLong(19, entity.createdByUserId);
        }
        if (entity.createdAt == null) {
          statement.bindNull(20);
        } else {
          statement.bindString(20, entity.createdAt);
        }
        if (entity.updatedAt == null) {
          statement.bindNull(21);
        } else {
          statement.bindString(21, entity.updatedAt);
        }
      }
    };
    this.__deletionAdapterOfVuelo = new EntityDeletionOrUpdateAdapter<Vuelo>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `control_aeronave_vuelos` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement, final Vuelo entity) {
        statement.bindLong(1, entity.id);
      }
    };
    this.__updateAdapterOfVuelo = new EntityDeletionOrUpdateAdapter<Vuelo>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `control_aeronave_vuelos` SET `id` = ?,`fecha` = ?,`origen` = ?,`destino` = ?,`numeroVueloLlegando` = ?,`numeroVueloSaliendo` = ?,`matricula` = ?,`operadorId` = ?,`posicionLlegada` = ?,`horaLlegadaReal` = ?,`horaSalidaItinerario` = ?,`horaSalidaPushback` = ?,`totalPax` = ?,`coordinadorId` = ?,`liderVueloId` = ?,`app_bloqueado` = ?,`app_cerrado` = ?,`app_cerrado_at` = ?,`created_by_user_id` = ?,`created_at` = ?,`updated_at` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement, final Vuelo entity) {
        statement.bindLong(1, entity.id);
        if (entity.fecha == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.fecha);
        }
        if (entity.origen == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.origen);
        }
        if (entity.destino == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.destino);
        }
        if (entity.numeroVueloLlegando == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.numeroVueloLlegando);
        }
        if (entity.numeroVueloSaliendo == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.numeroVueloSaliendo);
        }
        if (entity.matricula == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.matricula);
        }
        if (entity.operadorId == null) {
          statement.bindNull(8);
        } else {
          statement.bindLong(8, entity.operadorId);
        }
        if (entity.posicionLlegada == null) {
          statement.bindNull(9);
        } else {
          statement.bindString(9, entity.posicionLlegada);
        }
        if (entity.horaLlegadaReal == null) {
          statement.bindNull(10);
        } else {
          statement.bindString(10, entity.horaLlegadaReal);
        }
        if (entity.horaSalidaItinerario == null) {
          statement.bindNull(11);
        } else {
          statement.bindString(11, entity.horaSalidaItinerario);
        }
        if (entity.horaSalidaPushback == null) {
          statement.bindNull(12);
        } else {
          statement.bindString(12, entity.horaSalidaPushback);
        }
        if (entity.totalPax == null) {
          statement.bindNull(13);
        } else {
          statement.bindLong(13, entity.totalPax);
        }
        if (entity.coordinadorId == null) {
          statement.bindNull(14);
        } else {
          statement.bindLong(14, entity.coordinadorId);
        }
        if (entity.liderVueloId == null) {
          statement.bindNull(15);
        } else {
          statement.bindLong(15, entity.liderVueloId);
        }
        final int _tmp = entity.appBloqueado ? 1 : 0;
        statement.bindLong(16, _tmp);
        final int _tmp_1 = entity.appCerrado ? 1 : 0;
        statement.bindLong(17, _tmp_1);
        if (entity.appCerradoAt == null) {
          statement.bindNull(18);
        } else {
          statement.bindString(18, entity.appCerradoAt);
        }
        if (entity.createdByUserId == null) {
          statement.bindNull(19);
        } else {
          statement.bindLong(19, entity.createdByUserId);
        }
        if (entity.createdAt == null) {
          statement.bindNull(20);
        } else {
          statement.bindString(20, entity.createdAt);
        }
        if (entity.updatedAt == null) {
          statement.bindNull(21);
        } else {
          statement.bindString(21, entity.updatedAt);
        }
        statement.bindLong(22, entity.id);
      }
    };
    this.__preparedStmtOfCerrarVuelo = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "UPDATE control_aeronave_vuelos SET app_bloqueado=1, app_cerrado=1, updated_at=? WHERE id=?";
        return _query;
      }
    };
  }

  @Override
  public long insert(final Vuelo v) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      final long _result = __insertionAdapterOfVuelo.insertAndReturnId(v);
      __db.setTransactionSuccessful();
      return _result;
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public int delete(final Vuelo v) {
    __db.assertNotSuspendingTransaction();
    int _total = 0;
    __db.beginTransaction();
    try {
      _total += __deletionAdapterOfVuelo.handle(v);
      __db.setTransactionSuccessful();
      return _total;
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public int update(final Vuelo v) {
    __db.assertNotSuspendingTransaction();
    int _total = 0;
    __db.beginTransaction();
    try {
      _total += __updateAdapterOfVuelo.handle(v);
      __db.setTransactionSuccessful();
      return _total;
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void cerrarVuelo(final long id, final String ts) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfCerrarVuelo.acquire();
    int _argIndex = 1;
    if (ts == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, ts);
    }
    _argIndex = 2;
    _stmt.bindLong(_argIndex, id);
    try {
      __db.beginTransaction();
      try {
        _stmt.executeUpdateDelete();
        __db.setTransactionSuccessful();
      } finally {
        __db.endTransaction();
      }
    } finally {
      __preparedStmtOfCerrarVuelo.release(_stmt);
    }
  }

  @Override
  public Vuelo get(final long id) {
    final String _sql = "SELECT * FROM control_aeronave_vuelos WHERE id=? LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfFecha = CursorUtil.getColumnIndexOrThrow(_cursor, "fecha");
      final int _cursorIndexOfOrigen = CursorUtil.getColumnIndexOrThrow(_cursor, "origen");
      final int _cursorIndexOfDestino = CursorUtil.getColumnIndexOrThrow(_cursor, "destino");
      final int _cursorIndexOfNumeroVueloLlegando = CursorUtil.getColumnIndexOrThrow(_cursor, "numeroVueloLlegando");
      final int _cursorIndexOfNumeroVueloSaliendo = CursorUtil.getColumnIndexOrThrow(_cursor, "numeroVueloSaliendo");
      final int _cursorIndexOfMatricula = CursorUtil.getColumnIndexOrThrow(_cursor, "matricula");
      final int _cursorIndexOfOperadorId = CursorUtil.getColumnIndexOrThrow(_cursor, "operadorId");
      final int _cursorIndexOfPosicionLlegada = CursorUtil.getColumnIndexOrThrow(_cursor, "posicionLlegada");
      final int _cursorIndexOfHoraLlegadaReal = CursorUtil.getColumnIndexOrThrow(_cursor, "horaLlegadaReal");
      final int _cursorIndexOfHoraSalidaItinerario = CursorUtil.getColumnIndexOrThrow(_cursor, "horaSalidaItinerario");
      final int _cursorIndexOfHoraSalidaPushback = CursorUtil.getColumnIndexOrThrow(_cursor, "horaSalidaPushback");
      final int _cursorIndexOfTotalPax = CursorUtil.getColumnIndexOrThrow(_cursor, "totalPax");
      final int _cursorIndexOfCoordinadorId = CursorUtil.getColumnIndexOrThrow(_cursor, "coordinadorId");
      final int _cursorIndexOfLiderVueloId = CursorUtil.getColumnIndexOrThrow(_cursor, "liderVueloId");
      final int _cursorIndexOfAppBloqueado = CursorUtil.getColumnIndexOrThrow(_cursor, "app_bloqueado");
      final int _cursorIndexOfAppCerrado = CursorUtil.getColumnIndexOrThrow(_cursor, "app_cerrado");
      final int _cursorIndexOfAppCerradoAt = CursorUtil.getColumnIndexOrThrow(_cursor, "app_cerrado_at");
      final int _cursorIndexOfCreatedByUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "created_by_user_id");
      final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "created_at");
      final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updated_at");
      final Vuelo _result;
      if (_cursor.moveToFirst()) {
        _result = new Vuelo();
        _result.id = _cursor.getLong(_cursorIndexOfId);
        if (_cursor.isNull(_cursorIndexOfFecha)) {
          _result.fecha = null;
        } else {
          _result.fecha = _cursor.getString(_cursorIndexOfFecha);
        }
        if (_cursor.isNull(_cursorIndexOfOrigen)) {
          _result.origen = null;
        } else {
          _result.origen = _cursor.getString(_cursorIndexOfOrigen);
        }
        if (_cursor.isNull(_cursorIndexOfDestino)) {
          _result.destino = null;
        } else {
          _result.destino = _cursor.getString(_cursorIndexOfDestino);
        }
        if (_cursor.isNull(_cursorIndexOfNumeroVueloLlegando)) {
          _result.numeroVueloLlegando = null;
        } else {
          _result.numeroVueloLlegando = _cursor.getString(_cursorIndexOfNumeroVueloLlegando);
        }
        if (_cursor.isNull(_cursorIndexOfNumeroVueloSaliendo)) {
          _result.numeroVueloSaliendo = null;
        } else {
          _result.numeroVueloSaliendo = _cursor.getString(_cursorIndexOfNumeroVueloSaliendo);
        }
        if (_cursor.isNull(_cursorIndexOfMatricula)) {
          _result.matricula = null;
        } else {
          _result.matricula = _cursor.getString(_cursorIndexOfMatricula);
        }
        if (_cursor.isNull(_cursorIndexOfOperadorId)) {
          _result.operadorId = null;
        } else {
          _result.operadorId = _cursor.getLong(_cursorIndexOfOperadorId);
        }
        if (_cursor.isNull(_cursorIndexOfPosicionLlegada)) {
          _result.posicionLlegada = null;
        } else {
          _result.posicionLlegada = _cursor.getString(_cursorIndexOfPosicionLlegada);
        }
        if (_cursor.isNull(_cursorIndexOfHoraLlegadaReal)) {
          _result.horaLlegadaReal = null;
        } else {
          _result.horaLlegadaReal = _cursor.getString(_cursorIndexOfHoraLlegadaReal);
        }
        if (_cursor.isNull(_cursorIndexOfHoraSalidaItinerario)) {
          _result.horaSalidaItinerario = null;
        } else {
          _result.horaSalidaItinerario = _cursor.getString(_cursorIndexOfHoraSalidaItinerario);
        }
        if (_cursor.isNull(_cursorIndexOfHoraSalidaPushback)) {
          _result.horaSalidaPushback = null;
        } else {
          _result.horaSalidaPushback = _cursor.getString(_cursorIndexOfHoraSalidaPushback);
        }
        if (_cursor.isNull(_cursorIndexOfTotalPax)) {
          _result.totalPax = null;
        } else {
          _result.totalPax = _cursor.getInt(_cursorIndexOfTotalPax);
        }
        if (_cursor.isNull(_cursorIndexOfCoordinadorId)) {
          _result.coordinadorId = null;
        } else {
          _result.coordinadorId = _cursor.getLong(_cursorIndexOfCoordinadorId);
        }
        if (_cursor.isNull(_cursorIndexOfLiderVueloId)) {
          _result.liderVueloId = null;
        } else {
          _result.liderVueloId = _cursor.getLong(_cursorIndexOfLiderVueloId);
        }
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfAppBloqueado);
        _result.appBloqueado = _tmp != 0;
        final int _tmp_1;
        _tmp_1 = _cursor.getInt(_cursorIndexOfAppCerrado);
        _result.appCerrado = _tmp_1 != 0;
        if (_cursor.isNull(_cursorIndexOfAppCerradoAt)) {
          _result.appCerradoAt = null;
        } else {
          _result.appCerradoAt = _cursor.getString(_cursorIndexOfAppCerradoAt);
        }
        if (_cursor.isNull(_cursorIndexOfCreatedByUserId)) {
          _result.createdByUserId = null;
        } else {
          _result.createdByUserId = _cursor.getLong(_cursorIndexOfCreatedByUserId);
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

  @Override
  public List<Vuelo> list() {
    final String _sql = "SELECT * FROM control_aeronave_vuelos ORDER BY created_at DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfFecha = CursorUtil.getColumnIndexOrThrow(_cursor, "fecha");
      final int _cursorIndexOfOrigen = CursorUtil.getColumnIndexOrThrow(_cursor, "origen");
      final int _cursorIndexOfDestino = CursorUtil.getColumnIndexOrThrow(_cursor, "destino");
      final int _cursorIndexOfNumeroVueloLlegando = CursorUtil.getColumnIndexOrThrow(_cursor, "numeroVueloLlegando");
      final int _cursorIndexOfNumeroVueloSaliendo = CursorUtil.getColumnIndexOrThrow(_cursor, "numeroVueloSaliendo");
      final int _cursorIndexOfMatricula = CursorUtil.getColumnIndexOrThrow(_cursor, "matricula");
      final int _cursorIndexOfOperadorId = CursorUtil.getColumnIndexOrThrow(_cursor, "operadorId");
      final int _cursorIndexOfPosicionLlegada = CursorUtil.getColumnIndexOrThrow(_cursor, "posicionLlegada");
      final int _cursorIndexOfHoraLlegadaReal = CursorUtil.getColumnIndexOrThrow(_cursor, "horaLlegadaReal");
      final int _cursorIndexOfHoraSalidaItinerario = CursorUtil.getColumnIndexOrThrow(_cursor, "horaSalidaItinerario");
      final int _cursorIndexOfHoraSalidaPushback = CursorUtil.getColumnIndexOrThrow(_cursor, "horaSalidaPushback");
      final int _cursorIndexOfTotalPax = CursorUtil.getColumnIndexOrThrow(_cursor, "totalPax");
      final int _cursorIndexOfCoordinadorId = CursorUtil.getColumnIndexOrThrow(_cursor, "coordinadorId");
      final int _cursorIndexOfLiderVueloId = CursorUtil.getColumnIndexOrThrow(_cursor, "liderVueloId");
      final int _cursorIndexOfAppBloqueado = CursorUtil.getColumnIndexOrThrow(_cursor, "app_bloqueado");
      final int _cursorIndexOfAppCerrado = CursorUtil.getColumnIndexOrThrow(_cursor, "app_cerrado");
      final int _cursorIndexOfAppCerradoAt = CursorUtil.getColumnIndexOrThrow(_cursor, "app_cerrado_at");
      final int _cursorIndexOfCreatedByUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "created_by_user_id");
      final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "created_at");
      final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updated_at");
      final List<Vuelo> _result = new ArrayList<Vuelo>(_cursor.getCount());
      while (_cursor.moveToNext()) {
        final Vuelo _item;
        _item = new Vuelo();
        _item.id = _cursor.getLong(_cursorIndexOfId);
        if (_cursor.isNull(_cursorIndexOfFecha)) {
          _item.fecha = null;
        } else {
          _item.fecha = _cursor.getString(_cursorIndexOfFecha);
        }
        if (_cursor.isNull(_cursorIndexOfOrigen)) {
          _item.origen = null;
        } else {
          _item.origen = _cursor.getString(_cursorIndexOfOrigen);
        }
        if (_cursor.isNull(_cursorIndexOfDestino)) {
          _item.destino = null;
        } else {
          _item.destino = _cursor.getString(_cursorIndexOfDestino);
        }
        if (_cursor.isNull(_cursorIndexOfNumeroVueloLlegando)) {
          _item.numeroVueloLlegando = null;
        } else {
          _item.numeroVueloLlegando = _cursor.getString(_cursorIndexOfNumeroVueloLlegando);
        }
        if (_cursor.isNull(_cursorIndexOfNumeroVueloSaliendo)) {
          _item.numeroVueloSaliendo = null;
        } else {
          _item.numeroVueloSaliendo = _cursor.getString(_cursorIndexOfNumeroVueloSaliendo);
        }
        if (_cursor.isNull(_cursorIndexOfMatricula)) {
          _item.matricula = null;
        } else {
          _item.matricula = _cursor.getString(_cursorIndexOfMatricula);
        }
        if (_cursor.isNull(_cursorIndexOfOperadorId)) {
          _item.operadorId = null;
        } else {
          _item.operadorId = _cursor.getLong(_cursorIndexOfOperadorId);
        }
        if (_cursor.isNull(_cursorIndexOfPosicionLlegada)) {
          _item.posicionLlegada = null;
        } else {
          _item.posicionLlegada = _cursor.getString(_cursorIndexOfPosicionLlegada);
        }
        if (_cursor.isNull(_cursorIndexOfHoraLlegadaReal)) {
          _item.horaLlegadaReal = null;
        } else {
          _item.horaLlegadaReal = _cursor.getString(_cursorIndexOfHoraLlegadaReal);
        }
        if (_cursor.isNull(_cursorIndexOfHoraSalidaItinerario)) {
          _item.horaSalidaItinerario = null;
        } else {
          _item.horaSalidaItinerario = _cursor.getString(_cursorIndexOfHoraSalidaItinerario);
        }
        if (_cursor.isNull(_cursorIndexOfHoraSalidaPushback)) {
          _item.horaSalidaPushback = null;
        } else {
          _item.horaSalidaPushback = _cursor.getString(_cursorIndexOfHoraSalidaPushback);
        }
        if (_cursor.isNull(_cursorIndexOfTotalPax)) {
          _item.totalPax = null;
        } else {
          _item.totalPax = _cursor.getInt(_cursorIndexOfTotalPax);
        }
        if (_cursor.isNull(_cursorIndexOfCoordinadorId)) {
          _item.coordinadorId = null;
        } else {
          _item.coordinadorId = _cursor.getLong(_cursorIndexOfCoordinadorId);
        }
        if (_cursor.isNull(_cursorIndexOfLiderVueloId)) {
          _item.liderVueloId = null;
        } else {
          _item.liderVueloId = _cursor.getLong(_cursorIndexOfLiderVueloId);
        }
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfAppBloqueado);
        _item.appBloqueado = _tmp != 0;
        final int _tmp_1;
        _tmp_1 = _cursor.getInt(_cursorIndexOfAppCerrado);
        _item.appCerrado = _tmp_1 != 0;
        if (_cursor.isNull(_cursorIndexOfAppCerradoAt)) {
          _item.appCerradoAt = null;
        } else {
          _item.appCerradoAt = _cursor.getString(_cursorIndexOfAppCerradoAt);
        }
        if (_cursor.isNull(_cursorIndexOfCreatedByUserId)) {
          _item.createdByUserId = null;
        } else {
          _item.createdByUserId = _cursor.getLong(_cursorIndexOfCreatedByUserId);
        }
        if (_cursor.isNull(_cursorIndexOfCreatedAt)) {
          _item.createdAt = null;
        } else {
          _item.createdAt = _cursor.getString(_cursorIndexOfCreatedAt);
        }
        if (_cursor.isNull(_cursorIndexOfUpdatedAt)) {
          _item.updatedAt = null;
        } else {
          _item.updatedAt = _cursor.getString(_cursorIndexOfUpdatedAt);
        }
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public Long lastId() {
    final String _sql = "SELECT id FROM control_aeronave_vuelos ORDER BY id DESC LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final Long _result;
      if (_cursor.moveToFirst()) {
        if (_cursor.isNull(0)) {
          _result = null;
        } else {
          _result = _cursor.getLong(0);
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

  @Override
  public List<Vuelo> search(final String q) {
    final String _sql = "SELECT * FROM control_aeronave_vuelos WHERE origen LIKE ? OR destino LIKE ? OR matricula LIKE ? ORDER BY created_at DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 3);
    int _argIndex = 1;
    if (q == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, q);
    }
    _argIndex = 2;
    if (q == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, q);
    }
    _argIndex = 3;
    if (q == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, q);
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfFecha = CursorUtil.getColumnIndexOrThrow(_cursor, "fecha");
      final int _cursorIndexOfOrigen = CursorUtil.getColumnIndexOrThrow(_cursor, "origen");
      final int _cursorIndexOfDestino = CursorUtil.getColumnIndexOrThrow(_cursor, "destino");
      final int _cursorIndexOfNumeroVueloLlegando = CursorUtil.getColumnIndexOrThrow(_cursor, "numeroVueloLlegando");
      final int _cursorIndexOfNumeroVueloSaliendo = CursorUtil.getColumnIndexOrThrow(_cursor, "numeroVueloSaliendo");
      final int _cursorIndexOfMatricula = CursorUtil.getColumnIndexOrThrow(_cursor, "matricula");
      final int _cursorIndexOfOperadorId = CursorUtil.getColumnIndexOrThrow(_cursor, "operadorId");
      final int _cursorIndexOfPosicionLlegada = CursorUtil.getColumnIndexOrThrow(_cursor, "posicionLlegada");
      final int _cursorIndexOfHoraLlegadaReal = CursorUtil.getColumnIndexOrThrow(_cursor, "horaLlegadaReal");
      final int _cursorIndexOfHoraSalidaItinerario = CursorUtil.getColumnIndexOrThrow(_cursor, "horaSalidaItinerario");
      final int _cursorIndexOfHoraSalidaPushback = CursorUtil.getColumnIndexOrThrow(_cursor, "horaSalidaPushback");
      final int _cursorIndexOfTotalPax = CursorUtil.getColumnIndexOrThrow(_cursor, "totalPax");
      final int _cursorIndexOfCoordinadorId = CursorUtil.getColumnIndexOrThrow(_cursor, "coordinadorId");
      final int _cursorIndexOfLiderVueloId = CursorUtil.getColumnIndexOrThrow(_cursor, "liderVueloId");
      final int _cursorIndexOfAppBloqueado = CursorUtil.getColumnIndexOrThrow(_cursor, "app_bloqueado");
      final int _cursorIndexOfAppCerrado = CursorUtil.getColumnIndexOrThrow(_cursor, "app_cerrado");
      final int _cursorIndexOfAppCerradoAt = CursorUtil.getColumnIndexOrThrow(_cursor, "app_cerrado_at");
      final int _cursorIndexOfCreatedByUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "created_by_user_id");
      final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "created_at");
      final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updated_at");
      final List<Vuelo> _result = new ArrayList<Vuelo>(_cursor.getCount());
      while (_cursor.moveToNext()) {
        final Vuelo _item;
        _item = new Vuelo();
        _item.id = _cursor.getLong(_cursorIndexOfId);
        if (_cursor.isNull(_cursorIndexOfFecha)) {
          _item.fecha = null;
        } else {
          _item.fecha = _cursor.getString(_cursorIndexOfFecha);
        }
        if (_cursor.isNull(_cursorIndexOfOrigen)) {
          _item.origen = null;
        } else {
          _item.origen = _cursor.getString(_cursorIndexOfOrigen);
        }
        if (_cursor.isNull(_cursorIndexOfDestino)) {
          _item.destino = null;
        } else {
          _item.destino = _cursor.getString(_cursorIndexOfDestino);
        }
        if (_cursor.isNull(_cursorIndexOfNumeroVueloLlegando)) {
          _item.numeroVueloLlegando = null;
        } else {
          _item.numeroVueloLlegando = _cursor.getString(_cursorIndexOfNumeroVueloLlegando);
        }
        if (_cursor.isNull(_cursorIndexOfNumeroVueloSaliendo)) {
          _item.numeroVueloSaliendo = null;
        } else {
          _item.numeroVueloSaliendo = _cursor.getString(_cursorIndexOfNumeroVueloSaliendo);
        }
        if (_cursor.isNull(_cursorIndexOfMatricula)) {
          _item.matricula = null;
        } else {
          _item.matricula = _cursor.getString(_cursorIndexOfMatricula);
        }
        if (_cursor.isNull(_cursorIndexOfOperadorId)) {
          _item.operadorId = null;
        } else {
          _item.operadorId = _cursor.getLong(_cursorIndexOfOperadorId);
        }
        if (_cursor.isNull(_cursorIndexOfPosicionLlegada)) {
          _item.posicionLlegada = null;
        } else {
          _item.posicionLlegada = _cursor.getString(_cursorIndexOfPosicionLlegada);
        }
        if (_cursor.isNull(_cursorIndexOfHoraLlegadaReal)) {
          _item.horaLlegadaReal = null;
        } else {
          _item.horaLlegadaReal = _cursor.getString(_cursorIndexOfHoraLlegadaReal);
        }
        if (_cursor.isNull(_cursorIndexOfHoraSalidaItinerario)) {
          _item.horaSalidaItinerario = null;
        } else {
          _item.horaSalidaItinerario = _cursor.getString(_cursorIndexOfHoraSalidaItinerario);
        }
        if (_cursor.isNull(_cursorIndexOfHoraSalidaPushback)) {
          _item.horaSalidaPushback = null;
        } else {
          _item.horaSalidaPushback = _cursor.getString(_cursorIndexOfHoraSalidaPushback);
        }
        if (_cursor.isNull(_cursorIndexOfTotalPax)) {
          _item.totalPax = null;
        } else {
          _item.totalPax = _cursor.getInt(_cursorIndexOfTotalPax);
        }
        if (_cursor.isNull(_cursorIndexOfCoordinadorId)) {
          _item.coordinadorId = null;
        } else {
          _item.coordinadorId = _cursor.getLong(_cursorIndexOfCoordinadorId);
        }
        if (_cursor.isNull(_cursorIndexOfLiderVueloId)) {
          _item.liderVueloId = null;
        } else {
          _item.liderVueloId = _cursor.getLong(_cursorIndexOfLiderVueloId);
        }
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfAppBloqueado);
        _item.appBloqueado = _tmp != 0;
        final int _tmp_1;
        _tmp_1 = _cursor.getInt(_cursorIndexOfAppCerrado);
        _item.appCerrado = _tmp_1 != 0;
        if (_cursor.isNull(_cursorIndexOfAppCerradoAt)) {
          _item.appCerradoAt = null;
        } else {
          _item.appCerradoAt = _cursor.getString(_cursorIndexOfAppCerradoAt);
        }
        if (_cursor.isNull(_cursorIndexOfCreatedByUserId)) {
          _item.createdByUserId = null;
        } else {
          _item.createdByUserId = _cursor.getLong(_cursorIndexOfCreatedByUserId);
        }
        if (_cursor.isNull(_cursorIndexOfCreatedAt)) {
          _item.createdAt = null;
        } else {
          _item.createdAt = _cursor.getString(_cursorIndexOfCreatedAt);
        }
        if (_cursor.isNull(_cursorIndexOfUpdatedAt)) {
          _item.updatedAt = null;
        } else {
          _item.updatedAt = _cursor.getString(_cursorIndexOfUpdatedAt);
        }
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public boolean isCerrado(final long id) {
    final String _sql = "SELECT app_cerrado FROM control_aeronave_vuelos WHERE id=?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final boolean _result;
      if (_cursor.moveToFirst()) {
        final int _tmp;
        _tmp = _cursor.getInt(0);
        _result = _tmp != 0;
      } else {
        _result = false;
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
