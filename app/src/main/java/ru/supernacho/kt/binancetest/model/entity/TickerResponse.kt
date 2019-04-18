package ru.supernacho.kt.binancetest.model.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class TickerResponse {

    @SerializedName("symbol")
    @Expose
    var symbol: String? = null
    @SerializedName("priceChange")
    @Expose
    var priceChange: String? = null
    @SerializedName("priceChangePercent")
    @Expose
    var priceChangePercent: String? = null
    @SerializedName("weightedAvgPrice")
    @Expose
    var weightedAvgPrice: String? = null
    @SerializedName("prevClosePrice")
    @Expose
    var prevClosePrice: String? = null
    @SerializedName("lastPrice")
    @Expose
    var lastPrice: String? = null
    @SerializedName("lastQty")
    @Expose
    var lastQty: String? = null
    @SerializedName("bidPrice")
    @Expose
    var bidPrice: String? = null
    @SerializedName("askPrice")
    @Expose
    var askPrice: String? = null
    @SerializedName("openPrice")
    @Expose
    var openPrice: String? = null
    @SerializedName("highPrice")
    @Expose
    var highPrice: String? = null
    @SerializedName("lowPrice")
    @Expose
    var lowPrice: String? = null
    @SerializedName("volume")
    @Expose
    var volume: String? = null
    @SerializedName("quoteVolume")
    @Expose
    var quoteVolume: String? = null
    @SerializedName("openTime")
    @Expose
    var openTime: Long? = null
    @SerializedName("closeTime")
    @Expose
    var closeTime: Long? = null
    @SerializedName("firstId")
    @Expose
    var firstId: Long? = null
    @SerializedName("lastId")
    @Expose
    var lastId: Long? = null
    @SerializedName("count")
    @Expose
    var count: Long? = null

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is TickerResponse) return false

        if (symbol != other.symbol) return false
        if (priceChange != other.priceChange) return false
        if (priceChangePercent != other.priceChangePercent) return false
        if (weightedAvgPrice != other.weightedAvgPrice) return false
        if (prevClosePrice != other.prevClosePrice) return false
        if (lastPrice != other.lastPrice) return false
        if (lastQty != other.lastQty) return false
        if (bidPrice != other.bidPrice) return false
        if (askPrice != other.askPrice) return false
        if (openPrice != other.openPrice) return false
        if (highPrice != other.highPrice) return false
        if (lowPrice != other.lowPrice) return false
        if (volume != other.volume) return false
        if (quoteVolume != other.quoteVolume) return false
        if (openTime != other.openTime) return false
        if (closeTime != other.closeTime) return false
        if (firstId != other.firstId) return false
        if (lastId != other.lastId) return false
        if (count != other.count) return false

        return true
    }

    override fun hashCode(): Int {
        var result = symbol?.hashCode() ?: 0
        result = 31 * result + (priceChange?.hashCode() ?: 0)
        result = 31 * result + (priceChangePercent?.hashCode() ?: 0)
        result = 31 * result + (weightedAvgPrice?.hashCode() ?: 0)
        result = 31 * result + (prevClosePrice?.hashCode() ?: 0)
        result = 31 * result + (lastPrice?.hashCode() ?: 0)
        result = 31 * result + (lastQty?.hashCode() ?: 0)
        result = 31 * result + (bidPrice?.hashCode() ?: 0)
        result = 31 * result + (askPrice?.hashCode() ?: 0)
        result = 31 * result + (openPrice?.hashCode() ?: 0)
        result = 31 * result + (highPrice?.hashCode() ?: 0)
        result = 31 * result + (lowPrice?.hashCode() ?: 0)
        result = 31 * result + (volume?.hashCode() ?: 0)
        result = 31 * result + (quoteVolume?.hashCode() ?: 0)
        result = 31 * result + (openTime?.hashCode() ?: 0)
        result = 31 * result + (closeTime?.hashCode() ?: 0)
        result = 31 * result + (firstId?.hashCode() ?: 0)
        result = 31 * result + (lastId?.hashCode() ?: 0)
        result = 31 * result + (count?.hashCode() ?: 0)
        return result
    }


}