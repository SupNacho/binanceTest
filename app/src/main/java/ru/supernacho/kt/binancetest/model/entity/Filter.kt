package ru.supernacho.kt.binancetest.model.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class Filter {

    @SerializedName("filterType")
    @Expose
    var filterType: String? = null
    @SerializedName("minPrice")
    @Expose
    var minPrice: String? = null
    @SerializedName("maxPrice")
    @Expose
    var maxPrice: String? = null
    @SerializedName("tickSize")
    @Expose
    var tickSize: String? = null
    @SerializedName("multiplierUp")
    @Expose
    var multiplierUp: String? = null
    @SerializedName("multiplierDown")
    @Expose
    var multiplierDown: String? = null
    @SerializedName("avgPriceMins")
    @Expose
    var avgPriceMins: Long? = null
    @SerializedName("minQty")
    @Expose
    var minQty: String? = null
    @SerializedName("maxQty")
    @Expose
    var maxQty: String? = null
    @SerializedName("stepSize")
    @Expose
    var stepSize: String? = null
    @SerializedName("minNotional")
    @Expose
    var minNotional: String? = null
    @SerializedName("applyToMarket")
    @Expose
    var applyToMarket: Boolean? = null
    @SerializedName("limit")
    @Expose
    var limit: Long? = null
    @SerializedName("maxNumAlgoOrders")
    @Expose
    var maxNumAlgoOrders: Long? = null

}