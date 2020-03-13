package com.mufeng.component

import com.billy.cc.core.component.CC
import com.mufeng.mvvmlib.basic.BaseViewModel


class MainViewModel : BaseViewModel() {

    fun click(){
        CC.obtainBuilder("mainComponent")
            .setActionName("showMainActivity")
            .build()
            .callAsync()
    }

}