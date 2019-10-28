package com.jrubiralta.data.extension

import io.realm.Realm
import io.realm.RealmObject

/**
 * AndroidExtensions
 */

/**
 * Realm
 * */
fun RealmObject.save() {
    val realm = Realm.getDefaultInstance()
    realm.use { it1 ->
        it1.executeTransaction { it2 ->
            it2.copyToRealmOrUpdate(this)
        }
    }
}

fun <T : RealmObject> List<T>.save() {
    val realm = Realm.getDefaultInstance()
    realm.use { it1 ->
        it1.executeTransaction { it2 ->
            it2.copyToRealmOrUpdate(this)
        }
    }
}

fun <T : RealmObject> removeAll(realmObject: Class<T>) {
    val realm = Realm.getDefaultInstance()
    realm.use { it1 ->
        it1.executeTransaction { it2 ->
            it2.where(realmObject).findAll().deleteAllFromRealm()
        }
    }
}