package com.mufeng.home

import com.mufeng.home.databinding.HomeFragmentBinding
import com.mufeng.mvvmlib.base.BaseFragment

class HomeFragment : BaseFragment<HomeViewModel,HomeFragmentBinding>(){
    override val layoutId: Int
        get() = R.layout.home_fragment

    override fun initView() {
        super.initView()
        mBinding.vm = mViewModel
    }
}