package com.mufeng.mvvmlib.basic

import androidx.appcompat.app.AppCompatActivity

/**
 * @author MuFeng-T
 * @createTime 2019-10-23
 * @details
 */

sealed class UIChange{
    data class ToastEvent(val msg: String): UIChange()
    object FinishEvent : UIChange()
    class IntentEvent(
        var clzz: Class<out AppCompatActivity>,
        val isFinished: Boolean = false,
        val params: Array<out Pair<String, Any?>> = arrayOf()
    ): UIChange()
}