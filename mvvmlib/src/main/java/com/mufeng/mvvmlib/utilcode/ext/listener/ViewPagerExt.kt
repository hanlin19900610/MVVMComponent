package com.mufeng.mvvmlib.utilcode.ext.listener

import androidx.viewpager.widget.ViewPager

/**
 * @创建者 MuFeng-T
 * @创建时间 2020/3/6 16:14
 * @描述
 */

/**
 * Add an action which will be invoked when the ViewPager onPageSelected
 * @see ViewPager.addOnPageChangeListener
 */
fun ViewPager.onPageSelected(action: (newPosition: Int) -> Unit) =
    addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
        override fun onPageScrollStateChanged(state: Int) {}

        override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}

        override fun onPageSelected(position: Int) {
            action(position)
        }
    })