package com.mufeng.mvvmlib.binding.thirdview

import androidx.databinding.BindingAdapter
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.scwang.smartrefresh.layout.api.RefreshLayout
import io.reactivex.functions.Consumer

interface SmartRefreshLayoutCommand : Consumer<RefreshLayout>

//上拉加载下拉刷新
@BindingAdapter(value = ["bind_smart_refresh_listener", "bind_smart_load_more_listener"], requireAll = false)
fun setOnRefreshListener(
    refreshLayout: SmartRefreshLayout,
    refreshCommand: SmartRefreshLayoutCommand?,
    loadMoreCommand: SmartRefreshLayoutCommand?
) {
    if (refreshCommand != null) {
        refreshLayout.setOnRefreshListener {
            refreshCommand.accept(it)
        }
    }
    if (loadMoreCommand != null) {
        refreshLayout.setOnLoadMoreListener {
            loadMoreCommand.accept(it)
        }
    }
}

//设置刷新布局的状态
@BindingAdapter(
    value = ["bind_smart_hasMoreData",
        "bind_smart_finishRefresh",
        "bind_smart_finishLoadMore",
        "bind_smart_autoRefresh",
        "bind_smart_enableLoadMore",
        "bind_smart_enableRefresh"
    ], requireAll = false
)
fun setSmartRefreshStatus(
    refreshLayout: SmartRefreshLayout,
    hasMoreData: Boolean?,
    finishRefresh: Boolean?,
    finishLoadMore: Boolean?,
    autoRefresh: Boolean?,
    enableLoadMore: Boolean?,
    enableRefresh: Boolean?
) {
    //设置是否有更多数据
    if (hasMoreData != null) {
        if (hasMoreData) {
            refreshLayout.setNoMoreData(false)
        } else {
            refreshLayout.finishLoadMoreWithNoMoreData()
        }
    }

    //设置结束下拉刷新
    if (finishRefresh != null) {
        refreshLayout.finishRefresh(finishRefresh)
    }

    //设置结束上拉加载
    if (finishLoadMore != null) {
        refreshLayout.finishLoadMore(finishLoadMore)
    }

    //设置自动下拉刷新
    if (autoRefresh != null) {
        refreshLayout.autoRefresh()
    }

    //是否允许上拉加载
    if (enableLoadMore != null) {
        refreshLayout.setEnableLoadMore(enableLoadMore)
    }

    //是否允许下拉刷新
    if (enableRefresh != null) {
        refreshLayout.setEnableRefresh(enableRefresh)
    }
}

