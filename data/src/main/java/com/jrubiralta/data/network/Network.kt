package com.jrubiralta.data.network

import com.jrubiralta.data.model.RatesDTO
import com.jrubiralta.data.model.TransactionDTO
import io.reactivex.Single


interface Network {

    fun getTransaction() : Single<List<TransactionDTO>>
    fun getRates(): Single<List<RatesDTO>>

}