package ru.supernacho.kt.binancetest.model.repository

import ru.supernacho.kt.binancetest.model.api.BinanceApiService
import ru.supernacho.kt.binancetest.model.net.WsClient

class NetRepository(
    private val apiService: BinanceApiService,
    private val wsClient: WsClient
) : INetRepository {

    override fun getAllPairsInfo() = apiService.getAllPairs()
    override fun getTicker24hr() = apiService.getTicker24hr()
    override fun startListeningTicker() {
        wsClient.onStartConnection()
    }
}