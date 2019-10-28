package com.jrubiralta.goliath.presenter.product

import android.util.Log
import com.jrubiralta.domain.interactor.transactions.GetProductTransactionsUseCase
import com.jrubiralta.domain.interactor.transactions.GetRatesUseCase
import com.jrubiralta.goliath.presenter.BasePresenterImpl
import com.jrubiralta.goliath.ui.view.product.ProductDetailView

class ProductDetailPresenterImpl(
    view: ProductDetailView,
    private val getRatesUseCase: GetRatesUseCase,
    private val getProductTransactionsUseCase: GetProductTransactionsUseCase
)
    : BasePresenterImpl<ProductDetailView>(view),
    ProductDetailPresenter {

    override fun getRates() {
        executeGetRates()
    }

    override fun setProductList() {
        val product = view.getProduct()
        getProductTransactionsUseCase.execute(product,
            onSuccess = {
                view.updateList(it)
            },
            onEmpty = {
                Log.d("ERROR", "EMPTY LIST")
            },
            onError = {
                Log.d("ERROR", it.toString())
            })
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