package com.mufeng.mainmodule.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.mufeng.mainmodule.R
import com.mufeng.mainmodule.databinding.MainActivityMainBinding
import com.mufeng.mvvmlib.basic.view.BaseVMActivity

//class MainActivity : BaseVMActivity<MainViewModel, MainActivityMainBinding>() {
//    override val viewModel: MainViewModel
//        by viewModels()
//    override val layoutResId: Int =
//        R.layout.main_activity_main
//    override fun initView(savedInstanceState: Bundle?) {
//        binding.vm = viewModel
//    }
//
//    override fun initData() {
//    }
//
//
//}

class MainActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity_main)
    }
}
