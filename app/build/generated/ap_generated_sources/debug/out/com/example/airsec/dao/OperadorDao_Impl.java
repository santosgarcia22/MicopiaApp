package com.example.airsec.dao;

import android.database.Cursor;
import androidx.annotation.NonNull;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.example.airsec.model.Operador;
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
public final class OperadorDao_Impl implements OperadorDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Operador> __insertionAdapterOfOperador;

  public OperadorDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfOperador = new EntityInsertionAdapter<Operador>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `control_aeronave_operadores` (`id`,`codigo`,`nombre`,`created_at`,`updated_at`) VALUES (nullif(?, 0),?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement, final Operador entity) {
        statement.bindLong(1, entity.id);
        if (entity.codigo == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.codigo);
        }
        if (entity.nombre == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.nombre);
        }
        if (entity.createdAt == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.createdAt);
        }
        if (entity.updatedAt == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.updatedAt);
        }
      }
    };
  }

  @Override
  public long upsert(final Operador o) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      final long _result = __insertionAdapterOfOperador.insertAndReturnId(o);
      __db.setTransactionSuccessful();
      return _result;
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public List<Operador> all() {
    final String _sql = "SELECT * FROM control_aeronave_operadores ORDER BY nombre ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfCodigo = CursorUtil.getColumnIndexOrThrow(_cursor, "codigo");
      final int _cursorIndexOfNombre = CursorUtil.getColumnIndexOrThrow(_cursor, "nombre");
      final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "created_at");
      final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updated_at");
      final List<Operador> _result = new ArrayList<Operador>(_cursor.getCount());
      while (_cursor.moveToNext()) {
        final Operador _item;
        _item = new Operador();
        _item.id = _cursor.getLong(_cursorIndexOfId);
        if (_cursor.isNull(_cursorIndexOfCodigo)) {
          _item.codigo = null;
        } else {
          _item.codigo = _cursor.getString(_cursorIndexOfCodigo);
        }
        if (_cursor.isNull(_cursorIndexOfNombre)) {
          _item.nombre = null;
        } else {
          _item.nombre = _cursor.getString(_cursorIndexOfNombre);
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

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
