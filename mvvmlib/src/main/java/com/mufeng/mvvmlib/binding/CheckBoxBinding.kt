package com.mufeng.mvvmlib.binding

import android.widget.CheckBox
import androidx.databinding.BindingAdapter

/**
 * @创建者 MuFeng-T
 * @创建时间 2019/10/15 22:16
 * @描述
 */

@BindingAdapter("onCheckedChange")
fun CheckBox.setCheckedChanged(changedCallback: BindingCommand1<Boolean>){
    setOnCheckedChangeListener { compoundButton, isChecked ->
        changedCallback.apply(isChecked)
    }
}