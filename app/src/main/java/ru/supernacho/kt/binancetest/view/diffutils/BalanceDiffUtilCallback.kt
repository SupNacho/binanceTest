package ru.supernacho.kt.binancetest.view.diffutils

import android.support.v7.util.DiffUtil
import ru.supernacho.kt.binancetest.model.entity.Balance
import ru.supernacho.kt.binancetest.view.uimodel.ExInfoUiModel

class BalanceDiffUtilCallback(
    private val oldList: List<Balance>,
    private val newList: List<Balance>
) : DiffUtil.Callback(){
    override fun areItemsTheSame(oldPos: Int, newPos: Int) = oldList[oldPos].asset == newList[newPos].asset

    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areContentsTheSame(oldPos: Int, newPos: Int) = oldList[oldPos] == newList[newPos]
}