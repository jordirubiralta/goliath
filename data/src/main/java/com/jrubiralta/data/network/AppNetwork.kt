package com.jrubiralta.data.network

import com.jrubiralta.data.model.TransactionListDTO
import io.reactivex.Single

class AppNetwork(
    private val apiService: ApiService)
    : Network {

    override fun getTransaction(): Single<TransactionListDTO> {
        return apiService.getTransaction()
    }


}