package com.lins.card.data.local;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.lins.card.data.model.Card;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Integer;
import java.lang.Long;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class CardDao_Impl implements CardDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Card> __insertionAdapterOfCard;

  private final EntityDeletionOrUpdateAdapter<Card> __deletionAdapterOfCard;

  private final EntityDeletionOrUpdateAdapter<Card> __updateAdapterOfCard;

  private final SharedSQLiteStatement __preparedStmtOfDeleteCardById;

  public CardDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfCard = new EntityInsertionAdapter<Card>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `cards` (`id`,`name`,`category`,`holderName`,`note`,`frontImagePath`,`backImagePath`,`imagePaths`,`color`,`isFavorite`,`createdAt`,`updatedAt`,`favoriteAt`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Card entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getName() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getName());
        }
        if (entity.getCategory() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getCategory());
        }
        if (entity.getHolderName() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getHolderName());
        }
        if (entity.getNote() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getNote());
        }
        if (entity.getFrontImagePath() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getFrontImagePath());
        }
        if (entity.getBackImagePath() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getBackImagePath());
        }
        if (entity.getImagePaths() == null) {
          statement.bindNull(8);
        } else {
          statement.bindString(8, entity.getImagePaths());
        }
        statement.bindLong(9, entity.getColor());
        final int _tmp = entity.isFavorite() ? 1 : 0;
        statement.bindLong(10, _tmp);
        statement.bindLong(11, entity.getCreatedAt());
        statement.bindLong(12, entity.getUpdatedAt());
        statement.bindLong(13, entity.getFavoriteAt());
      }
    };
    this.__deletionAdapterOfCard = new EntityDeletionOrUpdateAdapter<Card>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `cards` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Card entity) {
        statement.bindLong(1, entity.getId());
      }
    };
    this.__updateAdapterOfCard = new EntityDeletionOrUpdateAdapter<Card>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `cards` SET `id` = ?,`name` = ?,`category` = ?,`holderName` = ?,`note` = ?,`frontImagePath` = ?,`backImagePath` = ?,`imagePaths` = ?,`color` = ?,`isFavorite` = ?,`createdAt` = ?,`updatedAt` = ?,`favoriteAt` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Card entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getName() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getName());
        }
        if (entity.getCategory() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getCategory());
        }
        if (entity.getHolderName() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getHolderName());
        }
        if (entity.getNote() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getNote());
        }
        if (entity.getFrontImagePath() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getFrontImagePath());
        }
        if (entity.getBackImagePath() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getBackImagePath());
        }
        if (entity.getImagePaths() == null) {
          statement.bindNull(8);
        } else {
          statement.bindString(8, entity.getImagePaths());
        }
        statement.bindLong(9, entity.getColor());
        final int _tmp = entity.isFavorite() ? 1 : 0;
        statement.bindLong(10, _tmp);
        statement.bindLong(11, entity.getCreatedAt());
        statement.bindLong(12, entity.getUpdatedAt());
        statement.bindLong(13, entity.getFavoriteAt());
        statement.bindLong(14, entity.getId());
      }
    };
    this.__preparedStmtOfDeleteCardById = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM cards WHERE id = ?";
        return _query;
      }
    };
  }

  @Override
  public Object insertCard(final Card card, final Continuation<? super Long> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfCard.insertAndReturnId(card);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteCard(final Card card, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfCard.handle(card);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object updateCard(final Card card, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfCard.handle(card);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteCardById(final long id, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteCardById.acquire();
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, id);
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfDeleteCardById.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<Card>> getAllCards() {
    final String _sql = "SELECT * FROM cards ORDER BY createdAt DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"cards"}, new Callable<List<Card>>() {
      @Override
      @NonNull
      public List<Card> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfHolderName = CursorUtil.getColumnIndexOrThrow(_cursor, "holderName");
          final int _cursorIndexOfNote = CursorUtil.getColumnIndexOrThrow(_cursor, "note");
          final int _cursorIndexOfFrontImagePath = CursorUtil.getColumnIndexOrThrow(_cursor, "frontImagePath");
          final int _cursorIndexOfBackImagePath = CursorUtil.getColumnIndexOrThrow(_cursor, "backImagePath");
          final int _cursorIndexOfImagePaths = CursorUtil.getColumnIndexOrThrow(_cursor, "imagePaths");
          final int _cursorIndexOfColor = CursorUtil.getColumnIndexOrThrow(_cursor, "color");
          final int _cursorIndexOfIsFavorite = CursorUtil.getColumnIndexOrThrow(_cursor, "isFavorite");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final int _cursorIndexOfFavoriteAt = CursorUtil.getColumnIndexOrThrow(_cursor, "favoriteAt");
          final List<Card> _result = new ArrayList<Card>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Card _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final String _tmpCategory;
            if (_cursor.isNull(_cursorIndexOfCategory)) {
              _tmpCategory = null;
            } else {
              _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
            }
            final String _tmpHolderName;
            if (_cursor.isNull(_cursorIndexOfHolderName)) {
              _tmpHolderName = null;
            } else {
              _tmpHolderName = _cursor.getString(_cursorIndexOfHolderName);
            }
            final String _tmpNote;
            if (_cursor.isNull(_cursorIndexOfNote)) {
              _tmpNote = null;
            } else {
              _tmpNote = _cursor.getString(_cursorIndexOfNote);
            }
            final String _tmpFrontImagePath;
            if (_cursor.isNull(_cursorIndexOfFrontImagePath)) {
              _tmpFrontImagePath = null;
            } else {
              _tmpFrontImagePath = _cursor.getString(_cursorIndexOfFrontImagePath);
            }
            final String _tmpBackImagePath;
            if (_cursor.isNull(_cursorIndexOfBackImagePath)) {
              _tmpBackImagePath = null;
            } else {
              _tmpBackImagePath = _cursor.getString(_cursorIndexOfBackImagePath);
            }
            final String _tmpImagePaths;
            if (_cursor.isNull(_cursorIndexOfImagePaths)) {
              _tmpImagePaths = null;
            } else {
              _tmpImagePaths = _cursor.getString(_cursorIndexOfImagePaths);
            }
            final long _tmpColor;
            _tmpColor = _cursor.getLong(_cursorIndexOfColor);
            final boolean _tmpIsFavorite;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsFavorite);
            _tmpIsFavorite = _tmp != 0;
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            final long _tmpFavoriteAt;
            _tmpFavoriteAt = _cursor.getLong(_cursorIndexOfFavoriteAt);
            _item = new Card(_tmpId,_tmpName,_tmpCategory,_tmpHolderName,_tmpNote,_tmpFrontImagePath,_tmpBackImagePath,_tmpImagePaths,_tmpColor,_tmpIsFavorite,_tmpCreatedAt,_tmpUpdatedAt,_tmpFavoriteAt);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<List<Card>> getCardsByCategory(final String category) {
    final String _sql = "SELECT * FROM cards WHERE category = ? ORDER BY createdAt DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (category == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, category);
    }
    return CoroutinesRoom.createFlow(__db, false, new String[] {"cards"}, new Callable<List<Card>>() {
      @Override
      @NonNull
      public List<Card> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfHolderName = CursorUtil.getColumnIndexOrThrow(_cursor, "holderName");
          final int _cursorIndexOfNote = CursorUtil.getColumnIndexOrThrow(_cursor, "note");
          final int _cursorIndexOfFrontImagePath = CursorUtil.getColumnIndexOrThrow(_cursor, "frontImagePath");
          final int _cursorIndexOfBackImagePath = CursorUtil.getColumnIndexOrThrow(_cursor, "backImagePath");
          final int _cursorIndexOfImagePaths = CursorUtil.getColumnIndexOrThrow(_cursor, "imagePaths");
          final int _cursorIndexOfColor = CursorUtil.getColumnIndexOrThrow(_cursor, "color");
          final int _cursorIndexOfIsFavorite = CursorUtil.getColumnIndexOrThrow(_cursor, "isFavorite");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final int _cursorIndexOfFavoriteAt = CursorUtil.getColumnIndexOrThrow(_cursor, "favoriteAt");
          final List<Card> _result = new ArrayList<Card>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Card _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final String _tmpCategory;
            if (_cursor.isNull(_cursorIndexOfCategory)) {
              _tmpCategory = null;
            } else {
              _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
            }
            final String _tmpHolderName;
            if (_cursor.isNull(_cursorIndexOfHolderName)) {
              _tmpHolderName = null;
            } else {
              _tmpHolderName = _cursor.getString(_cursorIndexOfHolderName);
            }
            final String _tmpNote;
            if (_cursor.isNull(_cursorIndexOfNote)) {
              _tmpNote = null;
            } else {
              _tmpNote = _cursor.getString(_cursorIndexOfNote);
            }
            final String _tmpFrontImagePath;
            if (_cursor.isNull(_cursorIndexOfFrontImagePath)) {
              _tmpFrontImagePath = null;
            } else {
              _tmpFrontImagePath = _cursor.getString(_cursorIndexOfFrontImagePath);
            }
            final String _tmpBackImagePath;
            if (_cursor.isNull(_cursorIndexOfBackImagePath)) {
              _tmpBackImagePath = null;
            } else {
              _tmpBackImagePath = _cursor.getString(_cursorIndexOfBackImagePath);
            }
            final String _tmpImagePaths;
            if (_cursor.isNull(_cursorIndexOfImagePaths)) {
              _tmpImagePaths = null;
            } else {
              _tmpImagePaths = _cursor.getString(_cursorIndexOfImagePaths);
            }
            final long _tmpColor;
            _tmpColor = _cursor.getLong(_cursorIndexOfColor);
            final boolean _tmpIsFavorite;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsFavorite);
            _tmpIsFavorite = _tmp != 0;
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            final long _tmpFavoriteAt;
            _tmpFavoriteAt = _cursor.getLong(_cursorIndexOfFavoriteAt);
            _item = new Card(_tmpId,_tmpName,_tmpCategory,_tmpHolderName,_tmpNote,_tmpFrontImagePath,_tmpBackImagePath,_tmpImagePaths,_tmpColor,_tmpIsFavorite,_tmpCreatedAt,_tmpUpdatedAt,_tmpFavoriteAt);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<List<Card>> searchCards(final String query) {
    final String _sql = "SELECT * FROM cards WHERE name LIKE '%' || ? || '%' OR note LIKE '%' || ? || '%' ORDER BY createdAt DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    if (query == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, query);
    }
    _argIndex = 2;
    if (query == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, query);
    }
    return CoroutinesRoom.createFlow(__db, false, new String[] {"cards"}, new Callable<List<Card>>() {
      @Override
      @NonNull
      public List<Card> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfHolderName = CursorUtil.getColumnIndexOrThrow(_cursor, "holderName");
          final int _cursorIndexOfNote = CursorUtil.getColumnIndexOrThrow(_cursor, "note");
          final int _cursorIndexOfFrontImagePath = CursorUtil.getColumnIndexOrThrow(_cursor, "frontImagePath");
          final int _cursorIndexOfBackImagePath = CursorUtil.getColumnIndexOrThrow(_cursor, "backImagePath");
          final int _cursorIndexOfImagePaths = CursorUtil.getColumnIndexOrThrow(_cursor, "imagePaths");
          final int _cursorIndexOfColor = CursorUtil.getColumnIndexOrThrow(_cursor, "color");
          final int _cursorIndexOfIsFavorite = CursorUtil.getColumnIndexOrThrow(_cursor, "isFavorite");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final int _cursorIndexOfFavoriteAt = CursorUtil.getColumnIndexOrThrow(_cursor, "favoriteAt");
          final List<Card> _result = new ArrayList<Card>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Card _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final String _tmpCategory;
            if (_cursor.isNull(_cursorIndexOfCategory)) {
              _tmpCategory = null;
            } else {
              _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
            }
            final String _tmpHolderName;
            if (_cursor.isNull(_cursorIndexOfHolderName)) {
              _tmpHolderName = null;
            } else {
              _tmpHolderName = _cursor.getString(_cursorIndexOfHolderName);
            }
            final String _tmpNote;
            if (_cursor.isNull(_cursorIndexOfNote)) {
              _tmpNote = null;
            } else {
              _tmpNote = _cursor.getString(_cursorIndexOfNote);
            }
            final String _tmpFrontImagePath;
            if (_cursor.isNull(_cursorIndexOfFrontImagePath)) {
              _tmpFrontImagePath = null;
            } else {
              _tmpFrontImagePath = _cursor.getString(_cursorIndexOfFrontImagePath);
            }
            final String _tmpBackImagePath;
            if (_cursor.isNull(_cursorIndexOfBackImagePath)) {
              _tmpBackImagePath = null;
            } else {
              _tmpBackImagePath = _cursor.getString(_cursorIndexOfBackImagePath);
            }
            final String _tmpImagePaths;
            if (_cursor.isNull(_cursorIndexOfImagePaths)) {
              _tmpImagePaths = null;
            } else {
              _tmpImagePaths = _cursor.getString(_cursorIndexOfImagePaths);
            }
            final long _tmpColor;
            _tmpColor = _cursor.getLong(_cursorIndexOfColor);
            final boolean _tmpIsFavorite;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsFavorite);
            _tmpIsFavorite = _tmp != 0;
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            final long _tmpFavoriteAt;
            _tmpFavoriteAt = _cursor.getLong(_cursorIndexOfFavoriteAt);
            _item = new Card(_tmpId,_tmpName,_tmpCategory,_tmpHolderName,_tmpNote,_tmpFrontImagePath,_tmpBackImagePath,_tmpImagePaths,_tmpColor,_tmpIsFavorite,_tmpCreatedAt,_tmpUpdatedAt,_tmpFavoriteAt);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<List<Card>> getFavoriteCards() {
    final String _sql = "SELECT * FROM cards WHERE isFavorite = 1 ORDER BY favoriteAt DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"cards"}, new Callable<List<Card>>() {
      @Override
      @NonNull
      public List<Card> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfHolderName = CursorUtil.getColumnIndexOrThrow(_cursor, "holderName");
          final int _cursorIndexOfNote = CursorUtil.getColumnIndexOrThrow(_cursor, "note");
          final int _cursorIndexOfFrontImagePath = CursorUtil.getColumnIndexOrThrow(_cursor, "frontImagePath");
          final int _cursorIndexOfBackImagePath = CursorUtil.getColumnIndexOrThrow(_cursor, "backImagePath");
          final int _cursorIndexOfImagePaths = CursorUtil.getColumnIndexOrThrow(_cursor, "imagePaths");
          final int _cursorIndexOfColor = CursorUtil.getColumnIndexOrThrow(_cursor, "color");
          final int _cursorIndexOfIsFavorite = CursorUtil.getColumnIndexOrThrow(_cursor, "isFavorite");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final int _cursorIndexOfFavoriteAt = CursorUtil.getColumnIndexOrThrow(_cursor, "favoriteAt");
          final List<Card> _result = new ArrayList<Card>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Card _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final String _tmpCategory;
            if (_cursor.isNull(_cursorIndexOfCategory)) {
              _tmpCategory = null;
            } else {
              _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
            }
            final String _tmpHolderName;
            if (_cursor.isNull(_cursorIndexOfHolderName)) {
              _tmpHolderName = null;
            } else {
              _tmpHolderName = _cursor.getString(_cursorIndexOfHolderName);
            }
            final String _tmpNote;
            if (_cursor.isNull(_cursorIndexOfNote)) {
              _tmpNote = null;
            } else {
              _tmpNote = _cursor.getString(_cursorIndexOfNote);
            }
            final String _tmpFrontImagePath;
            if (_cursor.isNull(_cursorIndexOfFrontImagePath)) {
              _tmpFrontImagePath = null;
            } else {
              _tmpFrontImagePath = _cursor.getString(_cursorIndexOfFrontImagePath);
            }
            final String _tmpBackImagePath;
            if (_cursor.isNull(_cursorIndexOfBackImagePath)) {
              _tmpBackImagePath = null;
            } else {
              _tmpBackImagePath = _cursor.getString(_cursorIndexOfBackImagePath);
            }
            final String _tmpImagePaths;
            if (_cursor.isNull(_cursorIndexOfImagePaths)) {
              _tmpImagePaths = null;
            } else {
              _tmpImagePaths = _cursor.getString(_cursorIndexOfImagePaths);
            }
            final long _tmpColor;
            _tmpColor = _cursor.getLong(_cursorIndexOfColor);
            final boolean _tmpIsFavorite;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsFavorite);
            _tmpIsFavorite = _tmp != 0;
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            final long _tmpFavoriteAt;
            _tmpFavoriteAt = _cursor.getLong(_cursorIndexOfFavoriteAt);
            _item = new Card(_tmpId,_tmpName,_tmpCategory,_tmpHolderName,_tmpNote,_tmpFrontImagePath,_tmpBackImagePath,_tmpImagePaths,_tmpColor,_tmpIsFavorite,_tmpCreatedAt,_tmpUpdatedAt,_tmpFavoriteAt);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Object getCardById(final long id, final Continuation<? super Card> $completion) {
    final String _sql = "SELECT * FROM cards WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Card>() {
      @Override
      @Nullable
      public Card call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfHolderName = CursorUtil.getColumnIndexOrThrow(_cursor, "holderName");
          final int _cursorIndexOfNote = CursorUtil.getColumnIndexOrThrow(_cursor, "note");
          final int _cursorIndexOfFrontImagePath = CursorUtil.getColumnIndexOrThrow(_cursor, "frontImagePath");
          final int _cursorIndexOfBackImagePath = CursorUtil.getColumnIndexOrThrow(_cursor, "backImagePath");
          final int _cursorIndexOfImagePaths = CursorUtil.getColumnIndexOrThrow(_cursor, "imagePaths");
          final int _cursorIndexOfColor = CursorUtil.getColumnIndexOrThrow(_cursor, "color");
          final int _cursorIndexOfIsFavorite = CursorUtil.getColumnIndexOrThrow(_cursor, "isFavorite");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final int _cursorIndexOfFavoriteAt = CursorUtil.getColumnIndexOrThrow(_cursor, "favoriteAt");
          final Card _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final String _tmpCategory;
            if (_cursor.isNull(_cursorIndexOfCategory)) {
              _tmpCategory = null;
            } else {
              _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
            }
            final String _tmpHolderName;
            if (_cursor.isNull(_cursorIndexOfHolderName)) {
              _tmpHolderName = null;
            } else {
              _tmpHolderName = _cursor.getString(_cursorIndexOfHolderName);
            }
            final String _tmpNote;
            if (_cursor.isNull(_cursorIndexOfNote)) {
              _tmpNote = null;
            } else {
              _tmpNote = _cursor.getString(_cursorIndexOfNote);
            }
            final String _tmpFrontImagePath;
            if (_cursor.isNull(_cursorIndexOfFrontImagePath)) {
              _tmpFrontImagePath = null;
            } else {
              _tmpFrontImagePath = _cursor.getString(_cursorIndexOfFrontImagePath);
            }
            final String _tmpBackImagePath;
            if (_cursor.isNull(_cursorIndexOfBackImagePath)) {
              _tmpBackImagePath = null;
            } else {
              _tmpBackImagePath = _cursor.getString(_cursorIndexOfBackImagePath);
            }
            final String _tmpImagePaths;
            if (_cursor.isNull(_cursorIndexOfImagePaths)) {
              _tmpImagePaths = null;
            } else {
              _tmpImagePaths = _cursor.getString(_cursorIndexOfImagePaths);
            }
            final long _tmpColor;
            _tmpColor = _cursor.getLong(_cursorIndexOfColor);
            final boolean _tmpIsFavorite;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsFavorite);
            _tmpIsFavorite = _tmp != 0;
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            final long _tmpFavoriteAt;
            _tmpFavoriteAt = _cursor.getLong(_cursorIndexOfFavoriteAt);
            _result = new Card(_tmpId,_tmpName,_tmpCategory,_tmpHolderName,_tmpNote,_tmpFrontImagePath,_tmpBackImagePath,_tmpImagePaths,_tmpColor,_tmpIsFavorite,_tmpCreatedAt,_tmpUpdatedAt,_tmpFavoriteAt);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Flow<Integer> getCardCount() {
    final String _sql = "SELECT COUNT(*) FROM cards";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"cards"}, new Callable<Integer>() {
      @Override
      @NonNull
      public Integer call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Integer _result;
          if (_cursor.moveToFirst()) {
            final Integer _tmp;
            if (_cursor.isNull(0)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getInt(0);
            }
            _result = _tmp;
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<Integer> getCardCountByCategory(final String category) {
    final String _sql = "SELECT COUNT(*) FROM cards WHERE category = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (category == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, category);
    }
    return CoroutinesRoom.createFlow(__db, false, new String[] {"cards"}, new Callable<Integer>() {
      @Override
      @NonNull
      public Integer call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Integer _result;
          if (_cursor.moveToFirst()) {
            final Integer _tmp;
            if (_cursor.isNull(0)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getInt(0);
            }
            _result = _tmp;
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
