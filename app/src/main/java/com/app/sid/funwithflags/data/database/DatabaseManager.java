package com.app.sid.funwithflags.data.database;

import android.app.Application;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;

import com.app.sid.funwithflags.data.database.schema.CountriesTableSchema;
import com.app.sid.funwithflags.utils.pref.PrefManager;
import com.squareup.sqlbrite.BriteDatabase;
import com.squareup.sqlbrite.SqlBrite;

import java.util.List;

import rx.Observable;
import rx.functions.Func1;
import rx.schedulers.Schedulers;


public class DatabaseManager<T> {

    private static DatabaseManager mInstance = null;
    @NonNull
    private DatabaseHelper mDbHelper;
    @NonNull
    private SQLiteDatabase mDatabase;
    @NonNull
    private BriteDatabase mBriteDBHelper;

    public static final String DATABASE_NAME = "funwithflags.db";
    public static final int DATABASE_VERSION = 1;

    private static class DatabaseHelper extends SQLiteOpenHelper {

        private static final String TAG = DatabaseHelper.class.getSimpleName();

        public DatabaseHelper(final Context context, final String name,
                              final SQLiteDatabase.CursorFactory factory, final int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CountriesTableSchema.CREATE_TABLE);
        }

        @Override
        public void onUpgrade(final SQLiteDatabase db, final int oldVersion,
                              final int newVersion) {
            db.execSQL(CountriesTableSchema.DROP_TABLE);


            PrefManager.getInstance().clearAll();
            this.onCreate(db);
        }
    }

    /**
     *
     */
    private DatabaseManager() {
    }

    public synchronized static DatabaseManager getInstance() {
        if (null == mInstance) {
            mInstance = new DatabaseManager();
        }
        return mInstance;
    }

    public void initDatabase(Application mApp) {
        this.mDbHelper = new DatabaseHelper(mApp, DATABASE_NAME, null,
                DATABASE_VERSION);
        this.mDatabase = this.mDbHelper.getWritableDatabase();
        SqlBrite sqlBrite = SqlBrite.create();
        mBriteDBHelper = sqlBrite.wrapDatabaseHelper(mDbHelper, Schedulers.io());
    }


    public long add(final ContentValues values, final String tableName) {
        long id = 0;
        id = mBriteDBHelper.insert(tableName, values, SQLiteDatabase.CONFLICT_REPLACE);
        return id;
    }

    public long delete(final String tableName, final String whereClause,
                       final String[] whereArgs) {
        return this.mBriteDBHelper.delete(tableName, whereClause, whereArgs);
    }

    public long update(String table, ContentValues values, String whereClause,
                       String[] whereArgs) {
        return this.mBriteDBHelper.update(table, values, whereClause, whereArgs);
    }


    public Observable<List<T>> read(String table, String query, Func1<Cursor, T> mapper) {
        return mBriteDBHelper.createQuery(table, query)
                .mapToList(mapper);
    }

    public Observable<T> readSingle(String table, String query, Func1<Cursor, T> mapper, String... args) {
        return mBriteDBHelper.createQuery(table, query, args)
                .mapToOneOrDefault(mapper, null);
    }

    public Observable<List<T>> read(String table, String query, Func1<Cursor, T> mapper, String... args) {
        return mBriteDBHelper.createQuery(table, query, args)
                .mapToList(mapper);
    }

    public Cursor read(final String tableName, final String[] columns) {
        if (mDbHelper != null) {
            Cursor cursor = null;
            SQLiteDatabase db = this.mDbHelper.getReadableDatabase();
            cursor = db.query(tableName, columns, null, null, null, null, null);
            return cursor;
        } else {
            return null;
        }

    }

    public Cursor read(final String tableName, final String[] columns,
                       final String selection, final String[] selectionArgs,
                       final String groupBy, final String having, final String orderBy) {
        Cursor cursor = null;
        SQLiteDatabase db = this.mDbHelper.getReadableDatabase();
        cursor = db.query(tableName, columns, selection, selectionArgs,
                groupBy, having, orderBy);
        return cursor;
    }

    public Cursor read(boolean distinct, final String tableName, final String[] columns,
                       final String selection, final String[] selectionArgs,
                       final String groupBy, final String having, final String orderBy, final String limit) {
        Cursor cursor = null;
        SQLiteDatabase db = this.mDbHelper.getReadableDatabase();
        cursor = db.query(distinct, tableName, columns, selection, selectionArgs,
                groupBy, having, orderBy, limit);
        return cursor;
    }


}
