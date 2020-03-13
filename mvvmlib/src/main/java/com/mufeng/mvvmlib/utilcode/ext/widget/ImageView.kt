package com.mufeng.mvvmlib.utilcode.ext.widget

import android.widget.ImageView

/**
 * @创建者 MuFeng-T
 * @创建时间 2020/3/6 22:02
 * @描述
 */
var ImageView.imageResource: Int
    get() = throw Exception("Property does not have a getter")
    set(value) {
        setImageResource(value)
    }