package com.mufeng.mvvmlib.interceptor

import com.billy.cc.core.component.CCResult
import com.billy.cc.core.component.Chain
import com.billy.cc.core.component.IGlobalCCInterceptor
import timber.log.Timber

class LogInterceptor : IGlobalCCInterceptor {

    private val TAG = "LogInterceptor"

    override fun intercept(chain: Chain?): CCResult {
        Timber.tag(TAG).w("===========log before: ${chain?.cc}")
        val result = chain?.proceed()
        Timber.tag(TAG).w("===========log after: $result")
        return result!!
    }

    override fun priority(): Int {
        return 1
    }
}