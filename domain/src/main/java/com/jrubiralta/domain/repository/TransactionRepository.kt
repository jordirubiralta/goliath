package com.jrubiralta.domain.repository

import com.jrubiralta.domain.model.Rates
import com.jrubiralta.domain.model.Transaction
import io.reactivex.Single

interface TransactionRepository {
    fun getTransaction(): Single<List<Transaction>>
    fun getRates(): Single<List<Rates>>
}