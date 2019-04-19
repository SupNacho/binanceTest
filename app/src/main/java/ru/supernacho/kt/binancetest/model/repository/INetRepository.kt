package ru.supernacho.kt.binancetest.model.repository

import io.reactivex.Single
import ru.supernacho.kt.binancetest.model.entity.AccountResponse
import ru.supernacho.kt.binancetest.model.entity.TickerResponse

interface INetRepository {
    fun getTicker24hr() : Single<List<TickerResponse>>
    fun getAccountData(): Single<AccountResponse>
}