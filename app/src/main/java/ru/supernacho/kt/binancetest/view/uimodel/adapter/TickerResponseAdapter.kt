package ru.supernacho.kt.binancetest.view.uimodel.adapter

import ru.supernacho.kt.binancetest.model.entity.TickerResponse
import ru.supernacho.kt.binancetest.view.uimodel.ExInfoUiModel

object TickerResponseAdapter {
    fun adapt(tickerEvent: TickerResponse): ExInfoUiModel{
        return ExInfoUiModel(
            symbol = tickerEvent.symbol,
            avgPrice = tickerEvent.weightedAvgPrice,
            highPrice = tickerEvent.highPrice,
            lowPrice = tickerEvent.lowPrice,
            percentChange = tickerEvent.priceChangePercent)
    }
}