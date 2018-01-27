package com.app.sid.funwithflags.realm;

import io.realm.Realm;

/**
 * Created by Sidhanshu Udawat on 27-Jan-18.
 */

public interface RealmMVP {

    Realm getRealm();

    void closeRealm(Realm realm);

    void clearData();

    void init();
}
