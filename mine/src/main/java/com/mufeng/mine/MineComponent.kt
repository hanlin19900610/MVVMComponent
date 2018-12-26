package com.mufeng.mine

import com.billy.cc.core.component.CC
import com.billy.cc.core.component.CCResult
import com.billy.cc.core.component.IComponent

class MineComponent : IComponent{
    override fun onCall(cc: CC?): Boolean {
        return when (cc?.actionName) {
            "getMineFragment" -> {
                CC.sendCCResult(cc.callId, CCResult.success("fragment",MineFragment()))
                false
            }
            else -> false
        }
    }

    override fun getName(): String {
        return "mineComponent"
    }
}