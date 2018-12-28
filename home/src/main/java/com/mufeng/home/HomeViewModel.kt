package com.mufeng.home

import android.view.View
import androidx.databinding.ObservableArrayList
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.mufeng.mvvmlib.base.BaseViewModel
import com.mufeng.mvvmlib.binding.thirdview.SmartRefreshLayoutCommand
import me.tatarka.bindingcollectionadapter2.itembindings.OnItemBindClass
import com.mufeng.mvvmlib.ext.ktx.map
import com.scwang.smartrefresh.layout.api.RefreshLayout
import me.tatarka.bindingcollectionadapter2.BindingRecyclerViewAdapter
import me.tatarka.bindingcollectionadapter2.collections.MergeObservableList
import java.util.Collections.addAll

class HomeViewModel: BaseViewModel() {

    var p = 1

    val adapter = HomeAdapter()

    val data = ObservableArrayList<String>()

    val itemBindClass = OnItemBindClass<String>().apply {
        map(BR.item, R.layout.home_head_view)
        map(BR.item, R.layout.home_item)
    }

    val headerFooterItems = MergeObservableList<String>()
        .insertItem("Header")
        .insertList(data)

    val viewHolder = BindingRecyclerViewAdapter.ViewHolderFactory { binding ->  HomeViewHolder(binding.root)}

    private class HomeViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onResume(lifecycleOwner: LifecycleOwner) {
        super.onResume(lifecycleOwner)
        autoRefreshStatus.value = true
    }

    fun getData(p: Int){
        val list = getList(p)
        hasMoreDataStatus.value = list != null && list.size >= 10
        if (p == 1) {
            data.clear()
            data.addAll(list)
            adapter.notifyDataSetChanged()
            finishRefreshStatus.value = true
        }else{
            data.addAll(list)
            adapter.notifyDataSetChanged()
            finishLoadMoreStatus.value = true
        }
    }

    private fun getList(p: Int) : List<String>{
        val list = arrayListOf<String>()
        if (p == 5) {
            for (i in 0 .. 6){
                list.add("Item $i")
            }
        }else{
            for (i in 0 .. 10){
                list.add("Item $i")
            }
        }
        return list
    }

    val finishRefreshStatus = MutableLiveData<Boolean>()
    val finishLoadMoreStatus = MutableLiveData<Boolean>()
    val hasMoreDataStatus = MutableLiveData<Boolean>()
    val autoRefreshStatus = MutableLiveData<Boolean>()

    //下拉刷新
    val refreshCommand = object : SmartRefreshLayoutCommand {
        override fun accept(t: RefreshLayout?) {
            p = 1
            getData(p)
        }
    }

    //上拉加载
    val loadMoreCommand = object : SmartRefreshLayoutCommand{
        override fun accept(t: RefreshLayout?) {
            p ++
            getData(p)
        }

    }

}