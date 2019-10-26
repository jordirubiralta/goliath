package com.jrubiralta.goliath.ui.view.home

import com.jrubiralta.domain.model.Transaction
import com.jrubiralta.goliath.ui.view.View

interface HomeView
    : View {
    fun updateList(transactionList: List<Transaction>)
    fun sumTransactions(transactionList: List<Transaction>)
}
