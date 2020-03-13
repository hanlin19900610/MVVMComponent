package com.mufeng.mvvmlib.binding

import android.view.View
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.databinding.BindingAdapter


/**
 * @创建者 MuFeng-T
 * @创建时间 2019/10/16 21:34
 * @描述
 */

/**
 *
 * @receiver RadioGroup
 * @param bindingCommand BindingCommand1<String>
 */
@BindingAdapter("onCheckedChangedCommand")
fun RadioGroup.onCheckedChangedCommand(bindingCommand: BindingCommand1<String>) {
    setOnCheckedChangeListener { group, checkedId ->
        val radioButton = group.findViewById<View>(checkedId) as RadioButton
        bindingCommand.apply(radioButton.text.toString())
    }
}