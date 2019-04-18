package ru.supernacho.kt.binancetest.model.net

import com.google.gson.Gson
import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener
import ru.supernacho.kt.binancetest.model.bus.IEventBus

class WsListener(private val eventBus: IEventBus): WebSocketListener() {

    override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
        eventBus.onNext(Gson().toJson(t))

    }

    override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
        webSocket.close(code, reason)
    }

    override fun onMessage(webSocket: WebSocket, text: String) {
        eventBus.onNext(text)
    }
}