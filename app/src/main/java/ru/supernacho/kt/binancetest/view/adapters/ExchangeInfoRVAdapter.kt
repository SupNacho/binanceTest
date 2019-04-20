package ru.supernacho.kt.binancetest.view.adapters

import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_exchange_info.view.*
import ru.supernacho.kt.binancetest.R
import ru.supernacho.kt.binancetest.view.diffutils.TickerDiffUtilCallback
import ru.supernacho.kt.binancetest.view.uimodel.ExInfoUiModel

class ExchangeInfoRVAdapter : BaseRVAdapter<ExInfoUiModel>() {

    fun setData(list: List<ExInfoUiModel>){
        val diffResult = DiffUtil.calculateDiff(TickerDiffUtilCallback(dataList, list), true)
        dataList = list
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_exchange_info, parent, false)
        return object: RecyclerView.ViewHolder(view){}
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val pair = dataList[position]
        holder.itemView.tv_ItemExchangeInfoPair.text = pair.symbol
        holder.itemView.tv_ItemExInfoAvgPrice.text = pair.avgPrice
        holder.itemView.tv_ItemExInfoPriceChangePercent.text = pair.percentChange
    }
}