package ru.supernacho.kt.binancetest.model.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class TickerEvent {

    @SerializedName("e")
    @Expose
    var eventType: String? = null
    @SerializedName("E")
    @Expose
    var eventTime: Long? = null
    @SerializedName("s")
    @Expose
    var symbol: String? = null
    @SerializedName("p")
    @Expose
    var priceChange: String? = null
    @SerializedName("P")
    @Expose
    var priceChangePercent: String? = null
    @SerializedName("w")
    @Expose
    var weightAvgPrice: String? = null
    @SerializedName("x")
    @Expose
    var firstTrade: String? = null
    @SerializedName("c")
    @Expose
    var lastPrice: String? = null
    @SerializedName("Q")
    @Expose
    var lastQuantity: String? = null
    @SerializedName("b")
    @Expose
    var bestBidPrice: String? = null
    @SerializedName("B")
    @Expose
    var bestBidQuantity: String? = null
    @SerializedName("a")
    @Expose
    var bestAskPrice: String? = null
    @SerializedName("A")
    @Expose
    var bestAskQuantity: String? = null
    @SerializedName("o")
    @Expose
    var openPrice: String? = null
    @SerializedName("h")
    @Expose
    var highPrice: String? = null
    @SerializedName("l")
    @Expose
    var lowPrice: String? = null
    @SerializedName("v")
    @Expose
    var v: String? = null
    @SerializedName("q")
    @Expose
    var q: String? = null
    @SerializedName("O")
    @Expose
    var statOpenTime: Long? = null
    @SerializedName("C")
    @Expose
    var statCloseTime: Long? = null
    @SerializedName("F")
    @Expose
    var f: Long? = null
    @SerializedName("L")
    @Expose
    var ll: Long? = null
    @SerializedName("n")
    @Expose
    var n: Long? = null

}