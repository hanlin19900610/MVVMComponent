package com.mufeng.mvvmlib.utilcode.ext.listener

import android.text.Editable
import android.text.TextWatcher
import android.widget.TextView

/**
 * @创建者 MuFeng-T
 * @创建时间 2020/3/6 16:16
 * @描述
 */

/**
 * Add a DSL listener to simplify [TextView.addTextChangedListener]
 */
fun TextView.textWatcher(watcher: TextWatcherKtx.() -> Unit) =
    addTextChangedListener(TextWatcherKtx().apply(watcher))

class TextWatcherKtx : TextWatcher {

    private var _afterTextChanged: ((Editable?) -> Unit)? = null
    private var _beforeTextChanged: ((CharSequence?, Int, Int, Int) -> Unit)? = null
    private var _onTextChanged: ((CharSequence?, Int, Int, Int) -> Unit)? = null


    fun afterTextChanged(listener: ((Editable?) -> Unit)) {
        _afterTextChanged = listener
    }

    fun beforeTextChanged(listener: (CharSequence?, Int, Int, Int) -> Unit) {
        _beforeTextChanged = listener
    }

    fun onTextChanged(listener: (CharSequence?, Int, Int, Int) -> Unit) {
        _onTextChanged = listener
    }

    override fun afterTextChanged(s: Editable?) {
        _afterTextChanged?.invoke(s)
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        _beforeTextChanged?.invoke(s, start, count, after)
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        _onTextChanged?.invoke(s, start, before, count)
    }

}