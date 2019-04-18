package ru.supernacho.kt.binancetest.model.repository

import io.reactivex.Single
import ru.supernacho.kt.binancetest.model.entity.Response
import ru.supernacho.kt.binancetest.model.entity.TickerResponse

interface INetRepository {
    fun getAllPairsInfo() : Single<Response>
    fun getTicker24hr() : Single<List<TickerResponse>>
    fun startListeningTicker()
}