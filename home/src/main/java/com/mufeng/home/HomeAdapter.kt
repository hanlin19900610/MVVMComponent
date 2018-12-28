package com.mufeng.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import me.tatarka.bindingcollectionadapter2.BindingRecyclerViewAdapter

class HomeAdapter : BindingRecyclerViewAdapter<String>() {

    override fun onCreateBinding(inflater: LayoutInflater?, layoutId: Int, viewGroup: ViewGroup?): ViewDataBinding {
        return super.onCreateBinding(inflater, layoutId, viewGroup)
    }

    override fun onBindBinding(
        binding: ViewDataBinding?,
        variableId: Int,
        layoutRes: Int,
        position: Int,
        item: String?
    ) {
        super.onBindBinding(binding, variableId, layoutRes, position, item)

    }

}