package com.mufeng.mine

import com.mufeng.mine.databinding.MineFragmentBinding
import com.mufeng.mvvmlib.base.BaseFragment

class MineFragment : BaseFragment<MineViewModel,MineFragmentBinding>(){
    override val layoutId: Int
        get() = R.layout.mine_fragment

    override fun initView() {
        super.initView()
        mBinding.vm = mViewModel
    }
}