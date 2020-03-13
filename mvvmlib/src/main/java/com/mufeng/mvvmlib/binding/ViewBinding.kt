package com.mufeng.mvvmlib.binding

import android.view.View
import androidx.databinding.BindingAdapter

/**
 * @创建者 MuFeng-T
 * @创建时间 2019/10/16 21:50
 * @描述
 */

//防重复点击间隔(毫秒)
val CLICK_INTERVAL = 500

/**
 * 点击事件， 可以处理防抖动 需要后期处理
 * @receiver View
 * @param bindingCommand BindingCommand
 */
@BindingAdapter("onClickCommand")
fun View.onClickCommand(bindingCommand: BindingCommand){

}

/**
 * 长按点击事件
 * @receiver View
 * @param bindingCommand BindingCommand
 */
@BindingAdapter("onLongClickCommand")
fun View.onLongClickCommand(bindingCommand: BindingCommand) {
    setOnLongClickListener {
        bindingCommand.apply()
        true
    }
}