package ru.supernacho.kt.binancetest.view.adapters

import android.support.v7.widget.RecyclerView

abstract class BaseRVAdapter<T> : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var dataList: List<T> = ArrayList()

    override fun getItemCount() = dataList.size
}