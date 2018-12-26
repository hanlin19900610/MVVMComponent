package com.mufeng.message

import com.mufeng.message.databinding.MessageFragmentBinding
import com.mufeng.mvvmlib.base.BaseFragment

class MessageFragment : BaseFragment<MessageViewModel,MessageFragmentBinding>(){
    override val layoutId: Int
        get() = R.layout.message_fragment

    override fun initView() {
        super.initView()
        mBinding.vm = mViewModel
    }

}