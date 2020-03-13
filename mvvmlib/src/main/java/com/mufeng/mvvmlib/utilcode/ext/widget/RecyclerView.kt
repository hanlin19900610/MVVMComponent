package com.mufeng.mvvmlib.utilcode.ext.widget

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.mufeng.mvvmlib.utilcode.ext.dp2px

/**
 * @创建者 MuFeng-T
 * @创建时间 2020/1/17 10:41
 * @描述
 */
/**
 * 移除所有差异性计算引发的默认更新动画.
 */
fun RecyclerView.removeAllAnimation() {
    val itemAnimator = DefaultItemNoAnimAnimator()
    this.itemAnimator = itemAnimator
    itemAnimator.supportsChangeAnimations = false
    itemAnimator.addDuration = 0L
    itemAnimator.changeDuration = 0L
    itemAnimator.removeDuration = 0L
}


/**
 * Add item padding
 * @param padding the top, bottom, left, right is same
 */
fun RecyclerView.itemPadding(padding:Int) {
    addItemDecoration(PaddingItemDecoration(padding, padding, padding, padding))
}

/**
 * Add item padding for top, bottom, left, right
 */
fun RecyclerView.itemPadding(top: Int, bottom: Int, left: Int = 0, right: Int = 0) {
    addItemDecoration(PaddingItemDecoration(top, bottom, left, right))
}

class PaddingItemDecoration(top: Int, bottom: Int, left: Int, right: Int) : RecyclerView.ItemDecoration() {

    private val mTop = top
    private val mBottom = bottom
    private val mLeft = left
    private val mRight = right

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect.bottom = view.dp2px(mBottom)
        outRect.top = view.dp2px(mTop)
        outRect.left = view.dp2px(mLeft)
        outRect.right = view.dp2px(mRight)
    }
}