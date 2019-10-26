package com.jrubiralta.data.repository

import com.jrubiralta.data.mapper.toModel
import com.jrubiralta.data.network.Network
import com.jrubiralta.domain.model.Transaction
import com.jrubiralta.domain.repository.TransactionRepository
import io.reactivex.Single

class GNBTransactionRepository(
    private val network: Network
)
    : TransactionRepository {

    override fun getTransaction(): Single<List<Transaction>> =
        network.getTransaction().map { it.map { it.toModel() } }

}