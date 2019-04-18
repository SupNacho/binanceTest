package ru.supernacho.kt.binancetest.model.api

import io.reactivex.Observable
import retrofit2.http.GET
import ru.supernacho.kt.binancetest.model.entity.Response

interface BinanceApiService {
    @GET("api/v1/exchangeInfo")
    fun getAllPairs(): Observable<Response>
}