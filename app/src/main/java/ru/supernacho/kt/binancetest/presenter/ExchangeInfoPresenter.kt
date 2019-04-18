package ru.supernacho.kt.binancetest.presenter

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import ru.supernacho.kt.binancetest.model.bus.listeners.IEventObserver
import ru.supernacho.kt.binancetest.model.bus.listeners.ITickerListener
import ru.supernacho.kt.binancetest.model.entity.TickerEvent
import ru.supernacho.kt.binancetest.model.repository.INetRepository
import ru.supernacho.kt.binancetest.view.fragments.ExchangeInfoView
import ru.supernacho.kt.binancetest.view.uimodel.adapter.ExchangeInfoResponseAdapter
import ru.supernacho.kt.binancetest.view.uimodel.adapter.TickerEventAdapter
import ru.supernacho.kt.binancetest.view.uimodel.adapter.TickerResponseAdapter
import java.util.concurrent.TimeUnit

@InjectViewState
class ExchangeInfoPresenter(
    private val uiScheduler: Scheduler,
    private val netRepository: INetRepository,
    private val eventObserver: IEventObserver
) : MvpPresenter<ExchangeInfoView>(), ITickerListener {
    private val compositeDisposable = CompositeDisposable()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        eventObserver.subscribe(this)
        requestTicker24h()
    }

    fun requestTicker24h() {
        compositeDisposable.clear()
        val disposable = netRepository.getTicker24hr()
            .subscribeOn(Schedulers.io())
            .map { response -> response.map { e -> TickerResponseAdapter.adapt(e) } }
            .observeOn(uiScheduler)
            .repeatWhen { o -> o.delay(3, TimeUnit.SECONDS) }
            .doOnError { t -> viewState.onReceivingError(t) }
            .subscribe { l ->
                l?.let {
                    viewState.onReceiveTicker24hr(it)
                }
            }
        compositeDisposable.add(disposable)
    }

    fun requestExchangeInfo() {
        compositeDisposable.clear()
        val disposable = netRepository.getAllPairsInfo()
            .subscribeOn(Schedulers.io())
            .map { response -> response.symbols?.map { e -> ExchangeInfoResponseAdapter.adapt(e) } }
            .observeOn(uiScheduler)
            .repeatWhen { o -> o.delay(3, TimeUnit.SECONDS) }
            .subscribe { l ->
                l?.let {
                    viewState.onReceiveExchangeInfo(it)
                }
            }
        compositeDisposable.add(disposable)
    }

    override fun onGetTickerData(data: List<TickerEvent>) {
        val list = data.map { tv -> TickerEventAdapter.adapt(tv) }
        viewState.onReceiveTicker24hr(list)
    }

    override fun onDestroy() {
        compositeDisposable.clear()
        super.onDestroy()
    }
}