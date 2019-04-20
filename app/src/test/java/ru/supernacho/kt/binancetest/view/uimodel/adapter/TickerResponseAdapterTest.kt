package ru.supernacho.kt.binancetest.view.uimodel.adapter

import org.junit.Test

import org.junit.Assert.*
import ru.supernacho.kt.binancetest.model.entity.TickerResponse

class TickerResponseAdapterTest {

    @Test
    fun adapt() {
        val inputModel = TickerResponse().apply {
            symbol = "test"
            weightedAvgPrice = "7.7"
            highPrice = "8.8"
            lowPrice = "0.0"
            priceChangePercent = "2.3"
            askPrice = "1.2"
            bidPrice = "2.3"
            lastPrice = "3.3"
            lastQty = "1231"
            openPrice = "4.3"
            prevClosePrice = "3.2"
            volume = "123123"
        }

        val result = TickerResponseAdapter.adapt(inputModel)

        assertEquals(result.avgPrice, inputModel.weightedAvgPrice)
        assertEquals(result.symbol, inputModel.symbol)
        assertEquals(result.highPrice, inputModel.highPrice)
        assertEquals(result.lowPrice, inputModel.lowPrice)
        assertEquals(result.percentChange, inputModel.priceChangePercent)
    }
}