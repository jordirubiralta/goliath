package com.jrubiralta.domain.repository

import com.jrubiralta.domain.model.TransactionList
import io.reactivex.Single

interface TransactionRepository {
    fun getTransaction(): Single<TransactionList>
}