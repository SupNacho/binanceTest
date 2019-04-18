package ru.supernacho.kt.binancetest.model.bus

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.reactivex.Scheduler
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import ru.supernacho.kt.binancetest.model.bus.listeners.IEventListener
import ru.supernacho.kt.binancetest.model.bus.listeners.IEventObserver
import ru.supernacho.kt.binancetest.model.bus.listeners.ITickerListener
import ru.supernacho.kt.binancetest.model.entity.TickerEvent
import timber.log.Timber
import java.lang.reflect.Type

class EventObserver(eventBus: IEventBus, private val uiScheduler: Scheduler): IEventObserver {
    private val observer = object : DisposableObserver<List<TickerEvent>>() {
        override fun onComplete() {
            Timber.d("Event onComplete produced")
        }

        override fun onNext(t: List<TickerEvent>) {
            dispatchAnswer(t)
        }

        override fun onError(e: Throwable) {
            Timber.e(e)
        }
    }
    private val gson = Gson()
    private val listeners = ArrayList<IEventListener>()
    init {
        eventBus.subscribe()
            .subscribeOn(Schedulers.io())
            .map { t: String -> parseAnswer(t) }
            .observeOn(uiScheduler)
            .subscribe(observer)
    }

    private fun parseAnswer(answer: String): List<TickerEvent>{
        val listType: Type = object: TypeToken<List<TickerEvent>>(){}.type
        return gson.fromJson<List<TickerEvent>>(answer, listType)
    }

    private fun dispatchAnswer(t: List<TickerEvent>){
        listeners.forEach { e -> (e as? ITickerListener)?.onGetTickerData(t) }
    }

    private fun subscribeDispatcher(subscriber: IEventListener, isUnSubscribe: Boolean = false){
        if (isUnSubscribe)
            listeners.remove(subscriber)
        else
            listeners.add(subscriber)
    }

    override fun subscribe(subscriber: IEventListener){
        subscribeDispatcher(subscriber)
    }

    override fun unSubscribe(subscriber: IEventListener){
        subscribeDispatcher(subscriber, true)
    }
}