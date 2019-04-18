package ru.supernacho.kt.binancetest.view.uimodel.adapter

import ru.supernacho.kt.binancetest.model.entity.Symbol
import ru.supernacho.kt.binancetest.view.uimodel.ExInfoUiModel

object ExchangeInfoResponseAdapter {
    fun adapt(symbols: Symbol): ExInfoUiModel{
        return ExInfoUiModel(
            symbol = symbols.symbol,
            highPrice =symbols.baseAsset,
            lowPrice =  symbols.quoteAsset,
            avgPrice = null,
            percentChange = symbols.status
        )
    }
}