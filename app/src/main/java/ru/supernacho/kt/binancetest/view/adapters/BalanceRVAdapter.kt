package ru.supernacho.kt.binancetest.view.adapters

import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_balance_info.view.*
import ru.supernacho.kt.binancetest.R
import ru.supernacho.kt.binancetest.model.entity.Balance
import ru.supernacho.kt.binancetest.view.diffutils.BalanceDiffUtilCallback

class BalanceRVAdapter : BaseRVAdapter<Balance>() {

    fun setData(list: List<Balance>){
        val diffResult = DiffUtil.calculateDiff(BalanceDiffUtilCallback(dataList, list), false)
        dataList = list
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_balance_info, parent, false)
        return object: RecyclerView.ViewHolder(view){}
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val balance = dataList[position]
        holder.itemView.tv_ItemBalanceCurrency.text = balance.asset
        holder.itemView.tv_ItemBalanceFree.text = balance.free
        holder.itemView.tv_ItemBalanceLocked.text = balance.locked
    }
}