package com.mufeng.video

import com.billy.cc.core.component.CC
import com.billy.cc.core.component.CCResult
import com.billy.cc.core.component.IComponent

class VideoComponent : IComponent {
    override fun onCall(cc: CC?): Boolean {
        return when (cc?.actionName) {
            "getVideoFragment" -> {
                CC.sendCCResult(cc.callId, CCResult.success("fragment",VideoFragment()))
                false
            }
            else -> false
        }
    }

    override fun getName(): String {
        return "videoComponent"
    }
}