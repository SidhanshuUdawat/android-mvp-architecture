package com.app.sid.funwithflags.realm

import io.realm.Realm

interface RealmMVP {
    fun getRealm(): Realm?
    fun closeRealm(realm: Realm)
    fun clearData()
}