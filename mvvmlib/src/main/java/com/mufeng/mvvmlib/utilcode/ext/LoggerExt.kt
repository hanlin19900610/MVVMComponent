package com.mufeng.mvvmlib.utilcode.ext

import android.app.Application
import com.mufeng.mvvmlib.utilcode.utils.logger.CrashReportingTree
import timber.log.Timber

/**
 * @author MuFeng-T
 * @createTime 2019-10-17
 * @details
 */

typealias Supplier<T> = () -> T

fun Application.initLogger(isDebug: Boolean = true) {
    if (isDebug)
        Timber.plant(Timber.DebugTree())
    else
        Timber.plant(CrashReportingTree())

    log { "initLogger successfully, isDebug = $isDebug" }
}

inline fun log(supplier: Supplier<String>) = logd(supplier)

inline fun logd(supplier: Supplier<String>) = Timber.d(supplier())

inline fun logi(supplier: Supplier<String>) = Timber.i(supplier())

inline fun logw(supplier: Supplier<String>) = Timber.w(supplier())

inline fun loge(supplier: Supplier<String>) = Timber.e(supplier())