package com.mufeng.mvvmlib.bean

open class BaseBean {
    var status: Int = 0
    var info: String = ""

   val isOk: Boolean
        get() = status == 1
}
