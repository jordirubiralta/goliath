package com.jrubiralta.util.realm

import android.content.Context
import android.text.TextUtils
import android.util.Base64
import com.jrubiralta.data.constant.RealmVersions
import com.jrubiralta.data.exception.InitRealmException
import com.jrubiralta.data.utils.Prefs
import com.jrubiralta.data.utils.security.KeyStoreUtil
import com.jrubiralta.goliath.domain.constants.Constants
import io.realm.Realm
import io.realm.RealmConfiguration
import java.security.SecureRandom

object RealmInitializer {

    fun initRealm(context: Context) {
        if (KeyStoreUtil.generateKey(context)) {
            Realm.init(context)
            initRealmConfiguration(context)
        } else {
            throw Exception("Error generating keystore")
        }
    }


    private fun initRealmConfiguration(context: Context) {
        try {
            val config = RealmConfiguration.Builder()
                    .name(Realm.DEFAULT_REALM_NAME)
                    .schemaVersion(RealmVersions.VERSION_1)
                    .migration(RealmMigrationUtil())
                    .encryptionKey(getRealmEncryptionKey(context))
                    .build()
            Realm.setDefaultConfiguration(config)
            if (!isRealmInstanceOk()) {
                throw InitRealmException()
            }
        } catch (e: IllegalArgumentException) {
            restartDatabase(context)
        } catch (e: InitRealmException) {
            restartDatabase(context)
        }
    }

    private fun isRealmInstanceOk(): Boolean {
        var realm: Realm? = null
        return try {
            realm = Realm.getDefaultInstance()
            realm?.close()
            true
        } catch (e: Exception) {
            realm?.close()
            false
        }
    }

    private fun restartDatabase(context: Context) {
        deleteRealm()
        Prefs.clear()
        initRealm(context)
    }

    private fun deleteRealm() {
        val configWithoutEncryptionKey = RealmConfiguration.Builder()
                .schemaVersion(RealmVersions.VERSION_1)
                .migration(RealmMigrationUtil())
                .build()
        Realm.deleteRealm(configWithoutEncryptionKey)
    }

    private fun getRealmEncryptionKey(context: Context): ByteArray {
        var result: ByteArray? = null
        try {
            var realmKey = Prefs.getString(context, Constants.KEY_REALM_KEY, null)
            if (TextUtils.isEmpty(realmKey)) {
                realmKey = createRealmKey(context)
            }
            result = Base64.decode(realmKey, Base64.DEFAULT)
        } catch (exceptionInFirstAttempt: Exception) {
            try {
                val realmKey: String? = createRealmKey(context)
                result = Base64.decode(realmKey, Base64.DEFAULT)
            } catch (exceptionInSecondAttempt: Exception) {
            }
        }
        return result!!
    }

    private fun createRealmKey(context: Context): String {
        var realmKey = ""
        while (TextUtils.isEmpty(realmKey)) {
            try {
                val key: ByteArray = ByteArray(64)
                SecureRandom().nextBytes(key)
                Prefs.setString(context, Constants.KEY_REALM_KEY, Base64.encodeToString(key, Base64.DEFAULT))
                realmKey = Prefs.getString(context, Constants.KEY_REALM_KEY, null)
            } catch (e: Exception) {
                realmKey = ""
            }
        }
        return realmKey
    }

}