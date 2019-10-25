package com.jrubiralta.data.network

import com.jrubiralta.goliath.datasource.BuildConfig
import com.jrubiralta.goliath.domain.constants.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


inline fun <reified S> createService(
        endPoint: String,
        authInterceptor: AuthInterceptor? = null, ignoreSSL: Boolean = false)
        : S {
    val logging = HttpLoggingInterceptor()
    if (BuildConfig.BUILD_TYPE == "debug") {
        logging.level = HttpLoggingInterceptor.Level.BODY
    } else {
        logging.level = HttpLoggingInterceptor.Level.NONE
    }

    val builder = if(!ignoreSSL){
        OkHttpClient.Builder()
    } else {
        UnsafeOkHttpClient.getUnsafeOkHttpBuilder()
    }
    builder.connectTimeout(Constants.DEFAULT_RETROFIT_TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .readTimeout(Constants.DEFAULT_RETROFIT_TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .writeTimeout(Constants.DEFAULT_RETROFIT_TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .addInterceptor(logging)

    if (authInterceptor != null) {
        builder.addInterceptor(authInterceptor)
    }

    val client = builder
            .build()

    val retrofit = Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .baseUrl(endPoint)
            .build()

    return retrofit.create(S::class.java)
}