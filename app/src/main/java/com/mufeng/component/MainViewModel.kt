package com.mufeng.component

import androidx.lifecycle.LifecycleOwner
import com.billy.cc.core.component.CC
import com.mufeng.mvvmlib.base.BaseViewModel
import com.mufeng.mvvmlib.ext.bindLifecycle
import com.mufeng.mvvmlib.ext.io_main_completable
import io.reactivex.Completable
import java.util.concurrent.TimeUnit

class MainViewModel : BaseViewModel() {

    override fun onCreate(lifecycleOwner: LifecycleOwner) {
        super.onCreate(lifecycleOwner)
        Completable.timer(3, TimeUnit.SECONDS)
            .compose(io_main_completable())
            .bindLifecycle(this)
            .subscribe {
                CC.obtainBuilder("loginComponent")
                    .setActionName("isLogin")
                    .build()
                    .callAsync { cc, result ->
                        if (result.isSuccess) {
                            CC.obtainBuilder("mainComponent")
                                .setActionName("showMainActivity")
                                .build()
                                .callAsync()
                            finish()
                        }
                    }
            }
    }

}