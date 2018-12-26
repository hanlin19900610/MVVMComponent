package com.mufeng.video

import com.mufeng.mvvmlib.base.BaseFragment
import com.mufeng.video.databinding.VideoFragmentBinding

class VideoFragment : BaseFragment<VideoViewModel,VideoFragmentBinding>(){
    override val layoutId: Int
        get() = R.layout.video_fragment
}