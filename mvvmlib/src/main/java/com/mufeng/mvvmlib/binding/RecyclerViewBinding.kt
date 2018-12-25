package com.mufeng.mvvmlib.binding

import android.annotation.SuppressLint
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jakewharton.rxbinding3.recyclerview.scrollStateChanges
import java.util.concurrent.TimeUnit

@SuppressLint("CheckResult")
@BindingAdapter(
        "bind_scrollStateChanges",
        "bind_scrollStateChanges_debounce",
        requireAll = false
)
fun setScrollStateChanges(recyclerView: RecyclerView,
                          listener: ScrollStateChangesListener,
                          debounce: Long = 500) {
    recyclerView.scrollStateChanges()
            .debounce(debounce, TimeUnit.MILLISECONDS)
            .subscribe { state ->
                listener(state)
            }
}

typealias ScrollStateChangesListener = (Int) -> Unit