package com.mufeng.mvvmlib.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.mufeng.mvvmlib.R

/**
 * @创建者 MuFeng-T
 * @创建时间 2019/10/16 21:28
 * @描述
 */

/**
 * Glide 加载图片
 * @receiver ImageView
 * @param url String
 */
@BindingAdapter("url")
fun ImageView.setImageUri(url: String?){
    Glide.with(this)
        .load(url)
        .apply {
            placeholder(R.drawable.loading_animation)
            error(R.drawable.ic_broken_image)
        }
        .into(this)
}