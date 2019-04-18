package ru.supernacho.kt.binancetest

import android.app.Application
import com.squareup.leakcanary.LeakCanary
import io.reactivex.plugins.RxJavaPlugins
import timber.log.Timber

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        instance = this
        if (LeakCanary.isInAnalyzerProcess(this))
            return
        LeakCanary.install(this)

        Timber.plant(Timber.DebugTree())

        RxJavaPlugins.setErrorHandler { e ->
            Thread.currentThread().uncaughtExceptionHandler.uncaughtException(mainLooper.thread, e)
        }

    }

    companion object {
        lateinit var instance: App
            private set
    }
}