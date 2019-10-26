package com.jrubiralta.data.network

import com.jrubiralta.data.model.TransactionDTO
import io.reactivex.Single

class AppNetwork(
    private val apiService: ApiService)
    : Network {

    override fun getTransaction(): Single<List<TransactionDTO>> {
        return apiService.getTransaction()
    }


}