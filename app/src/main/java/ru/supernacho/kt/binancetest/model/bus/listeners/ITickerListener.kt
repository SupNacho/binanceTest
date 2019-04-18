package ru.supernacho.kt.binancetest.model.bus.listeners

import ru.supernacho.kt.binancetest.model.entity.TickerEvent

interface ITickerListener: IEventListener {
    fun onGetTickerData(data: List<TickerEvent>)
}