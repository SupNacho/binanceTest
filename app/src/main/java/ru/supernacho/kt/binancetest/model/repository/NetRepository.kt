package ru.supernacho.kt.binancetest.model.repository

import ru.supernacho.kt.binancetest.model.api.BinanceApiService

class NetRepository(private val apiService: BinanceApiService) : INetRepository {

    override fun getTicker24hr() = apiService.getTicker24hr()
}