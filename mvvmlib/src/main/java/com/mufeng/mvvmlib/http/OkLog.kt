package com.mufeng.mvvmlib.http

import android.text.TextUtils
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import timber.log.Timber

/**
 * @创建者 MuFeng-T
 * @创建时间 2020/2/21 17:08
 * @描述
 */
object OkLog {
    private val TAG = "OKHttp"
    private val LINE_SEPARATOR = System.getProperty("line.separator")
    private val JSON_INDENT = 4
    private val SUFFIX = ".kt"


    fun log(msg: String) {
        var msg = msg
        if (TextUtils.isEmpty(msg)) {
            return
        }
        msg = msg.trim { it <= ' ' }
        val message: String
        message = try {
            when {
                msg.startsWith("{") -> {
                    val jsonObject = JSONObject(msg)
                    jsonObject.toString(JSON_INDENT)
                }
                msg.startsWith("[") -> {
                    val jsonArray = JSONArray(msg)
                    jsonArray.toString(JSON_INDENT)
                }
                else -> msg
            }
        } catch (e: JSONException) {
            msg
        }

        val lines =
            LINE_SEPARATOR?.toRegex()?.let { message.split(it).dropLastWhile { it.isEmpty() }.toTypedArray() }
        for (line in lines!!) {
            Timber.tag(TAG).e("║ $line")
        }
    }

    fun start(hint: String) {
        printLine(TAG, true, hint)
        log(getStackTrace() + LINE_SEPARATOR)
    }

    fun end(hint: String) {
        printLine(TAG, false, hint)
    }

    private fun printLine(tag: String, isTop: Boolean, hint: String) {
        var hint = hint
        val top = "╔════════ %s ══════════════════════════════════"
        val bot = "╚════════ %s ══════════════════════════════════"
        hint = if (TextUtils.isEmpty(hint)) "════════" else hint
        val fotmated = String.format(if (isTop) top else bot, hint)
        Timber.tag(tag).e(fotmated)
    }


    private fun getStackTrace(): String {
        val STACK_TRACE_INDEX_4 = 4
        val stackTrace = Thread.currentThread().stackTrace

        val targetElement = stackTrace[STACK_TRACE_INDEX_4]
        var className = targetElement.className
        val classNameInfo =
            className.split("\\.".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        if (classNameInfo.isNotEmpty()) {
            className = classNameInfo[classNameInfo.size - 1] + SUFFIX
        }

        if (className.contains("$")) {
            className =
                className.split("\\$".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()[0] + SUFFIX
        }

        val methodName = targetElement.methodName
        var lineNumber = targetElement.lineNumber

        if (lineNumber < 0) {
            lineNumber = 0
        }

        return "[ ($className:$lineNumber)#$methodName ] "
    }


}