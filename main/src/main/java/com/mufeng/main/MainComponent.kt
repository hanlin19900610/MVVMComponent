package com.mufeng.main

import com.billy.cc.core.component.CC
import com.billy.cc.core.component.CCResult
import com.billy.cc.core.component.CCUtil
import com.billy.cc.core.component.IComponent
import com.mufeng.main.main.MainActivity

class MainComponent : IComponent{
    override fun onCall(cc: CC?): Boolean {
        return when (cc?.actionName) {
            "showMainActivity" -> {
                CCUtil.navigateTo(cc,MainActivity::class.java)
                CC.sendCCResult(cc.callId, CCResult.success())
                false
            }
            else-> false
        }
    }

    override fun getName(): String {
        return "mainComponent"
    }
}