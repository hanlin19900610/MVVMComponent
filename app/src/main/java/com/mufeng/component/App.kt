package com.mufeng.component

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import com.billy.cc.core.component.CC

class App : Application() {

    companion object {
        lateinit var INSTANCE: App
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