package com.jrubiralta.goliath

import android.app.Application
import com.github.salomonbrys.kodein.Kodein
import com.jrubiralta.goliath.di.appModule
import com.jrubiralta.goliath.di.dataModule
import com.jrubiralta.goliath.di.domainModule

class App
    : Application() {

    var kodein = Kodein { importDependencies(this) }

    fun importDependencies(kodeinBuilder: Kodein.Builder) {
        kodeinBuilder.import(appModule(this))
        kodeinBuilder.import(domainModule)
        kodeinBuilder.import(dataModule)
    }

}
