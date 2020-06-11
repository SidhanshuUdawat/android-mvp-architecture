package com.app.sid.funwithflags.realm

import android.content.Context
import android.util.Log
import com.app.sid.funwithflags.utils.AppUtils
import io.realm.Realm
import io.realm.RealmConfiguration
import javax.inject.Inject

class RealmManager @Inject constructor(context: Context) : RealmMVP {

    private val config: RealmConfiguration

    init {
        Realm.init(context)
        config = RealmConfiguration.Builder()
                .schemaVersion(REALM_SCHEMA_VERSION.toLong())
                .deleteRealmIfMigrationNeeded()
                .build()
        Realm.setDefaultConfiguration(config)
    }

    override fun getRealm(): Realm = Realm.getDefaultInstance()

    override fun closeRealm(realm: Realm) {
        if (!realm.isClosed) {
            closeRealm(realm)
        }
    }

    override fun clearData() {
        Realm.deleteRealm(config)
    }

    companion object {
        private const val REALM_SCHEMA_VERSION = 1
    }

}