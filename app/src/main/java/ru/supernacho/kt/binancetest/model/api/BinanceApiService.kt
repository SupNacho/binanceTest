package ru.supernacho.kt.binancetest.model.api

import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import ru.supernacho.kt.binancetest.model.entity.Response
import ru.supernacho.kt.binancetest.model.entity.TickerResponse

interface BinanceApiService {
    @GET("api/v1/exchangeInfo")
    fun getAllPairs(): Single<Response>

    @GET("/api/v1/ticker/24hr")
    fun getTicker24hr(): Single<List<TickerResponse>>
}