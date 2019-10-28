package com.jrubiralta.goliath.ui.view.product

import com.jrubiralta.domain.model.Transaction
import com.jrubiralta.goliath.ui.view.View


interface ProductDetailView
    : View {

    fun getProduct(): String
    fun updateList(list: List<Transaction>)
}
