package com.jrubiralta.data.repository

import com.jrubiralta.data.database.DatabaseDataSource
import com.jrubiralta.data.mapper.toModel
import com.jrubiralta.data.network.Network
import com.jrubiralta.domain.model.Rates
import com.jrubiralta.domain.model.Transaction
import com.jrubiralta.domain.repository.TransactionRepository
import io.reactivex.Maybe
import io.reactivex.Single

class GNBTransactionRepository(
    private val network: Network,
    private val database: DatabaseDataSource)
    : TransactionRepository {

    override fun getTransaction(): Single<List<Transaction>> =
        network.getTransaction().map { it.map { it.toModel() } }

    override fun getRates(): Single<List<Rates>> =
        network.getRates().map { it.map { it.toModel() } }

    override fun saveTransactions(list: List<Transaction>): Single<Int> {
        return database.saveTransactions(list)
    }

    override fun getTransactionsFromDB(): Maybe<List<Transaction>> {
        return database.getTransactions()
    }

    override fun clearAllDataBase(): Single<Boolean> {
        return database.clearAllDataBase()
    }

    override fun getProductTransactionsFromDB(sku: String): Maybe<List<Transaction>> {
        return database.getProductTransactions(sku)
    }
}