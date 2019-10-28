package com.jrubiralta.data.database

import com.jrubiralta.domain.model.Transaction
import io.reactivex.Maybe
import io.reactivex.Single

interface DatabaseDataSource {
    fun saveTransactions(list: List<Transaction>): Single<Int>
    fun getTransactions(): Maybe<List<Transaction>>
    fun clearAllDataBase(): Single<Boolean>
}