package com.mufeng.login

import com.billy.cc.core.component.CCUtil
import com.mufeng.login.databinding.LoginActivityBinding
import com.mufeng.mvvmlib.base.BaseActivity

class LoginActivity : BaseActivity<LoginViewModel,LoginActivityBinding>() {
    override val layoutId: Int
        get() = R.layout.login_activity

    override fun initView() {
        super.initView()
        mBinding.vm = mViewModel
        val callId = CCUtil.getNavigateCallId(this)
        mViewModel.callId = callId

    }
}