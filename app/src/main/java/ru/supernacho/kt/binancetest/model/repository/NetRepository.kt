package ru.supernacho.kt.binancetest.model.repository

import io.reactivex.Single
import ru.supernacho.kt.binancetest.domain.security.BinanceSigner
import ru.supernacho.kt.binancetest.model.api.ApiConst
import ru.supernacho.kt.binancetest.model.api.BinanceApiService
import ru.supernacho.kt.binancetest.model.entity.AccountResponse

class NetRepository(private val apiService: BinanceApiService) : INetRepository {

    override fun getTicker24hr() = apiService.getTicker24hr()

    override fun getAccountData(): Single<AccountResponse>{
        val time = System.currentTimeMillis()
        val signature = BinanceSigner.sign("timestamp=$time&recvWindow=${ApiConst.recvWindow}", ApiConst.secretKey)
        return apiService.getAccountData(header = ApiConst.publicKey, recvWindow = ApiConst.recvWindow, timeStamp = time, signature = signature)
    }
}