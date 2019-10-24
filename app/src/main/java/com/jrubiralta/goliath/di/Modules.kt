package com.jrubiralta.goliath.di

import android.content.Context
import com.github.salomonbrys.kodein.*
import com.jrubiralta.goliath.BuildConfig
import com.jrubiralta.goliath.domain.constants.BuildType
import com.jrubiralta.goliath.domain.constants.buildType
import com.jrubiralta.goliath.domain.executor.Executor
import com.jrubiralta.goliath.executor.RxExecutor

/**
 * Modules
 */
fun appModule(context: Context) = Kodein.Module {

    bind<Context>() with singleton { context }
    bind<Executor>() with singleton { RxExecutor() }
    bind<BuildType>() with singleton { buildType(BuildConfig.BUILD_TYPE) }

}

val domainModule = Kodein.Module {

}

val dataModule = Kodein.Module {
    //Preferences
    //Database
    // Api Services
    //Data sources
    //Repository
}