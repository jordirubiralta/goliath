package com.jrubiralta.util.realm

import com.jrubiralta.data.constant.RealmVersions
import io.realm.DynamicRealm
import io.realm.RealmMigration

class RealmMigrationUtil : RealmMigration {

    override fun migrate(realm: DynamicRealm, oldVersion: Long, newVersion: Long) {
        val schema = realm.schema
        var currentMigrationVersion = oldVersion
        if (currentMigrationVersion == RealmVersions.VERSION_1) {
            // TODO
            /*realm.where(whateverVO.VO_NAME).findAll()
            val objectSchema = schema.get(whateverVO.VO_NAME)
            objectSchema!!.addField(whateverVO.WHATEVER_FIELD, String::class.java)*/
            currentMigrationVersion++
        }
        if (currentMigrationVersion == RealmVersions.VERSION_2) {
            // TODO
            currentMigrationVersion++
        }
    }
}