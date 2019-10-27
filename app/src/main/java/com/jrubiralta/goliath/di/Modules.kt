package com.jrubiralta.goliath.di

import android.content.Context
import com.github.salomonbrys.kodein.*
import com.jrubiralta.data.network.*
import com.jrubiralta.data.repository.GNBTransactionRepository
import com.jrubiralta.domain.interactor.transactions.GetRatesUseCase
import com.jrubiralta.domain.interactor.transactions.GetTransactionsUseCase
import com.jrubiralta.domain.repository.TransactionRepository
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
    bind() from provider { GetTransactionsUseCase(repository = instance(), executor = instance()) }
    bind() from provider { GetRatesUseCase(repository = instance(), executor = instance()) }
}

val dataModule = Kodein.Module {
    //Preferences
    //Database
    //Api Services
    bind<Network>() with singleton {
        AppNetwork(
            apiService = createService(endPoint = ApiService.ENDPOINT_1, authInterceptor = BasicAuthInterceptor(), ignoreSSL = true)
        )
    }
    //Data sources
    //Repository
    bind<TransactionRepository>() with singleton { GNBTransactionRepository(network = instance()) }

}