package com.mufeng.main.main

import androidx.fragment.app.Fragment
import com.billy.cc.core.component.CC
import com.mufeng.main.R
import com.mufeng.main.databinding.MainActivityBinding
import com.mufeng.mvvmlib.base.BaseActivity

class MainActivity : BaseActivity<MainViewModel,MainActivityBinding>() {

    private lateinit var homeFragment: Fragment
    private lateinit var videoFragment: Fragment
    private lateinit var messageFragment: Fragment
    private lateinit var mineFragment: Fragment

    override val layoutId: Int
        get() = R.layout.main_activity

    override fun initView() {
        super.initView()
        mBinding.vm = mViewModel
        homeFragment = CC.obtainBuilder("homeComponent").setActionName("getHomeFragment").build().call().getDataItem("fragment")
        videoFragment = CC.obtainBuilder("videoComponent").setActionName("getVideoFragment").build().call().getDataItem("fragment")
        messageFragment = CC.obtainBuilder("messageComponent").setActionName("getMessageFragment").build().call().getDataItem("fragment")
        mineFragment = CC.obtainBuilder("mineComponent").setActionName("getMineFragment").build().call().getDataItem("fragment")
        val fragments = arrayListOf(homeFragment,videoFragment,messageFragment, mineFragment)
        val adapter = MyPageAdapter(supportFragmentManager,fragments)
        mBinding.viewPager.adapter = adapter
        mBinding.viewPager.offscreenPageLimit = 4
        mBinding.bbl.setViewPager(mBinding.viewPager)
        mBinding.bbl.setSmoothScroll(true)
    }

}