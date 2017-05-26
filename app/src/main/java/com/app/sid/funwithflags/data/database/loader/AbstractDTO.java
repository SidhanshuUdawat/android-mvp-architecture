package com.app.sid.funwithflags.data.database.loader;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.List;

import rx.Observable;

public abstract class AbstractDTO<T> {

    private Context mContext;

    public AbstractDTO(Context context) {
        this.mContext = context;
    }

    public abstract long insert(T entity, String tableName);

    public abstract long delete(T entity);

    public abstract long delete(String tableName);

    public abstract long update(T entity);

    public abstract Observable<List<T>> readObserver();

    public abstract Observable<List<T>> readObserver(T entity);

    public abstract List read();

    public abstract List read(String selection, String[] selectionArgs,
                              String groupBy, String having, String orderBy);

    public abstract ContentValues getContentValues(T entity);

    public abstract List getListFromCursor(Cursor cursor);

    public abstract String[] getColumns();
}
