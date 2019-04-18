package ru.supernacho.kt.binancetest.model.net

import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.WebSocket
import ru.supernacho.kt.binancetest.model.bus.EventBus
import ru.supernacho.kt.binancetest.model.bus.IEventBus
import java.util.concurrent.TimeUnit

class WsClient(private val eventBus: IEventBus) {
    private var client: OkHttpClient? = null
    private var webSocket: WebSocket? = null


    fun onStartConnection(){
        client = initClient()
        val request = Request.Builder().url("wss://stream.binance.com:9443/ws/!ticker@arr").build()
        webSocket = client?.newWebSocket(request, WsListener(eventBus))

    }

    private fun initClient() = OkHttpClient.Builder()
        .connectTimeout(15, TimeUnit.SECONDS)
        .readTimeout(15, TimeUnit.MINUTES)
        .retryOnConnectionFailure(true)
        .build()
}