package com.jrubiralta.goliath.presenter.home

import android.util.Log
import com.jrubiralta.domain.interactor.transactions.GetTransactionsUseCase
import com.jrubiralta.goliath.presenter.BasePresenterImpl
import com.jrubiralta.goliath.ui.view.home.HomeView

class HomePresenterImpl(
    view: HomeView,
    private val getTransactionsUseCase: GetTransactionsUseCase)
    : BasePresenterImpl<HomeView>(view),
    HomePresenter {

    override fun getTransactions() {
        executeGetTransactions()
    }

    private fun executeGetTransactions() {
        getTransactionsUseCase.execute(
            onSuccess = {
                view.updateList(it)
            },
            onError = {
                Log.d("ERROR", it.toString())
            }
        )
    }

}