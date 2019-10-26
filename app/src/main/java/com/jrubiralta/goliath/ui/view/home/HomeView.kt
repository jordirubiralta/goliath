package com.jrubiralta.goliath.ui.view.home

import com.jrubiralta.domain.model.TransactionList
import com.jrubiralta.goliath.ui.view.View

interface HomeView
    : View {
    fun updateList(transactionList: TransactionList)
}
