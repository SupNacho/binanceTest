package ru.supernacho.kt.binancetest.di

import io.reactivex.android.schedulers.AndroidSchedulers
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ru.supernacho.kt.binancetest.model.api.ApiConst
import ru.supernacho.kt.binancetest.model.api.ApiService
import ru.supernacho.kt.binancetest.model.api.BinanceApiService
import ru.supernacho.kt.binancetest.model.bus.EventBus
import ru.supernacho.kt.binancetest.model.bus.EventObserver
import ru.supernacho.kt.binancetest.model.bus.IEventBus
import ru.supernacho.kt.binancetest.model.bus.listeners.IEventObserver
import ru.supernacho.kt.binancetest.model.net.WsClient
import ru.supernacho.kt.binancetest.model.repository.INetRepository
import ru.supernacho.kt.binancetest.model.repository.NetRepository
import ru.supernacho.kt.binancetest.presenter.ExchangeInfoPresenter

val appMainModule = Kodein.Module("main_module", false){
    bind<IEventBus>() with singleton { EventBus() }
    bind<WsClient>() with singleton { WsClient(eventBus = instance()) }
    bind<IEventObserver>() with singleton { EventObserver(eventBus = instance(), uiScheduler = AndroidSchedulers.mainThread())}
    bind<GsonConverterFactory>() with singleton { GsonConverterFactory.create() }
    bind<Retrofit>() with singleton {
        Retrofit.Builder()
            .baseUrl(ApiConst.BINANCE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(instance())
            .build()
    }
    bind<BinanceApiService>() with singleton { ApiService.getApi(retrofit = instance()) }
    bind<INetRepository>() with singleton { NetRepository(apiService = instance(), wsClient = instance()) }

    bind() from provider { ExchangeInfoPresenter(uiScheduler = AndroidSchedulers.mainThread(), netRepository = instance(),
        eventObserver = instance()) }
}