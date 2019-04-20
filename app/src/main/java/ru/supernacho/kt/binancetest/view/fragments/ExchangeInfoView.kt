package ru.supernacho.kt.binancetest.view.fragments

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import ru.supernacho.kt.binancetest.view.uimodel.ExInfoUiModel

@StateStrategyType(value = AddToEndSingleStrategy::class)
interface ExchangeInfoView: MvpView {
    fun onReceiveTicker24hr(response: List<ExInfoUiModel>)
    fun onReceivingError(t: Throwable)
}