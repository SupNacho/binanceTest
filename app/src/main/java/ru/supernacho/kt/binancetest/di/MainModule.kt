package ru.supernacho.kt.binancetest.di

import io.reactivex.android.schedulers.AndroidSchedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ru.supernacho.kt.binancetest.BuildConfig
import ru.supernacho.kt.binancetest.model.api.ApiConst
import ru.supernacho.kt.binancetest.model.api.ApiService
import ru.supernacho.kt.binancetest.model.api.BinanceApiService
import ru.supernacho.kt.binancetest.model.repository.INetRepository
import ru.supernacho.kt.binancetest.model.repository.NetRepository
import ru.supernacho.kt.binancetest.presenter.AccountPresenter
import ru.supernacho.kt.binancetest.presenter.ExchangeInfoPresenter

val appMainModule = Kodein.Module("main_module", false){
    bind<GsonConverterFactory>() with singleton { GsonConverterFactory.create() }
    bind<Retrofit>() with singleton {
        Retrofit.Builder()
            .baseUrl(ApiConst.BINANCE_URL)
            .client(OkHttpClient.Builder().apply {
                addInterceptor(
                    HttpLoggingInterceptor().apply {
                        if (BuildConfig.DEBUG) {
                            level = HttpLoggingInterceptor.Level.BODY
                        }
                    }
                )
            }.build())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(instance())
            .build()
    }
    bind<BinanceApiService>() with singleton { ApiService.getApi(retrofit = instance()) }
    bind<INetRepository>() with singleton { NetRepository(apiService = instance()) }

    bind() from provider { ExchangeInfoPresenter(uiScheduler = AndroidSchedulers.mainThread(), netRepository = instance())}
    bind() from provider { AccountPresenter(uiScheduler = AndroidSchedulers.mainThread(), netRepository = instance())}
}