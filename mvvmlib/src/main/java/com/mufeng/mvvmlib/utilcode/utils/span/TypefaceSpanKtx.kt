package com.mufeng.mvvmlib.utilcode.utils.span

import android.graphics.Paint
import android.graphics.Typeface
import android.os.Parcel
import android.os.Parcelable
import android.text.TextPaint
import android.text.style.TypefaceSpan
import androidx.annotation.Nullable

/**
 * @创建者 MuFeng-T
 * @创建时间 2020/3/6 15:37
 * @描述
 */

class TypefaceSpanKtx(private val newType: Typeface) : TypefaceSpan("") {

    override fun updateDrawState(ds: TextPaint) {
        applyCustomTypeFace(ds, newType)
    }

    override fun updateMeasureState(paint: TextPaint) {
        applyCustomTypeFace(paint, newType)
    }

    companion object {

        /* http://stackoverflow.com/questions/6612316/how-set-spannable-object-font-with-custom-font#answer-10741161 */

        val CREATOR: Parcelable.Creator<TypefaceSpanKtx> = object : Parcelable.Creator<TypefaceSpanKtx> {
            override fun createFromParcel(source: Parcel): TypefaceSpanKtx? {
                return null
            }

            override fun newArray(size: Int): Array<TypefaceSpanKtx?> {
                return arrayOfNulls(size)
            }
        }

        private fun applyCustomTypeFace(paint: Paint, @Nullable tf: Typeface?) {
            if (tf == null) {
                return
            }

            val oldStyle: Int
            val old = paint.typeface
            oldStyle = old?.style ?: 0

            val fake = oldStyle and tf.style.inv()
            if (fake and Typeface.BOLD != 0) {
                paint.isFakeBoldText = true
            }

            if (fake and Typeface.ITALIC != 0) {
                paint.textSkewX = -0.25f
            }

            paint.typeface = tf
        }
    }
}