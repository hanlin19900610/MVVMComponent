package com.mufeng.component

import android.os.Bundle
import androidx.activity.viewModels
import com.mufeng.component.databinding.ActivityMainBinding
import com.mufeng.mvvmlib.basic.view.BaseVMActivity

class MainActivity : BaseVMActivity<MainViewModel,ActivityMainBinding>() {
    override val viewModel: MainViewModel by viewModels()
    override val layoutResId: Int
        get() = R.layout.activity_main

    override fun initView(savedInstanceState: Bundle?) {
        binding.vm = viewModel
    }

    override fun initData() {
    }


}
