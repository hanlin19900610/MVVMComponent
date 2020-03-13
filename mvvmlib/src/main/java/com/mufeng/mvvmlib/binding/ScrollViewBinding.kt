package com.mufeng.mvvmlib.binding

import android.widget.ScrollView
import androidx.core.widget.NestedScrollView
import androidx.databinding.BindingAdapter


/**
 * @创建者 MuFeng-T
 * @创建时间 2019/10/16 21:40
 * @描述
 */

/**
 * NestedScrollView 滑动监听
 * @receiver NestedScrollView
 * @param onScrollChangeCommand BindingCommand1<NestScrollDataWrapper>
 */
@BindingAdapter("onScrollChangeCommand")
fun NestedScrollView.onScrollChangeCommand(onScrollChangeCommand: BindingCommand1<NestScrollDataWrapper>) {
    setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { _, scrollX, scrollY, oldScrollX, oldScrollY ->
        onScrollChangeCommand.apply(
            NestScrollDataWrapper(scrollX, scrollY, oldScrollX, oldScrollY)
        )
    })
}

/**
 * ScrollView 滑动监听
 * @receiver ScrollView
 * @param onScrollChangeCommand BindingCommand1<ScrollDataWrapper>
 */
@BindingAdapter("onScrollChangeCommand")
fun ScrollView.onScrollChangeCommand(onScrollChangeCommand: BindingCommand1<ScrollDataWrapper>) {
    viewTreeObserver.addOnScrollChangedListener {
        onScrollChangeCommand.apply(
            ScrollDataWrapper(
                scrollX,
                scrollY
            )
        )
    }
}

data class NestScrollDataWrapper(
    val scrollX: Int,
    val scrollY: Int,
    val oldScrollX: Int,
    val oldScrollY: Int
)

data class ScrollDataWrapper(
    val scrollX: Int,
    val scrollY: Int
)