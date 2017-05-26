package com.app.sid.funwithflags.data.database.loader;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import java.util.List;

public abstract class AbstractDataLoader<E extends List<?>> extends AsyncTaskLoader<E> {

    protected E mLastDataList = null;

    protected abstract E buildList();

    public AbstractDataLoader(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
    }

    /*Runs on a worker thread.
     * Delegates to concrete subclass's buildList() method */
    @Override
    public E loadInBackground() {
        // TODO Auto-generated method stub
        return this.buildList();
    }

    /*Runs on the UI thread.
     * Routes the results from the background thread to the user of dataList */
    @Override
    public void deliverResult(E dataList) {

        if (isReset()) {
            /*The loader is stopped and we received an async query*/
            this.deleteDataList(dataList);
            return;
        }

        E oldDataList = this.mLastDataList;
        this.mLastDataList = dataList;

        if (isStarted()) {
            super.deliverResult(dataList);
        } else {
            if (oldDataList != null && oldDataList != dataList
                    && oldDataList.size() > 0) {
                this.deleteDataList(oldDataList);
            }
        }
    }


    /**
     * Starts an asynchronous load of the list data. When the result is ready
     * the callbacks will be called on the UI thread. If a previous load has
     * been completed and is still valid the result may be passed to the
     * callbacks immediately.
     * <p>
     * Must be called from the UI thread.
     */
    @Override
    protected void onStartLoading() {
        // TODO Auto-generated method stub
        //super.onStartLoading();
        if (this.mLastDataList != null) {
            this.deliverResult(this.mLastDataList);
        }
        if (takeContentChanged() || this.mLastDataList == null
                || this.mLastDataList.size() == 0) {
            forceLoad();
        }
    }

    /**
     * Must be called from the UI thread, triggered by a call to stopLoading().
     */
    @Override
    protected void onStopLoading() {
        // Attempt to cancel the current load task if possible.
        cancelLoad();
    }

    /**
     * Must be called from the UI thread, triggered by a call to cancel(). Here,
     * we make sure our Cursor is closed, if it still exists and is not already
     * closed.
     */
    @Override
    public void onCanceled(E dataList) {
        if (dataList != null && dataList.size() > 0) {
            this.deleteDataList(dataList);
        }
    }

    /**
     * Must be called from the UI thread, triggered by a call to reset(). Here,
     * we make sure our Cursor is closed, if it still exists and is not already
     * closed.
     */
    @Override
    protected void onReset() {
        super.onReset();
        // Ensure the loader is stopped
        this.onStopLoading();
        if (this.mLastDataList != null && this.mLastDataList.size() > 0) {
            this.deleteDataList(this.mLastDataList);
        }
        this.mLastDataList = null;
    }

    protected void deleteDataList(E dataList) {
        if (dataList != null && dataList.size() > 0) {
            for (int i = 0; i < dataList.size(); i++) {
                dataList.remove(i);
            }
        }
    }

}
