package ru.supernacho.kt.binancetest.model.api

import retrofit2.Retrofit

class ApiService {
    companion object{
        fun getApi(retrofit: Retrofit) = retrofit.create(BinanceApiService::class.java)
    }
}