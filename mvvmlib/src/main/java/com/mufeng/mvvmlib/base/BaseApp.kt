package com.mufeng.mvvmlib.base

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import com.billy.cc.core.component.CC

open class BaseApp : Application(){

    companion object {
        lateinit var INSTANCE: BaseApp
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    override fun onCreate() {
        super.onCreate()

        INSTANCE = this

        //CC
        CC.enableVerboseLog(true)
        CC.enableDebug(true)
        CC.enableRemoteCC(true)
    }


}