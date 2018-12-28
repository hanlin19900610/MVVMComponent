package com.mufeng.login

import android.app.Activity
import android.content.Intent
import com.billy.cc.core.component.CC
import com.billy.cc.core.component.CCResult
import com.billy.cc.core.component.CCUtil
import com.billy.cc.core.component.IComponent
import com.mufeng.mvvmlib.ext.sharedPreferences

class LoginComponent: IComponent {
    override fun onCall(cc: CC?): Boolean {
        when (cc?.actionName) {
            "isLogin" -> {
                val context = cc.context
                val isLogin = context.sharedPreferences().getBoolean("isLogin",false)
                return if (isLogin) {
                    CC.sendCCResult(cc.callId, CCResult.success())
                    false
                }else{
                    CCUtil.navigateTo(cc,LoginActivity::class.java)
                    true
                }

            }
        }
        return false
    }

    override fun getName(): String {
        return "loginComponent"
    }
}
