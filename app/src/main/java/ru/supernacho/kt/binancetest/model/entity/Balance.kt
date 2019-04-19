package ru.supernacho.kt.binancetest.model.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class Balance {

    @SerializedName("asset")
    @Expose
    var asset: String? = null
    @SerializedName("free")
    @Expose
    var free: String? = null
    @SerializedName("locked")
    @Expose
    var locked: String? = null

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Balance) return false

        if (asset != other.asset) return false
        if (free != other.free) return false
        if (locked != other.locked) return false

        return true
    }

    override fun hashCode(): Int {
        var result = asset?.hashCode() ?: 0
        result = 31 * result + (free?.hashCode() ?: 0)
        result = 31 * result + (locked?.hashCode() ?: 0)
        return result
    }

}