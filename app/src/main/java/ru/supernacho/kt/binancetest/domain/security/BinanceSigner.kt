package ru.supernacho.kt.binancetest.domain.security

import org.apache.commons.codec.binary.Hex
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec

object BinanceSigner {
    fun sign(message: String, secret: String): String {
        try {
            val sha256HMAC = Mac.getInstance("HmacSHA256")
            val secretKeySpec = SecretKeySpec(secret.toByteArray(), "HmacSHA256")
            sha256HMAC.init(secretKeySpec)
            return String(Hex.encodeHex(sha256HMAC.doFinal(message.toByteArray())))
        } catch (e: Exception) {
            throw RuntimeException("Unable to sign message.", e)
        }
    }
}