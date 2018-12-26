package com.mufeng.component

import com.mufeng.component.databinding.ActivityMainBinding
import com.mufeng.mvvmlib.base.BaseActivity

class MainActivity : BaseActivity<MainViewModel,ActivityMainBinding>() {
    override val layoutId: Int
        get() = R.layout.activity_main

    override fun initView() {
        super.initView()
        mBinding.vm = mViewModel
    }

}
