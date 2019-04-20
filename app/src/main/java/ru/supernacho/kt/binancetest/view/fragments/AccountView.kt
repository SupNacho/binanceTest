package ru.supernacho.kt.binancetest.view.fragments

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import ru.supernacho.kt.binancetest.model.entity.AccountResponse

@StateStrategyType(value = AddToEndSingleStrategy::class)
interface AccountView: MvpView {
    fun onReceiveAccountData(uiModel: AccountResponse)
    fun onReceiveError(error: Throwable)
}