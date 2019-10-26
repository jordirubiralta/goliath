package com.jrubiralta.domain.repository

import com.jrubiralta.domain.model.Transaction
import io.reactivex.Single

interface TransactionRepository {
    fun getTransaction(): Single<List<Transaction>>
}