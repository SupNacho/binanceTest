package ru.supernacho.kt.binancetest.view.uimodel.adapter

import ru.supernacho.kt.binancetest.model.entity.TickerResponse
import ru.supernacho.kt.binancetest.view.uimodel.ExInfoUiModel

object TickerResponseAdapter {
    fun adapt(tickerResponse: TickerResponse): ExInfoUiModel{
        return ExInfoUiModel(
            symbol = tickerResponse.symbol,
            avgPrice = tickerResponse.weightedAvgPrice,
            highPrice = tickerResponse.highPrice,
            lowPrice = tickerResponse.lowPrice,
            percentChange = tickerResponse.priceChangePercent)
    }
}