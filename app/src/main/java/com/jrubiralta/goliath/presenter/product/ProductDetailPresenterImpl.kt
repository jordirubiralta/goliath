package com.jrubiralta.goliath.presenter.product

import android.util.Log
import com.jrubiralta.domain.interactor.transactions.GetRatesUseCase
import com.jrubiralta.goliath.presenter.BasePresenterImpl
import com.jrubiralta.goliath.ui.view.product.ProductDetailView

class ProductDetailPresenterImpl(
    view: ProductDetailView,
    private val getRatesUseCase: GetRatesUseCase)
    : BasePresenterImpl<ProductDetailView>(view),
    ProductDetailPresenter {

    override fun getRates() {
        executeGetRates()
    }

    private fun executeGetRates() {
        getRatesUseCase.execute(
            onSuccess = {
                Log.d("ERROR", it.toString())
            },
            onError = {
                Log.d("ERROR", it.toString())
            }
        )
    }

}