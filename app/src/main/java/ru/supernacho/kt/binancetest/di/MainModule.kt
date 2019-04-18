package ru.supernacho.kt.binancetest.di

import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ru.supernacho.kt.binancetest.model.api.ApiConst
import ru.supernacho.kt.binancetest.model.api.ApiService
import ru.supernacho.kt.binancetest.model.api.BinanceApiService

val appMainModule = Kodein.Module("main_module", false){
    bind<GsonConverterFactory>() with singleton { GsonConverterFactory.create() }
    bind<Retrofit>() with singleton {
        Retrofit.Builder()
            .baseUrl(ApiConst.BINANCE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(instance())
            .build()
    }
    bind<BinanceApiService>() with singleton { ApiService.getApi(instance()) }
}