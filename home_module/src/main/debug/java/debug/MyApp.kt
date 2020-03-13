package debug

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import com.billy.cc.core.component.CC
import com.mufeng.mvvmlib.utilcode.ext.initLogger
import com.mufeng.mvvmlib.utilcode.utils.initContext


class MyApp : Application() {

    companion object {
        lateinit var INSTANCE: MyApp
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    override fun onCreate() {
        super.onCreate()

        INSTANCE = this

        initContext(this)
        initLogger(true)

        //CC
        CC.enableVerboseLog(true)
        CC.enableDebug(true)
        CC.enableRemoteCC(true)
    }

}