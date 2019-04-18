package ru.supernacho.kt.binancetest.model.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class Response {

    @SerializedName("timezone")
    @Expose
    var timezone: String? = null
    @SerializedName("serverTime")
    @Expose
    var serverTime: Long? = null
    @SerializedName("rateLimits")
    @Expose
    var rateLimits: List<RateLimit>? = null
    @SerializedName("exchangeFilters")
    @Expose
    var exchangeFilters: List<Any>? = null
    @SerializedName("symbols")
    @Expose
    var symbols: List<Symbol>? = null

}