package com.mufeng.mvvmlib.binding

import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout



/**
 * @创建者 MuFeng-T
 * @创建时间 2019/10/16 21:47
 * @描述
 */

/**
 * 下拉刷新事件
 * @receiver SwipeRefreshLayout
 * @param bindingCommand BindingCommand
 */
@BindingAdapter("onRefreshCommand")
fun SwipeRefreshLayout.onRefreshCommand(bindingCommand: BindingCommand) {
    setOnRefreshListener {
        bindingCommand.apply()
    }
}

/**
 * 是否在刷新中
 * @receiver SwipeRefreshLayout
 * @param refreshing Boolean
 */
@BindingAdapter("refreshing")
fun SwipeRefreshLayout.setRefreshing(refreshing: Boolean) {
    isRefreshing = refreshing
}