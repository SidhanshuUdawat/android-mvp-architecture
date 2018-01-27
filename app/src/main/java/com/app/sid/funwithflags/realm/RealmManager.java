package com.app.sid.funwithflags.realm;


import android.content.Context;
import android.util.Log;

import com.app.sid.funwithflags.utils.AppUtils;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by Sidhanshu Udawat on 27-Jan-18.
 */

public class RealmManager implements RealmMVP {

    private static final String TAG = RealmManager.class.getSimpleName();
    private static final int REALM_SCHEMA_VERSION = 1;
    private Context mContext;
    private RealmConfiguration mRealmConfiguration;

    public RealmManager(Context context) {
        mContext = context;
    }

    /**
     * Setting Realm library up.
     * deleteRealmIfMigrationNeeded is used to avoid the app from crashing when the schema of
     * the database changes from one version to another.
     */
    @Override
    public void init() {
        try {
            Realm.init(mContext);
            mRealmConfiguration = new RealmConfiguration.Builder()
                    .schemaVersion(REALM_SCHEMA_VERSION)
                    .deleteRealmIfMigrationNeeded()
                    .build();
            Realm.setDefaultConfiguration(mRealmConfiguration);
        } catch (UnsatisfiedLinkError error) {
            Log.e(TAG, error.getMessage());
        }
    }

    /**
     * Returns a realm instance
     *
     * @return the realm instance to be used
     */
    @Override
    public Realm getRealm() {
        Realm realm = null;
        try {
            realm = Realm.getDefaultInstance();
        } catch (Exception e) {
            Log.e(TAG, "Opening Error: " + e.getMessage());
            clearData();
            AppUtils.restartApp(mContext);
        }
        return realm;
    }

    @Override
    public void closeRealm(Realm realm) {
        try {
            if (realm != null && !realm.isClosed()) {
                realm.close();
            }
        } catch (Exception e) {
            Log.e(TAG, "Closing Error: " + e.getMessage());
        }
    }

    @Override
    public void clearData() {
        try {
            Realm.deleteRealm(mRealmConfiguration);
        } catch (Exception ex) {
            Log.e(TAG, "Clearing Error: " + ex.getMessage());
        }
    }
}
