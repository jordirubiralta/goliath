package com.jrubiralta.goliath.ui.view.product

import com.jrubiralta.domain.model.Rates
import com.jrubiralta.domain.model.Transaction
import com.jrubiralta.goliath.ui.view.View


interface ProductDetailView
    : View {

    fun getProduct(): String
    fun updateList(list: List<Transaction>)
    fun changeCurrencyToEur(amount: Double, currency: String): Double
    fun setRatesList(currency: String, list: List<Rates>)
}
