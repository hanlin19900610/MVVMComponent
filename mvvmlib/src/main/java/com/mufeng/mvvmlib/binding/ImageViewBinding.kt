package com.mufeng.mvvmlib.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.request.RequestOptions
import com.mufeng.mvvmlib.glide.GlideApp

/**
 *
 * 1.普通图片加载
 * 2.圆形图片加载
 * 3.圆角图片加载
 *
 * @param imageView ImageView
 * @param url String?
 */
@BindingAdapter("bind_imageUrl")
fun loadImage(imageView: ImageView, url: String?) {
    GlideApp.with(imageView.context)
            .load(url)
            .into(imageView)
}

@BindingAdapter("bind_imageUrl_circle")
fun loadImageCircle(imageView: ImageView, url: String?) {
    GlideApp.with(imageView.context)
            .load(url)
            .apply(RequestOptions().circleCrop())
            .into(imageView)
}