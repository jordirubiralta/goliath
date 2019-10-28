package com.jrubiralta.data.network

import com.jrubiralta.data.model.dto.RatesDTO
import com.jrubiralta.data.model.dto.TransactionDTO
import io.reactivex.Single

class AppNetwork(
    private val apiService: ApiService)
    : Network {

    override fun getTransaction(): Single<List<TransactionDTO>> {
        return apiService.getTransaction()
    }

    override fun getRates(): Single<List<RatesDTO>> {
        return apiService.getRates()
    }


}