package com.mufeng.login

import android.view.View
import androidx.lifecycle.MutableLiveData
import com.billy.cc.core.component.CC
import com.billy.cc.core.component.CCResult
import com.mufeng.mvvmlib.base.BaseApp
import com.mufeng.mvvmlib.base.BaseViewModel
import com.mufeng.mvvmlib.binding.ViewClickCommand
import com.mufeng.mvvmlib.ext.bindLifecycle
import com.mufeng.mvvmlib.ext.io_main_completable
import com.mufeng.mvvmlib.ext.sharedPreferences
import io.reactivex.Completable
import java.util.concurrent.TimeUnit

class LoginViewModel : BaseViewModel() {

    val userName = MutableLiveData<String>()
    val password = MutableLiveData<String>()

    var callId: String = ""

    val loginCommand = object : ViewClickCommand{
        override fun accept(t: View?) {
            if (userName.value == null || userName.value?.isBlank() == true) {
                toast("请输入用户名")
                return
            }
            if (password.value == null || password.value?.isBlank() == true) {
                toast("请输入密码")
                return
            }
            //模拟登录网络请求
            showProgress()
            Completable.timer(3,TimeUnit.SECONDS)
                .compose(io_main_completable())
                .bindLifecycle(this@LoginViewModel)
                .subscribe {
                    if (userName.value == "mufeng" && password.value == "123456") {
                        hideProgress()
                        BaseApp.INSTANCE.sharedPreferences().edit().putBoolean("isLogin",true).apply()
                        toast("登录成功")
                        CC.sendCCResult(callId, CCResult.success())
                    }else{
                        hideProgress()
                        toast("用户名或密码错误")
                        return@subscribe
                    }
                }

        }
    }

}