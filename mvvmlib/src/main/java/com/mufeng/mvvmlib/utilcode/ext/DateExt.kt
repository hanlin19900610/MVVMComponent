package com.mufeng.mvvmlib.utilcode.ext

import java.text.SimpleDateFormat
import java.util.*

/**
 * @创建者 MuFeng-T
 * @创建时间 2020/2/11 13:04
 * @描述
 */

const val PATTERN_1 = "yyyy-MM-dd HH:mm:ss"
const val PATTERN_2 = "yyyy-MM-dd HH:mm"
const val PATTERN_3 = "yyyy-MM-dd"
const val PATTERN_4 = "yyyy/MM/dd HH:mm:ss"
const val PATTERN_5 = "yyyy/MM/dd HH:mm"
const val PATTERN_6 = "yyyy/MM/dd"
const val PATTERN_7 = "yyyy年MM月dd日"
const val PATTERN_8 = "yyyyMMdd"
const val PATTERN_9 = "MM"
const val PATTERN_10 = "dd"
const val PATTERN_11 = "yyyy/MM"
const val PATTERN_12 = "yyyy-MM"

fun Date.formatDateStr(pattern: String = PATTERN_1): String {
    val df = SimpleDateFormat(pattern)
    return df.format(this)
}

/**
 * 格式化时间（输出类似于 刚刚, 4分钟前, 一小时前, 昨天这样的时间）
 *
 * @return
 */
fun String.formatDisplayTime(pattern: String = PATTERN_1): String{
    val df = SimpleDateFormat(pattern)
    return df.parse(this).formatDisplayTime()
}
fun Date.formatDisplayTime(): String {

    if (this == null) {
        return ""
    }

    var display = ""
    val tMin = 60 * 1000
    val tHour = 60 * tMin
    val tDay = 24 * tHour



    try {

        val today = Date()
        val thisYearDf = SimpleDateFormat("yyyy")
        val todayDf = SimpleDateFormat("yyyy-MM-dd")
        val thisYear = Date(thisYearDf.parse(thisYearDf.format(today)).time)
        val yesterday = Date(todayDf.parse(todayDf.format(today)).time)
        val beforeYes = Date(yesterday.time - tDay)
        if (this != null) {
            val halfDf = SimpleDateFormat("MM月dd日")
            val dTime = today.time - this.time
            if (this.before(thisYear)) {
                display = SimpleDateFormat("yyyy年MM月dd日").format(this)
            } else {

                if (dTime < tMin) {
                    display = "刚刚"
                } else if (dTime < tHour) {
                    display = Math.ceil((dTime / tMin).toDouble()).toInt().toString() + "分钟前"
                } else if (dTime < tDay && this.after(yesterday)) {
                    display = Math.ceil((dTime / tHour).toDouble()).toInt().toString() + "小时前"
                } else if (this.after(beforeYes) && this.before(yesterday)) {
                    display = "昨天  " + SimpleDateFormat("HH:mm").format(this)
                } else {
                    display = SimpleDateFormat("MM-dd HH:mm").format(this)
                }
            }
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }

    return display
}

fun String.getYear(pattern: String = "-"): Int{
    return this.split(pattern)[0].toInt()
}

fun String.getMonth(pattern: String = "-"): Int{
    return this.split(pattern)[1].toInt()
}

fun String.getDay(pattern: String = "-"): Int{
    return this.split(pattern)[2].toInt()
}