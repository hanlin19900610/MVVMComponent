package com.mufeng.home

import com.billy.cc.core.component.CC
import com.billy.cc.core.component.CCResult
import com.billy.cc.core.component.IComponent

class HomeComponent : IComponent {
    override fun onCall(cc: CC?): Boolean {
        return when (cc?.actionName) {
            "getHomeFragment" -> {
                CC.sendCCResult(cc.callId, CCResult.success("fragment",HomeFragment()))
                false
            }
            else -> false
        }
    }

    override fun getName(): String {
        return "homeComponent"
    }
}