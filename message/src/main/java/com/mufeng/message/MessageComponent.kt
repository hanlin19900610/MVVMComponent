package com.mufeng.message

import com.billy.cc.core.component.CC
import com.billy.cc.core.component.CCResult
import com.billy.cc.core.component.IComponent

class MessageComponent : IComponent{
    override fun onCall(cc: CC?): Boolean {
        return when (cc?.actionName) {
            "getMessageFragment" -> {
                CC.sendCCResult(cc.callId, CCResult.success("fragment",MessageFragment()))
                false
            }
            else -> false
        }
    }

    override fun getName(): String {
        return "messageComponent"
    }
}