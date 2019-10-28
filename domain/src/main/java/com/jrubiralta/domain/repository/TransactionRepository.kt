package com.jrubiralta.domain.repository

import com.jrubiralta.domain.model.Rates
import com.jrubiralta.domain.model.Transaction
import io.reactivex.Maybe
import io.reactivex.Single

interface TransactionRepository {
    fun getTransaction(): Single<List<Transaction>>
    fun getRates(): Single<List<Rates>>
    fun saveTransactions(list: List<Transaction>): Single<Int>
    fun getTransactionsFromDB(): Maybe<List<Transaction>>
    fun clearAllDataBase(): Single<Boolean>
    fun getProductTransactionsFromDB(sku: String): Maybe<List<Transaction>>
}