package com.mufeng.login

import com.billy.cc.core.component.CC
import com.billy.cc.core.component.IComponent

class LoginComponent: IComponent {
    override fun onCall(cc: CC?): Boolean {
        return false
    }

    override fun getName(): String {
        return "loginComponent"
    }
}