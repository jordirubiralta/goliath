package com.jrubiralta.goliath.presenter.home

import android.util.Log
import com.jrubiralta.domain.interactor.transactions.GetTransactionsDBUseCase
import com.jrubiralta.domain.interactor.transactions.GetTransactionsUseCase
import com.jrubiralta.goliath.presenter.BasePresenterImpl
import com.jrubiralta.goliath.ui.view.home.HomeView

class HomePresenterImpl(
    view: HomeView,
    private val getTransactionsDBUseCase: GetTransactionsDBUseCase
)
    : BasePresenterImpl<HomeView>(view),
    HomePresenter {

    override fun getTransactions() {
        executeGetTransactions()
    }

    private fun executeGetTransactions() {
        getTransactionsDBUseCase.execute(
            onEmpty = {
                Log.d("ERROR", "EMPTY LIST")
            },
            onSuccess = {
                val list = view.sortList(it)
                view.updateList(list)
            },
            onError = {
                Log.d("ERROR", it.toString())
            }
        )
    }

}