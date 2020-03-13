package com.mufeng.mvvmlib.utilcode.ext

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

/**
 * @创建者 MuFeng-T
 * @创建时间 2020/1/17 10:32
 * @描述
 */
fun <T> LifecycleOwner.observe(liveData: LiveData<T>, observer: (T) -> Unit) {
    liveData.observe(this, Observer { it?.let { t -> observer(t) } })
}