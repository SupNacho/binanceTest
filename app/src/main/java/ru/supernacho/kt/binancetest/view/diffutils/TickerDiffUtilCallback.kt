package ru.supernacho.kt.binancetest.view.diffutils

import android.support.v7.util.DiffUtil
import ru.supernacho.kt.binancetest.view.uimodel.ExInfoUiModel

class TickerDiffUtilCallback(
    private val oldList: List<ExInfoUiModel>,
    private val newList: List<ExInfoUiModel>
) : DiffUtil.Callback(){
    override fun areItemsTheSame(oldPos: Int, newPos: Int) = oldList[oldPos].symbol == newList[newPos].symbol

    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areContentsTheSame(oldPos: Int, newPos: Int) = oldList[oldPos] == newList[newPos]
}