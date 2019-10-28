package com.jrubiralta.data.network

import com.jrubiralta.data.model.dto.RatesDTO
import com.jrubiralta.data.model.dto.TransactionDTO
import io.reactivex.Single


interface Network {

    fun getTransaction() : Single<List<TransactionDTO>>
    fun getRates(): Single<List<RatesDTO>>

}