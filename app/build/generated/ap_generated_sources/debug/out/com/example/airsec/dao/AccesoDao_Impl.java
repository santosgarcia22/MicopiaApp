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
import com.example.airsec.model.Acceso;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class AccesoDao_Impl implements AccesoDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Acceso> __insertionAdapterOfAcceso;

  private final EntityDeletionOrUpdateAdapter<Acceso> __deletionAdapterOfAcceso;

  private final EntityDeletionOrUpdateAdapter<Acceso> __updateAdapterOfAcceso;

  public AccesoDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfAcceso = new EntityInsertionAdapter<Acceso>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR ABORT INTO `control_aeronave_accesos` (`id`,`vuelo_id`,`nombre`,`identificacion`,`empresa`,`herramientas`,`motivo_entrada`,`hora_entrada`,`hora_salida`,`hora_entrada1`,`hora_salida2`,`firma_path`,`created_at`,`updated_at`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement, final Acceso entity) {
        statement.bindLong(1, entity.id);
        statement.bindLong(2, entity.vueloId);
        if (entity.nombre == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.nombre);
        }
        if (entity.identificacion == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.identificacion);
        }
        if (entity.empresa == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.empresa);
        }
        if (entity.herramientas == null) {
          statement.bindNull(6);
        } else {
          statement.bindLong(6, entity.herramientas);
        }
        if (entity.motivoEntrada == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.motivoEntrada);
        }
        if (entity.horaEntrada == null) {
          statement.bindNull(8);
        } else {
          statement.bindString(8, entity.horaEntrada);
        }
        if (entity.horaSalida == null) {
          statement.bindNull(9);
        } else {
          statement.bindString(9, entity.horaSalida);
        }
        if (entity.horaEntrada1 == null) {
          statement.bindNull(10);
        } else {
          statement.bindString(10, entity.horaEntrada1);
        }
        if (entity.horaSalida2 == null) {
          statement.bindNull(11);
        } else {
          statement.bindString(11, entity.horaSalida2);
        }
        if (entity.firmaPath == null) {
          statement.bindNull(12);
        } else {
          statement.bindString(12, entity.firmaPath);
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
    this.__deletionAdapterOfAcceso = new EntityDeletionOrUpdateAdapter<Acceso>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `control_aeronave_accesos` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement, final Acceso entity) {
        statement.bindLong(1, entity.id);
      }
    };
    this.__updateAdapterOfAcceso = new EntityDeletionOrUpdateAdapter<Acceso>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `control_aeronave_accesos` SET `id` = ?,`vuelo_id` = ?,`nombre` = ?,`identificacion` = ?,`empresa` = ?,`herramientas` = ?,`motivo_entrada` = ?,`hora_entrada` = ?,`hora_salida` = ?,`hora_entrada1` = ?,`hora_salida2` = ?,`firma_path` = ?,`created_at` = ?,`updated_at` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement, final Acceso entity) {
        statement.bindLong(1, entity.id);
        statement.bindLong(2, entity.vueloId);
        if (entity.nombre == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.nombre);
        }
        if (entity.identificacion == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.identificacion);
        }
        if (entity.empresa == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.empresa);
        }
        if (entity.herramientas == null) {
          statement.bindNull(6);
        } else {
          statement.bindLong(6, entity.herramientas);
        }
        if (entity.motivoEntrada == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.motivoEntrada);
        }
        if (entity.horaEntrada == null) {
          statement.bindNull(8);
        } else {
          statement.bindString(8, entity.horaEntrada);
        }
        if (entity.horaSalida == null) {
          statement.bindNull(9);
        } else {
          statement.bindString(9, entity.horaSalida);
        }
        if (entity.horaEntrada1 == null) {
          statement.bindNull(10);
        } else {
          statement.bindString(10, entity.horaEntrada1);
        }
        if (entity.horaSalida2 == null) {
          statement.bindNull(11);
        } else {
          statement.bindString(11, entity.horaSalida2);
        }
        if (entity.firmaPath == null) {
          statement.bindNull(12);
        } else {
          statement.bindString(12, entity.firmaPath);
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
  public long insert(final Acceso a) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      final long _result = __insertionAdapterOfAcceso.insertAndReturnId(a);
      __db.setTransactionSuccessful();
      return _result;
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(final Acceso a) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfAcceso.handle(a);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public int update(final Acceso a) {
    __db.assertNotSuspendingTransaction();
    int _total = 0;
    __db.beginTransaction();
    try {
      _total += __updateAdapterOfAcceso.handle(a);
      __db.setTransactionSuccessful();
      return _total;
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public List<Acceso> byVuelo(final long vueloId) {
    final String _sql = "SELECT * FROM control_aeronave_accesos WHERE vuelo_id=? ORDER BY nombre ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, vueloId);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfVueloId = CursorUtil.getColumnIndexOrThrow(_cursor, "vuelo_id");
      final int _cursorIndexOfNombre = CursorUtil.getColumnIndexOrThrow(_cursor, "nombre");
      final int _cursorIndexOfIdentificacion = CursorUtil.getColumnIndexOrThrow(_cursor, "identificacion");
      final int _cursorIndexOfEmpresa = CursorUtil.getColumnIndexOrThrow(_cursor, "empresa");
      final int _cursorIndexOfHerramientas = CursorUtil.getColumnIndexOrThrow(_cursor, "herramientas");
      final int _cursorIndexOfMotivoEntrada = CursorUtil.getColumnIndexOrThrow(_cursor, "motivo_entrada");
      final int _cursorIndexOfHoraEntrada = CursorUtil.getColumnIndexOrThrow(_cursor, "hora_entrada");
      final int _cursorIndexOfHoraSalida = CursorUtil.getColumnIndexOrThrow(_cursor, "hora_salida");
      final int _cursorIndexOfHoraEntrada1 = CursorUtil.getColumnIndexOrThrow(_cursor, "hora_entrada1");
      final int _cursorIndexOfHoraSalida2 = CursorUtil.getColumnIndexOrThrow(_cursor, "hora_salida2");
      final int _cursorIndexOfFirmaPath = CursorUtil.getColumnIndexOrThrow(_cursor, "firma_path");
      final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "created_at");
      final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updated_at");
      final List<Acceso> _result = new ArrayList<Acceso>(_cursor.getCount());
      while (_cursor.moveToNext()) {
        final Acceso _item;
        _item = new Acceso();
        _item.id = _cursor.getLong(_cursorIndexOfId);
        _item.vueloId = _cursor.getLong(_cursorIndexOfVueloId);
        if (_cursor.isNull(_cursorIndexOfNombre)) {
          _item.nombre = null;
        } else {
          _item.nombre = _cursor.getString(_cursorIndexOfNombre);
        }
        if (_cursor.isNull(_cursorIndexOfIdentificacion)) {
          _item.identificacion = null;
        } else {
          _item.identificacion = _cursor.getString(_cursorIndexOfIdentificacion);
        }
        if (_cursor.isNull(_cursorIndexOfEmpresa)) {
          _item.empresa = null;
        } else {
          _item.empresa = _cursor.getString(_cursorIndexOfEmpresa);
        }
        if (_cursor.isNull(_cursorIndexOfHerramientas)) {
          _item.herramientas = null;
        } else {
          _item.herramientas = (byte) (_cursor.getShort(_cursorIndexOfHerramientas));
        }
        if (_cursor.isNull(_cursorIndexOfMotivoEntrada)) {
          _item.motivoEntrada = null;
        } else {
          _item.motivoEntrada = _cursor.getString(_cursorIndexOfMotivoEntrada);
        }
        if (_cursor.isNull(_cursorIndexOfHoraEntrada)) {
          _item.horaEntrada = null;
        } else {
          _item.horaEntrada = _cursor.getString(_cursorIndexOfHoraEntrada);
        }
        if (_cursor.isNull(_cursorIndexOfHoraSalida)) {
          _item.horaSalida = null;
        } else {
          _item.horaSalida = _cursor.getString(_cursorIndexOfHoraSalida);
        }
        if (_cursor.isNull(_cursorIndexOfHoraEntrada1)) {
          _item.horaEntrada1 = null;
        } else {
          _item.horaEntrada1 = _cursor.getString(_cursorIndexOfHoraEntrada1);
        }
        if (_cursor.isNull(_cursorIndexOfHoraSalida2)) {
          _item.horaSalida2 = null;
        } else {
          _item.horaSalida2 = _cursor.getString(_cursorIndexOfHoraSalida2);
        }
        if (_cursor.isNull(_cursorIndexOfFirmaPath)) {
          _item.firmaPath = null;
        } else {
          _item.firmaPath = _cursor.getString(_cursorIndexOfFirmaPath);
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
  public Acceso findByDoc(final long vueloId, final String doc) {
    final String _sql = "SELECT * FROM control_aeronave_accesos WHERE vuelo_id=? AND identificacion=? LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, vueloId);
    _argIndex = 2;
    if (doc == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, doc);
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfVueloId = CursorUtil.getColumnIndexOrThrow(_cursor, "vuelo_id");
      final int _cursorIndexOfNombre = CursorUtil.getColumnIndexOrThrow(_cursor, "nombre");
      final int _cursorIndexOfIdentificacion = CursorUtil.getColumnIndexOrThrow(_cursor, "identificacion");
      final int _cursorIndexOfEmpresa = CursorUtil.getColumnIndexOrThrow(_cursor, "empresa");
      final int _cursorIndexOfHerramientas = CursorUtil.getColumnIndexOrThrow(_cursor, "herramientas");
      final int _cursorIndexOfMotivoEntrada = CursorUtil.getColumnIndexOrThrow(_cursor, "motivo_entrada");
      final int _cursorIndexOfHoraEntrada = CursorUtil.getColumnIndexOrThrow(_cursor, "hora_entrada");
      final int _cursorIndexOfHoraSalida = CursorUtil.getColumnIndexOrThrow(_cursor, "hora_salida");
      final int _cursorIndexOfHoraEntrada1 = CursorUtil.getColumnIndexOrThrow(_cursor, "hora_entrada1");
      final int _cursorIndexOfHoraSalida2 = CursorUtil.getColumnIndexOrThrow(_cursor, "hora_salida2");
      final int _cursorIndexOfFirmaPath = CursorUtil.getColumnIndexOrThrow(_cursor, "firma_path");
      final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "created_at");
      final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updated_at");
      final Acceso _result;
      if (_cursor.moveToFirst()) {
        _result = new Acceso();
        _result.id = _cursor.getLong(_cursorIndexOfId);
        _result.vueloId = _cursor.getLong(_cursorIndexOfVueloId);
        if (_cursor.isNull(_cursorIndexOfNombre)) {
          _result.nombre = null;
        } else {
          _result.nombre = _cursor.getString(_cursorIndexOfNombre);
        }
        if (_cursor.isNull(_cursorIndexOfIdentificacion)) {
          _result.identificacion = null;
        } else {
          _result.identificacion = _cursor.getString(_cursorIndexOfIdentificacion);
        }
        if (_cursor.isNull(_cursorIndexOfEmpresa)) {
          _result.empresa = null;
        } else {
          _result.empresa = _cursor.getString(_cursorIndexOfEmpresa);
        }
        if (_cursor.isNull(_cursorIndexOfHerramientas)) {
          _result.herramientas = null;
        } else {
          _result.herramientas = (byte) (_cursor.getShort(_cursorIndexOfHerramientas));
        }
        if (_cursor.isNull(_cursorIndexOfMotivoEntrada)) {
          _result.motivoEntrada = null;
        } else {
          _result.motivoEntrada = _cursor.getString(_cursorIndexOfMotivoEntrada);
        }
        if (_cursor.isNull(_cursorIndexOfHoraEntrada)) {
          _result.horaEntrada = null;
        } else {
          _result.horaEntrada = _cursor.getString(_cursorIndexOfHoraEntrada);
        }
        if (_cursor.isNull(_cursorIndexOfHoraSalida)) {
          _result.horaSalida = null;
        } else {
          _result.horaSalida = _cursor.getString(_cursorIndexOfHoraSalida);
        }
        if (_cursor.isNull(_cursorIndexOfHoraEntrada1)) {
          _result.horaEntrada1 = null;
        } else {
          _result.horaEntrada1 = _cursor.getString(_cursorIndexOfHoraEntrada1);
        }
        if (_cursor.isNull(_cursorIndexOfHoraSalida2)) {
          _result.horaSalida2 = null;
        } else {
          _result.horaSalida2 = _cursor.getString(_cursorIndexOfHoraSalida2);
        }
        if (_cursor.isNull(_cursorIndexOfFirmaPath)) {
          _result.firmaPath = null;
        } else {
          _result.firmaPath = _cursor.getString(_cursorIndexOfFirmaPath);
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
  public Acceso byVueloAndDoc(final long vueloId, final String doc) {
    final String _sql = "SELECT * FROM control_aeronave_accesos WHERE vuelo_id=? AND identificacion=? LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, vueloId);
    _argIndex = 2;
    if (doc == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, doc);
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfVueloId = CursorUtil.getColumnIndexOrThrow(_cursor, "vuelo_id");
      final int _cursorIndexOfNombre = CursorUtil.getColumnIndexOrThrow(_cursor, "nombre");
      final int _cursorIndexOfIdentificacion = CursorUtil.getColumnIndexOrThrow(_cursor, "identificacion");
      final int _cursorIndexOfEmpresa = CursorUtil.getColumnIndexOrThrow(_cursor, "empresa");
      final int _cursorIndexOfHerramientas = CursorUtil.getColumnIndexOrThrow(_cursor, "herramientas");
      final int _cursorIndexOfMotivoEntrada = CursorUtil.getColumnIndexOrThrow(_cursor, "motivo_entrada");
      final int _cursorIndexOfHoraEntrada = CursorUtil.getColumnIndexOrThrow(_cursor, "hora_entrada");
      final int _cursorIndexOfHoraSalida = CursorUtil.getColumnIndexOrThrow(_cursor, "hora_salida");
      final int _cursorIndexOfHoraEntrada1 = CursorUtil.getColumnIndexOrThrow(_cursor, "hora_entrada1");
      final int _cursorIndexOfHoraSalida2 = CursorUtil.getColumnIndexOrThrow(_cursor, "hora_salida2");
      final int _cursorIndexOfFirmaPath = CursorUtil.getColumnIndexOrThrow(_cursor, "firma_path");
      final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "created_at");
      final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updated_at");
      final Acceso _result;
      if (_cursor.moveToFirst()) {
        _result = new Acceso();
        _result.id = _cursor.getLong(_cursorIndexOfId);
        _result.vueloId = _cursor.getLong(_cursorIndexOfVueloId);
        if (_cursor.isNull(_cursorIndexOfNombre)) {
          _result.nombre = null;
        } else {
          _result.nombre = _cursor.getString(_cursorIndexOfNombre);
        }
        if (_cursor.isNull(_cursorIndexOfIdentificacion)) {
          _result.identificacion = null;
        } else {
          _result.identificacion = _cursor.getString(_cursorIndexOfIdentificacion);
        }
        if (_cursor.isNull(_cursorIndexOfEmpresa)) {
          _result.empresa = null;
        } else {
          _result.empresa = _cursor.getString(_cursorIndexOfEmpresa);
        }
        if (_cursor.isNull(_cursorIndexOfHerramientas)) {
          _result.herramientas = null;
        } else {
          _result.herramientas = (byte) (_cursor.getShort(_cursorIndexOfHerramientas));
        }
        if (_cursor.isNull(_cursorIndexOfMotivoEntrada)) {
          _result.motivoEntrada = null;
        } else {
          _result.motivoEntrada = _cursor.getString(_cursorIndexOfMotivoEntrada);
        }
        if (_cursor.isNull(_cursorIndexOfHoraEntrada)) {
          _result.horaEntrada = null;
        } else {
          _result.horaEntrada = _cursor.getString(_cursorIndexOfHoraEntrada);
        }
        if (_cursor.isNull(_cursorIndexOfHoraSalida)) {
          _result.horaSalida = null;
        } else {
          _result.horaSalida = _cursor.getString(_cursorIndexOfHoraSalida);
        }
        if (_cursor.isNull(_cursorIndexOfHoraEntrada1)) {
          _result.horaEntrada1 = null;
        } else {
          _result.horaEntrada1 = _cursor.getString(_cursorIndexOfHoraEntrada1);
        }
        if (_cursor.isNull(_cursorIndexOfHoraSalida2)) {
          _result.horaSalida2 = null;
        } else {
          _result.horaSalida2 = _cursor.getString(_cursorIndexOfHoraSalida2);
        }
        if (_cursor.isNull(_cursorIndexOfFirmaPath)) {
          _result.firmaPath = null;
        } else {
          _result.firmaPath = _cursor.getString(_cursorIndexOfFirmaPath);
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
