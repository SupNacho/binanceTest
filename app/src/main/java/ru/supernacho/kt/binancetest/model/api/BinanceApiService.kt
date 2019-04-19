package ru.supernacho.kt.binancetest.model.api

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query
import ru.supernacho.kt.binancetest.model.entity.AccountResponse
import ru.supernacho.kt.binancetest.model.entity.TickerResponse

interface BinanceApiService {

    @GET("/api/v1/ticker/24hr")
    fun getTicker24hr(): Single<List<TickerResponse>>

    @GET("/api/v3/account")
    fun getAccountData(@Header("x-mbx-apikey") header: String, @Query("timestamp")timeStamp: Long,
                       @Query("recvWindow") recvWindow: Long, @Query("signature") signature: String): Single<AccountResponse>
}