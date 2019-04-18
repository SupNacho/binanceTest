package ru.supernacho.kt.binancetest.view.uimodel.adapter

import ru.supernacho.kt.binancetest.model.entity.TickerEvent
import ru.supernacho.kt.binancetest.view.uimodel.ExInfoUiModel

object TickerEventAdapter {
    fun adapt(tickerEvent: TickerEvent): ExInfoUiModel{
        return ExInfoUiModel(
            symbol = tickerEvent.symbol,
            avgPrice = tickerEvent.weightAvgPrice,
            highPrice = tickerEvent.highPrice,
            lowPrice = tickerEvent.lowPrice,
            percentChange = tickerEvent.priceChangePercent)
    }
}