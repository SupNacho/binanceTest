package ru.supernacho.kt.binancetest.model.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class AccountResponse {

    @SerializedName("makerCommission")
    @Expose
    var makerCommission: Long? = null
    @SerializedName("takerCommission")
    @Expose
    var takerCommission: Long? = null
    @SerializedName("buyerCommission")
    @Expose
    var buyerCommission: Long? = null
    @SerializedName("sellerCommission")
    @Expose
    var sellerCommission: Long? = null
    @SerializedName("canTrade")
    @Expose
    var canTrade: Boolean? = null
    @SerializedName("canWithdraw")
    @Expose
    var canWithdraw: Boolean? = null
    @SerializedName("canDeposit")
    @Expose
    var canDeposit: Boolean? = null
    @SerializedName("updateTime")
    @Expose
    var updateTime: Long? = null
    @SerializedName("balances")
    @Expose
    var balances: List<Balance>? = null

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is AccountResponse) return false

        if (makerCommission != other.makerCommission) return false
        if (takerCommission != other.takerCommission) return false
        if (buyerCommission != other.buyerCommission) return false
        if (sellerCommission != other.sellerCommission) return false
        if (canTrade != other.canTrade) return false
        if (canWithdraw != other.canWithdraw) return false
        if (canDeposit != other.canDeposit) return false
        if (updateTime != other.updateTime) return false
        if (balances != other.balances) return false

        return true
    }

    override fun hashCode(): Int {
        var result = makerCommission?.hashCode() ?: 0
        result = 31 * result + (takerCommission?.hashCode() ?: 0)
        result = 31 * result + (buyerCommission?.hashCode() ?: 0)
        result = 31 * result + (sellerCommission?.hashCode() ?: 0)
        result = 31 * result + (canTrade?.hashCode() ?: 0)
        result = 31 * result + (canWithdraw?.hashCode() ?: 0)
        result = 31 * result + (canDeposit?.hashCode() ?: 0)
        result = 31 * result + (updateTime?.hashCode() ?: 0)
        result = 31 * result + (balances?.hashCode() ?: 0)
        return result
    }


}