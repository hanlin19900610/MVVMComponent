package com.mufeng.mvvmlib.utilcode.ext.widget.paging

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList

/**
 * @创建者 MuFeng-T
 * @创建时间 2020/1/17 10:21
 * @描述
 */
fun <Key, Value> DataSource.Factory<Key, Value>.toLiveDataPagedList(
    config: PagedList.Config? = null,
    boundaryCallback: PagedList.BoundaryCallback<Value>? = null
): LiveData<PagedList<Value>> {
    return LivePagedListBuilder<Key, Value>(
        this, config ?: PagedList.Config.Builder()
            .setInitialLoadSizeHint(PAGING_DEFAULT_INITIAL_LOAD_SIZE_HINT)
            .setPageSize(PAGING_DEFAULT_PAGE_SIZE)
            .setPrefetchDistance(PAGING_DEFAULT_PREFETCH_DISTANCE)
            .setEnablePlaceholders(false)
            .build()
    )
        .setBoundaryCallback(boundaryCallback)
        .build()

}

private const val PAGING_DEFAULT_PAGE_SIZE = 15
private const val PAGING_DEFAULT_PREFETCH_DISTANCE = 15
private const val PAGING_DEFAULT_INITIAL_LOAD_SIZE_HINT = 30