package com.mufeng.main.main

import androidx.fragment.app.Fragment
import com.billy.cc.core.component.CC
import com.mufeng.mvvmlib.base.BaseViewModel

class MainViewModel : BaseViewModel() {


    val pageItems = arrayListOf(
        CC.obtainBuilder("homeComponent").setActionName("getHomeFragment").build().call().getDataItem("fragment")as Fragment,
        CC.obtainBuilder("homeComponent").setActionName("getHomeFragment").build().call().getDataItem("fragment")as Fragment,
        CC.obtainBuilder("homeComponent").setActionName("getHomeFragment").build().call().getDataItem("fragment")as Fragment,
        CC.obtainBuilder("homeComponent").setActionName("getHomeFragment").build().call().getDataItem("fragment")as Fragment
    )

}