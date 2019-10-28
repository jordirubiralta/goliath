package com.jrubiralta.data.network

import com.jrubiralta.data.model.dto.RatesDTO
import com.jrubiralta.data.model.dto.TransactionDTO
import io.reactivex.Single
import retrofit2.http.GET

interface ApiService {

    companion object {
        val ENDPOINT_1 = "https://quiet-stone-2094.herokuapp.com/"
    }

    @GET("transactions.json")
    fun getTransaction(): Single<List<TransactionDTO>>

    @GET("rates.json")
    fun getRates(): Single<List<RatesDTO>>


}