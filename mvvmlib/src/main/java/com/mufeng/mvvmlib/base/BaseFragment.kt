package com.mufeng.mvvmlib.base

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.lxj.xpopup.XPopup
import com.lxj.xpopup.interfaces.XPopupCallback
import com.mufeng.mvvmlib.bean.ErrorBean
import com.mufeng.mvvmlib.bean.IntentClass
import com.mufeng.mvvmlib.ext.bindLifecycle
import com.mufeng.mvvmlib.ext.toFlowable
import com.mufeng.mvvmlib.ext.toast
import java.lang.reflect.ParameterizedType

abstract class BaseFragment<VM : BaseViewModel, VB : ViewDataBinding> : Fragment() {

    /**
     * 视图是否加载完毕
     */
    protected var isViewPrepare = false
    /**
     * 数据是否加载过了
     */
    protected var hasLoadData = false

    private lateinit var mRootView : View
    protected lateinit var mBinding : VB
    protected lateinit var mViewModel : VM

    protected lateinit var mXPopup: XPopup

    abstract val layoutId : Int

    override fun onCreateView(inflater : LayoutInflater, container : ViewGroup?, savedInstanceState : Bundle?) : View? {
        mRootView = LayoutInflater.from(context).inflate(layoutId, container, false)
        return mRootView
    }

    override fun onViewCreated(view : View, savedInstanceState : Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mBinding = DataBindingUtil.bind(view) !!
        mBinding.setLifecycleOwner(this)
        mViewModel = initViewModel()
        mXPopup = XPopup.get(context)
            .dismissOnBackPressed(true)
            .dismissOnTouchOutside(false)
            .asLoading()
            .setPopupCallback(object : XPopupCallback {
                override fun onDismiss() {
                    onDialogDismiss()
                }

                override fun onShow() {
                    onDialogShow()
                }
            })
        lifecycle.addObserver(mViewModel)
        isViewPrepare = true
        initView()
        initUIBinding()
        initViewObservable()
        initData()
        lazyLoadDataIfPrepared()
    }

    /**
     * 界面初始化
     */
    open protected fun initView() {

    }

    open protected fun initViewObservable() {

    }

    open protected fun onDialogShow() {

    }

    open protected fun onDialogDismiss() {

    }

    /**
     * 初始化数据 (非懒加载)
     */
    open protected fun initData() {

    }

    /**
     * 懒加载
     */
    open protected fun lazyLoad() {

    }

    override fun setUserVisibleHint(isVisibleToUser : Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (isVisibleToUser) {
            lazyLoadDataIfPrepared()
        }
    }

    private fun lazyLoadDataIfPrepared() {
        if (userVisibleHint && isViewPrepare && ! hasLoadData) {
            lazyLoad()
            hasLoadData = true
        }
    }

    private fun initUIBinding() {
        mViewModel.error.toFlowable().bindLifecycle(this).subscribe { onError(it) }
        mViewModel.progress.toFlowable().bindLifecycle(this).subscribe { onProgress(it) }
        mViewModel.toast.toFlowable().bindLifecycle(this).subscribe { context?.toast(it) }
        mViewModel.finish.toFlowable().bindLifecycle(this).subscribe { activity?.finish() }
        mViewModel.intentClass.toFlowable().bindLifecycle(this).subscribe { startActivity(it) }
    }

    open protected fun startActivity(intentClass : IntentClass) {
        val intent = Intent(activity, intentClass.targetClass)
        if (intentClass.bundle != null) {
            intent.putExtras(intentClass.bundle!!)
        }
        startActivity(intent)
        if (intentClass.isFinish == true)
            activity ?.finish()
    }

    open protected fun onProgress(isShow : Boolean) {
        if (isShow) {
            mXPopup.show()
        } else {
            mXPopup.dismiss()
        }
    }

    open protected fun onError(errorBean : ErrorBean) {
        errorBean.msg.let { activity?.toast(it) }
    }

    private fun initViewModel() : VM {
        val typeClass1 = this.javaClass.genericSuperclass
        val actualType1 : Class<out BaseViewModel>
        if (typeClass1 is ParameterizedType) {
            actualType1 = typeClass1.actualTypeArguments[0] as Class<VM>
            return createViewModel(this, actualType1)
        } else {
            throw IllegalArgumentException("没有填写继承于BaseViewModel的泛型参数")
        }
    }

    /**
     * 创建ViewModel
     * @param cls
     * @param <T>
     * @return
    </T> */
    fun <T : ViewModel> createViewModel(fragment : androidx.fragment.app.Fragment, cls : Class<T>) : T {
        return ViewModelProviders.of(fragment).get(cls)
    }

    override fun onDestroy() {
        super.onDestroy()
        mXPopup.dismiss()
        lifecycle.removeObserver(mViewModel)
    }

}