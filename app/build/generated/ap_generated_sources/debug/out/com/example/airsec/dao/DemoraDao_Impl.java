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
import com.example.airsec.model.Demora;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.Collections;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class DemoraDao_Impl implements DemoraDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Demora> __insertionAdapterOfDemora;

  private final EntityDeletionOrUpdateAdapter<Demora> __updateAdapterOfDemora;

  private final SharedSQLiteStatement __preparedStmtOfDeleteByVuelo;

  public DemoraDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfDemora = new EntityInsertionAdapter<Demora>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `control_aeronave_demoras` (`id`,`vuelo_id`,`motivo`,`minutos`,`agente_id`,`created_at`,`updated_at`) VALUES (nullif(?, 0),?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement, final Demora entity) {
        statement.bindLong(1, entity.id);
        statement.bindLong(2, entity.vueloId);
        if (entity.motivo == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.motivo);
        }
        if (entity.minutos == null) {
          statement.bindNull(4);
        } else {
          statement.bindLong(4, entity.minutos);
        }
        if (entity.agenteId == null) {
          statement.bindNull(5);
        } else {
          statement.bindLong(5, entity.agenteId);
        }
        if (entity.createdAt == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.createdAt);
        }
        if (entity.updatedAt == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.updatedAt);
        }
      }
    };
    this.__updateAdapterOfDemora = new EntityDeletionOrUpdateAdapter<Demora>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `control_aeronave_demoras` SET `id` = ?,`vuelo_id` = ?,`motivo` = ?,`minutos` = ?,`agente_id` = ?,`created_at` = ?,`updated_at` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement, final Demora entity) {
        statement.bindLong(1, entity.id);
        statement.bindLong(2, entity.vueloId);
        if (entity.motivo == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.motivo);
        }
        if (entity.minutos == null) {
          statement.bindNull(4);
        } else {
          statement.bindLong(4, entity.minutos);
        }
        if (entity.agenteId == null) {
          statement.bindNull(5);
        } else {
          statement.bindLong(5, entity.agenteId);
        }
        if (entity.createdAt == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.createdAt);
        }
        if (entity.updatedAt == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.updatedAt);
        }
        statement.bindLong(8, entity.id);
      }
    };
    this.__preparedStmtOfDeleteByVuelo = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM control_aeronave_demoras WHERE vuelo_id=?";
        return _query;
      }
    };
  }

  @Override
  public long upsert(final Demora d) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      final long _result = __insertionAdapterOfDemora.insertAndReturnId(d);
      __db.setTransactionSuccessful();
      return _result;
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public int update(final Demora d) {
    __db.assertNotSuspendingTransaction();
    int _total = 0;
    __db.beginTransaction();
    try {
      _total += __updateAdapterOfDemora.handle(d);
      __db.setTransactionSuccessful();
      return _total;
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public int deleteByVuelo(final long vueloId) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteByVuelo.acquire();
    int _argIndex = 1;
    _stmt.bindLong(_argIndex, vueloId);
    try {
      __db.beginTransaction();
      try {
        final int _result = _stmt.executeUpdateDelete();
        __db.setTransactionSuccessful();
        return _result;
      } finally {
        __db.endTransaction();
      }
    } finally {
      __preparedStmtOfDeleteByVuelo.release(_stmt);
    }
  }

  @Override
  public Demora byVuelo(final long vueloId) {
    final String _sql = "SELECT * FROM control_aeronave_demoras WHERE vuelo_id=? LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, vueloId);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfVueloId = CursorUtil.getColumnIndexOrThrow(_cursor, "vuelo_id");
      final int _cursorIndexOfMotivo = CursorUtil.getColumnIndexOrThrow(_cursor, "motivo");
      final int _cursorIndexOfMinutos = CursorUtil.getColumnIndexOrThrow(_cursor, "minutos");
      final int _cursorIndexOfAgenteId = CursorUtil.getColumnIndexOrThrow(_cursor, "agente_id");
      final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "created_at");
      final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updated_at");
      final Demora _result;
      if (_cursor.moveToFirst()) {
        _result = new Demora();
        _result.id = _cursor.getLong(_cursorIndexOfId);
        _result.vueloId = _cursor.getLong(_cursorIndexOfVueloId);
        if (_cursor.isNull(_cursorIndexOfMotivo)) {
          _result.motivo = null;
        } else {
          _result.motivo = _cursor.getString(_cursorIndexOfMotivo);
        }
        if (_cursor.isNull(_cursorIndexOfMinutos)) {
          _result.minutos = null;
        } else {
          _result.minutos = _cursor.getInt(_cursorIndexOfMinutos);
        }
        if (_cursor.isNull(_cursorIndexOfAgenteId)) {
          _result.agenteId = null;
        } else {
          _result.agenteId = _cursor.getLong(_cursorIndexOfAgenteId);
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
