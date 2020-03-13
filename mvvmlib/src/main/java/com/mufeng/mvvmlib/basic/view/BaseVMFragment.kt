package com.mufeng.mvvmlib.basic.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.lxj.xpopup.XPopup
import com.lxj.xpopup.impl.LoadingPopupView
import com.lxj.xpopup.interfaces.SimpleCallback
import com.mufeng.mvvmlib.basic.*
import com.mufeng.mvvmlib.utilcode.ext.fillIntentArguments
import com.mufeng.mvvmlib.http.ApiException
import com.mufeng.mvvmlib.utilcode.utils.toast
import com.mufeng.mvvmlib.widget.StatefulLayout
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope

/**
 * @创建者 MuFeng-T
 * @创建时间 2019/10/15 21:09
 * @描述
 */
abstract class BaseVMFragment<VM : BaseViewModel, VB : ViewDataBinding> : Fragment(),
    CoroutineScope by MainScope() {

    protected lateinit var binding: VB
    abstract val viewModel: VM
    abstract val layoutResId: Int
    lateinit var loadingView: LoadingPopupView

    var statefulLayout: StatefulLayout? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, layoutResId, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadingView = XPopup.Builder(activity)
            .dismissOnTouchOutside(false)
            .setPopupCallback(SimpleCallback())
            .asLoading()
        binding.lifecycleOwner = this
        startObserve()
        initView()
        initData()
    }

    open  fun startObserve() {
        viewModel.apply {

            uiChange.eventObserver(this@BaseVMFragment) {
                when (it) {
                    is UIChange.ToastEvent -> context?.toast(it.msg)
                    is UIChange.FinishEvent -> activity?.finish()
                    is UIChange.IntentEvent -> startActivity(it)
                }
            }

            apiException.eventObserver(this@BaseVMFragment){
                if (it is ApiException){
                    onError(it)
                }
            }

            apiLoading.eventObserver(this@BaseVMFragment){
                if (it){
                    showLoading()
                }else{
                    hideLoading()
                }
            }

        }
    }

    abstract fun initView()

    abstract fun initData()

    open fun onError(e: ApiException) {
        toast(e.displayMessage)
    }

    protected open fun showLoading(){
        loadingView.show()
    }

    protected open fun hideLoading(){
        loadingView.dismiss()
    }
    private fun startActivity(intentEvent: UIChange.IntentEvent) {
        val intent = Intent(activity, intentEvent.clzz)
        if (intentEvent.params.isNotEmpty()) {
            intent.fillIntentArguments(*intentEvent.params)
        }
        startActivity(intent)
        if (intentEvent.isFinished) {
            activity?.finish()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.unbind()
    }

}
