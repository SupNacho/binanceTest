package ru.supernacho.kt.binancetest.model.bus.listeners

interface IEventObserver {
    fun subscribe(subscriber: IEventListener)
    fun unSubscribe(subscriber: IEventListener)
}