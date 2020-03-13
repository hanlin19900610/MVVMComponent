package com.mufeng.mvvmlib.binding

import android.widget.Switch
import androidx.databinding.BindingAdapter



/**
 * @创建者 MuFeng-T
 * @创建时间 2019/10/16 21:32
 * @描述
 */

/**
 * 设置开关状态
 * @receiver Switch
 * @param isChecked Boolean
 */
@BindingAdapter("switchState")
fun Switch.setSwitchState(isChecked: Boolean){
    setChecked(isChecked)
}

/**
 *  Switch的状态改变监听
 * @receiver Switch
 * @param changeListener BindingCommand1<Boolean>
 */
@BindingAdapter("onCheckedChangeCommand")
fun Switch.onCheckedChangeCommand(changeListener: BindingCommand1<Boolean>) {
    setOnCheckedChangeListener { _, isChecked -> changeListener.apply(isChecked) }
}