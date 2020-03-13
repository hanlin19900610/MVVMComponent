package com.mufeng.home

import com.billy.cc.core.component.CC
import com.billy.cc.core.component.CCResult
import com.billy.cc.core.component.CCUtil
import com.billy.cc.core.component.IComponent

/**
 * @创建者 MuFeng-T
 * @创建时间 2020/3/13 21:25
 * @描述
 */
class ModuleComponent : IComponent {
    override fun onCall(cc: CC?): Boolean {
        return false
    }

    override fun getName(): String {
        return "home_moduleComponent"
    }
}