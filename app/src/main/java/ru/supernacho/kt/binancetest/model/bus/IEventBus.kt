package ru.supernacho.kt.binancetest.model.bus

import io.reactivex.subjects.PublishSubject

interface IEventBus {
    fun onNext(message: String)
    fun subscribe(): PublishSubject<String>
}