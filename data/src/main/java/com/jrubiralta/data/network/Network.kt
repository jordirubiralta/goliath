package com.jrubiralta.data.network

import com.jrubiralta.data.model.TransactionListDTO
import io.reactivex.Single


interface Network {

    fun getTransaction() : Single<TransactionListDTO>

}