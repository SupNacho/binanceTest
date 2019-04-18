package ru.supernacho.kt.binancetest.model.bus

import io.reactivex.subjects.PublishSubject

class EventBus: IEventBus {
    private val eventBus = PublishSubject.create<String>()

    override fun onNext(message: String) {
        eventBus.onNext(message)
    }

    override fun subscribe(): PublishSubject<String> {
        return eventBus
    }
}