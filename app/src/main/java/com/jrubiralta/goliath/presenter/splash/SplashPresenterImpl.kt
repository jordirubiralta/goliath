package com.enel.edist.presenter.splash

import android.util.Log
import com.jrubiralta.domain.interactor.transactions.ClearAllDataBaseUseCase
import com.jrubiralta.domain.interactor.transactions.GetTransactionsUseCase
import com.jrubiralta.domain.interactor.transactions.SaveTransactionsUseCase
import com.jrubiralta.domain.model.Transaction
import com.jrubiralta.goliath.presenter.BasePresenterImpl
import com.jrubiralta.goliath.presenter.splash.SplashPresenter
import com.jrubiralta.goliath.ui.view.splash.SplashView
import io.reactivex.disposables.Disposable

class SplashPresenterImpl(
    view: SplashView,
    private val clearAllDataBaseUseCase: ClearAllDataBaseUseCase,
    private val getTransactionsUseCase: GetTransactionsUseCase,
    private val saveTransactionsUseCase: SaveTransactionsUseCase)
    : BasePresenterImpl<SplashView>(view),
    SplashPresenter {

    companion object {
        const val TIME_WAIT: Long = 500
    }

    private var subscribe: Disposable? = null

    override fun pause() {
        super.pause()
        subscribe?.dispose()
    }

    override fun clearDataBase() {
        clearAllDataBase()
    }

    private fun clearAllDataBase() {
        clearAllDataBaseUseCase.execute(
            onSuccess = {
                executeGetTransactions()
            },
            onError = {
                Log.d("ERROR", it.toString())
            }
        )
    }

    private fun executeGetTransactions() {
        getTransactionsUseCase.execute(
            onSuccess = {
                saveTransations(it)
            },
            onError = {
                Log.d("ERROR", it.toString())
            }
        )
    }

    private fun saveTransations(list: List<Transaction>) {
        saveTransactionsUseCase.execute(list,
            onSuccess = {
                view.goToHomeActivity()
            },
            onError = {
                Log.d("ERROR", it.toString())
            })
    }
}
