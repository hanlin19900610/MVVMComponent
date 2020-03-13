package com.mufeng.mvvmlib.utilcode.utils.logger

import android.util.Log
import timber.log.Timber

/**
 * @author MuFeng-T
 * @createTime 2019-10-17
 * @details
 */

class CrashReportingTree : Timber.Tree() {

    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        if (priority == Log.VERBOSE || priority == Log.DEBUG) {
            return
        }

        FakeCrashLibrary.log(priority, tag, message)

        if (t != null) {
            if (priority == Log.ERROR) {
                FakeCrashLibrary.logError(t)
            } else if (priority == Log.WARN) {
                FakeCrashLibrary.logWarning(t)
            }
        }
    }
}

private class FakeCrashLibrary private constructor() {

    init {
        throw AssertionError("No instances.")
    }

    companion object {
        fun log(priority: Int, tag: String?, message: String) {

        }

        fun logWarning(t: Throwable) {

        }

        fun logError(t: Throwable) {

        }
    }
}