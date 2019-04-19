package ru.supernacho.kt.binancetest.presenter

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import ru.supernacho.kt.binancetest.model.repository.INetRepository
import ru.supernacho.kt.binancetest.view.fragments.ExchangeInfoView
import ru.supernacho.kt.binancetest.view.uimodel.adapter.TickerResponseAdapter
import java.util.concurrent.TimeUnit

@InjectViewState
class ExchangeInfoPresenter(
    private val uiScheduler: Scheduler,
    private val netRepository: INetRepository
) : MvpPresenter<ExchangeInfoView>() {
    private val compositeDisposable = CompositeDisposable()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        requestTicker24h()
    }

    fun requestTicker24h() {
        compositeDisposable.clear()
        val disposable = netRepository.getTicker24hr()
            .subscribeOn(Schedulers.io())
            .map { response -> response.map { e -> TickerResponseAdapter.adapt(e) } }
            .observeOn(uiScheduler)
            .repeatWhen { o -> o.delay(2600L, TimeUnit.MILLISECONDS) } //2600 ms delay for repeat request + ~400ms for response give ~3000ms for screen update
            .doOnError { t -> viewState.onReceivingError(t) }
            .subscribe { l ->
                l?.let {
                    viewState.onReceiveTicker24hr(it)
                }
            }
        compositeDisposable.add(disposable)
    }

    override fun onDestroy() {
        compositeDisposable.clear()
        super.onDestroy()
    }
}