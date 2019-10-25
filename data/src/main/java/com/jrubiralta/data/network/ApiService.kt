package com.jrubiralta.data.network

import com.jrubiralta.data.model.TransactionListDTO
import io.reactivex.Single
import retrofit2.http.GET

interface ApiService {

    companion object {
        val ENDPOINT_1 = "http://quiet-stone-2094.herokuapp.com/transactions.json"
    }

    @GET
    fun getTransaction(): Single<TransactionListDTO>

}