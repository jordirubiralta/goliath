package com.jrubiralta.goliath

import android.app.Application
import android.util.Log
import com.github.salomonbrys.kodein.Kodein
import com.jrubiralta.goliath.di.appModule
import com.jrubiralta.goliath.di.dataModule
import com.jrubiralta.goliath.di.domainModule
import io.realm.Realm
import io.realm.RealmConfiguration

class App
    : Application() {

    var kodein = Kodein { importDependencies(this) }

    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
        val config = RealmConfiguration.Builder().build()
        Realm.setDefaultConfiguration(config)
    }

    fun importDependencies(kodeinBuilder: Kodein.Builder) {
        kodeinBuilder.import(appModule(this))
        kodeinBuilder.import(domainModule)
        kodeinBuilder.import(dataModule)
    }
}
