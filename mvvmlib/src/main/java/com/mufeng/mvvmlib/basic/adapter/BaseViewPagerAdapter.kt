package com.mufeng.mvvmlib.basic.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

/**
 * @创建者 MuFeng-T
 * @创建时间 2019/11/7 14:41
 * @描述
 */
class BaseViewPagerAdapter(fm: FragmentManager, private val fragments: List<Fragment>) :
    FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    override fun getItem(position: Int): Fragment {
        return fragments[position]
    }

    override fun getCount(): Int {
        return fragments.size
    }
}