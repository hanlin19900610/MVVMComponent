package com.mufeng.mvvmlib.ext

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast


inline fun Context.toast(value: () -> String) =
        Toast.makeText(this, value(), Toast.LENGTH_SHORT).show()

//扩展函数 toast
fun Context.toast(value: String) = toast { value }

//扩展函数 跳转到外部浏览器
fun Context.jumpBrowser(url: String) =
        Uri.parse(url).run {
            Intent(Intent.ACTION_VIEW, this)
        }.also { intent ->
            startActivity(intent)
        }