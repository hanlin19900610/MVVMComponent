package com.mufeng.mvvmlib.base

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
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

abstract class BaseActivity<VM: BaseViewModel,VB: ViewDataBinding> : AppCompatActivity() {

    protected lateinit var mViewModel: VM
    protected lateinit var mBinding: VB

    protected lateinit var mXPopup: XPopup

    //设置布局文件
    abstract val layoutId: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this,layoutId)
        mViewModel = initViewModel()
        mXPopup = XPopup.get(this)
            .dismissOnBackPressed(true)
            .dismissOnTouchOutside(false)
            .asLoading()
            .setPopupCallback(object : XPopupCallback{
                override fun onDismiss() {
                    onDialogDismiss()
                }

                override fun onShow() {
                    onDialogShow()
                }
            })
        lifecycle.addObserver(mViewModel)
        mBinding.setLifecycleOwner(this)

        initView()
        initUIBinding()
        initViewObservable()
    }

    open protected fun onDialogShow() {

    }

    open protected fun onDialogDismiss() {

    }

    /**
     * 组件初始化
     */
    open protected fun initView() {

    }

    /**
     * ViewModel与View通信处理
     */
    open protected fun initViewObservable(){

    }

    private fun initUIBinding() {
        mViewModel.error.toFlowable().bindLifecycle(this).subscribe { onError(it) }
        mViewModel.progress.toFlowable().bindLifecycle(this).subscribe { onProgress(it) }
        mViewModel.toast.toFlowable().bindLifecycle(this).subscribe { toast(it) }
        mViewModel.finish.toFlowable().bindLifecycle(this).subscribe { finish() }
        mViewModel.intentClass.toFlowable().bindLifecycle(this).subscribe { startActivity(it) }
    }

    open protected fun startActivity(intentClass : IntentClass) {
        val intent = Intent(this,intentClass.targetClass)
        if (intentClass.bundle != null) {
            intent.putExtras(intentClass.bundle!!)
        }
        startActivity(intent)
        if (intentClass.isFinish == true)
            finish()
    }

    open protected fun onProgress(isShow : Boolean) {
        if (isShow) {
            mXPopup.show()
        }else{
            mXPopup.dismiss()
        }
    }

    open protected fun onError(errorBean : ErrorBean) {
        toast(errorBean.msg)
    }

    private fun initViewModel() : VM {
        val typeClass1 = this.javaClass.genericSuperclass
        val actualType1: Class<out BaseViewModel>
        if (typeClass1 is ParameterizedType) {
            actualType1 = typeClass1.actualTypeArguments[0] as Class<VM>
            return createViewModel(this,actualType1)
        }else{
            throw IllegalArgumentException("没有填写继承于BaseViewModel的泛型参数")
        }
    }

    /**
     * 创建ViewModel
     * @param cls
     * @param <T>
     * @return
    </T> */
    fun <T : ViewModel> createViewModel(activity: AppCompatActivity, cls: Class<T>): T {
        return ViewModelProviders.of(activity).get(cls)
    }


    override fun onDestroy() {
        super.onDestroy()
        mXPopup.dismiss()
        lifecycle.removeObserver(mViewModel)
    }

}