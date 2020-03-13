package com.mufeng.mvvmlib.widget

import android.content.Context
import android.graphics.Rect
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.animation.TranslateAnimation
import androidx.core.widget.NestedScrollView

/**
 * @创建者 MuFeng-T
 * @创建时间 2020/1/18 8:22
 * @描述
 */
class OverScrollView : NestedScrollView {

    private var isCalled: Boolean = false
    private var mCallback: Callback? = null
    private var mView: View? = null
    private val mRect = Rect()
    private var y = 0
    private var isFirst: Boolean = true

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyle: Int = 0) : super(
        context,
        attrs,
        defStyle
    )

    override fun onFinishInflate() {
        if (childCount > 0) {
            mView = getChildAt(0)
        }
        super.onFinishInflate()
    }

    override fun onTouchEvent(ev: MotionEvent?): Boolean {
        if (mView != null) {
            commonOnTouch(ev)
        }
        return super.onTouchEvent(ev)
    }

    private fun commonOnTouch(ev: MotionEvent?) {
        val action = ev?.action
        val cy = ev?.y?.toInt()
        when (action) {
            MotionEvent.ACTION_DOWN -> {
            }
            MotionEvent.ACTION_MOVE -> {
                var dy = cy?.minus(y)
                if (isFirst) {
                    dy = 0
                    isFirst = false
                }
                y = cy ?: 0
                if (isNeedMove()) {
                    if (mRect.isEmpty) {
                        mRect.set(
                            mView?.left ?: 0, mView?.top ?: 0,
                            mView?.right ?: 0, mView?.bottom ?: 0
                        )
                    }
                    mView?.layout(
                        mView?.left ?: 0, mView?.top ?: 0 + 2 * (dy ?: 0) / 3,
                        mView?.right ?: 0, mView?.bottom ?: 0 + 2 * (dy ?: 0) / 3
                    )
                    if (shouldCallBack(dy ?: 0)) {
                        if (mCallback != null) {
                            if (!isCalled) {
                                isCalled = true
                                resetPosition()
                                mCallback?.callback()
                            }
                        }
                    }
                }
            }
            MotionEvent.ACTION_UP -> if (!mRect.isEmpty) {
                resetPosition()
            }
        }
    }

    private fun shouldCallBack(dy: Int): Boolean {
        if (dy > 0 && mView?.top ?: 0 > height / 2) {
            return true
        }
        return false
    }

    private fun resetPosition() {
        val animation = TranslateAnimation(
            0.toFloat(),
            0.toFloat(),
            mView?.top?.toFloat() ?: 0.0f,
            mRect.top.toFloat()
        )

        animation.duration = 200
        animation.fillAfter = true
        mView?.startAnimation(animation)
        mView?.layout(mRect.left,mRect.top,mRect.right,mRect.bottom)
        mRect.setEmpty()
        isFirst = true
        isCalled = false
    }

    fun isNeedMove(): Boolean {
        val offset = mView?.measuredHeight!! - height
        val scrollY = scrollY
        if (scrollY == 0 || scrollY == offset){
            return true
        }
        return false
    }

    fun setCallBack(callback: Callback){
        this.mCallback = callback
    }

    interface Callback {
        fun callback()
    }

}