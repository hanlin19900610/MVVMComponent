package com.mufeng.mvvmlib.utilcode.utils.span

import android.graphics.Canvas
import android.graphics.Paint
import android.text.style.ReplacementSpan

/**
 * @创建者 MuFeng-T
 * @创建时间 2020/3/6 15:32
 * @描述
 */
class BlockLineSpanKtx (private val mHeight: Int = 0) : ReplacementSpan() {


    override fun getSize(paint: Paint, text: CharSequence?, start: Int, end: Int, fm: Paint.FontMetricsInt?): Int {
        fm?.let {
            it.ascent = -mHeight
            it.top = it.ascent
            it.descent = -it.ascent
            it.bottom = -it.ascent
        }
        return 0
    }

    override fun draw(
        canvas: Canvas,
        text: CharSequence?,
        start: Int,
        end: Int,
        x: Float,
        top: Int,
        y: Int,
        bottom: Int,
        paint: Paint
    ) {
    }
}