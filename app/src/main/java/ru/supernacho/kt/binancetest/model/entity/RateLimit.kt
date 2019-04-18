package ru.supernacho.kt.binancetest.model.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class RateLimit {

    @SerializedName("rateLimitType")
    @Expose
    var rateLimitType: String? = null
    @SerializedName("interval")
    @Expose
    var interval: String? = null
    @SerializedName("intervalNum")
    @Expose
    var intervalNum: Long? = null
    @SerializedName("limit")
    @Expose
    var limit: Long? = null

}