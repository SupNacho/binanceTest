package ru.supernacho.kt.binancetest.model.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class Symbol {

    @SerializedName("symbol")
    @Expose
    var symbol: String? = null
    @SerializedName("status")
    @Expose
    var status: String? = null
    @SerializedName("baseAsset")
    @Expose
    var baseAsset: String? = null
    @SerializedName("baseAssetPrecision")
    @Expose
    var baseAssetPrecision: Long? = null
    @SerializedName("quoteAsset")
    @Expose
    var quoteAsset: String? = null
    @SerializedName("quotePrecision")
    @Expose
    var quotePrecision: Long? = null
    @SerializedName("orderTypes")
    @Expose
    var orderTypes: List<String>? = null
    @SerializedName("icebergAllowed")
    @Expose
    var icebergAllowed: Boolean? = null
    @SerializedName("isSpotTradingAllowed")
    @Expose
    var isSpotTradingAllowed: Boolean? = null
    @SerializedName("isMarginTradingAllowed")
    @Expose
    var isMarginTradingAllowed: Boolean? = null
    @SerializedName("filters")
    @Expose
    var filters: List<Filter>? = null

}