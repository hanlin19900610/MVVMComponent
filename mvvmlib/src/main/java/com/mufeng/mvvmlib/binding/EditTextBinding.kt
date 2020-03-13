package com.mufeng.mvvmlib.binding

import android.content.Context.INPUT_METHOD_SERVICE
import android.view.inputmethod.InputMethodManager
import android.view.inputmethod.InputMethodManager.SHOW_IMPLICIT
import android.widget.EditText
import androidx.core.widget.doOnTextChanged
import androidx.databinding.BindingAdapter


/**
 * @创建者 MuFeng-T
 * @创建时间 2019/10/16 21:21
 * @描述
 */

/**
 * EditText重新获取焦点的事件绑定
 * @receiver EditText
 * @param needRequestFocus Boolean
 */
@BindingAdapter("requestFocus")
fun EditText.requestFocusCommand(needRequestFocus: Boolean){
    if (needRequestFocus) {
        setSelection(text.length)
        requestFocus()
        val imm =
            context.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(this, SHOW_IMPLICIT)
    }
    isFocusableInTouchMode = needRequestFocus
}

/**
 * EditText输入文字改变的监听
 * @receiver EditText
 * @param textChanged BindingCommand1<String>
 */
@BindingAdapter("textChanged")
fun EditText.addTextChangedListener(textChanged: BindingCommand1<String>) {
    doOnTextChanged { text, _, _, _ ->
        textChanged.apply(text.toString())
    }
}