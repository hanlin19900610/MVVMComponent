package com.mufeng.home

import com.mufeng.home.databinding.HomeFragmentBinding
import com.mufeng.mvvmlib.base.BaseFragment
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener

class HomeFragment : BaseFragment<HomeViewModel,HomeFragmentBinding>(){

    override val layoutId: Int
        get() = R.layout.home_fragment

    override fun initView() {
        super.initView()
        mBinding.vm = mViewModel

    }
}