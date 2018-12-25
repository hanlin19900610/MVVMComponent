package com.mufeng.mvvmlib.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mufeng.mvvmlib.bean.ErrorBean
import com.mufeng.mvvmlib.bean.IntentClass
import com.mufeng.mvvmlib.http.ExceptionHandle

open class BaseViewModel : ViewModel(), IViewModel{

    var lifecycleOwner: LifecycleOwner? = null

    val progress = MutableLiveData<Boolean>()
    val toast = MutableLiveData<String>()
    val finish = MutableLiveData<Boolean>()
    val intentClass = MutableLiveData<IntentClass>()
    val error = MutableLiveData<ErrorBean>()

    override fun onCreate(lifecycleOwner: LifecycleOwner) {
        this.lifecycleOwner = lifecycleOwner
    }

    override fun onStart(lifecycleOwner: LifecycleOwner) {

    }

    override fun onResume(lifecycleOwner: LifecycleOwner) {

    }

    override fun onPause(lifecycleOwner: LifecycleOwner) {

    }

    override fun onStop(lifecycleOwner: LifecycleOwner) {

    }

    override fun onDestroy(lifecycleOwner: LifecycleOwner) {
        this.lifecycleOwner = null
    }

    override fun onLifecycleChanged(lifecycleOwner: LifecycleOwner, event: Lifecycle.Event) {

    }

    /**
     * 显示加载框
     */
    fun showProgress(){
        progress.value = true
    }

    /**
     * 隐藏加载框
     */
    fun hideProgress(){
        progress.value = false
    }

    /**
     * 吐司
     * @param msg String
     */
    fun toast(msg: String){
        toast.value = msg
    }

    /**
     * 结束界面
     */
    fun finish() {
        finish.value = true
    }

    /**
     * 界面跳转
     * @param clz Class<out Activity>
     * @param bundle Bundle?
     * @param isFinish Boolean
     */
    fun startActivity(clz : Class<out AppCompatActivity>, bundle : Bundle? = null, isFinish : Boolean = false) {
        intentClass.value = IntentClass(clz,bundle,isFinish)
    }

    fun onError(throwable : Throwable){
        hideProgress()
        throwable.printStackTrace()
        val errorBean = ExceptionHandle.handleException(throwable)
        error.value = errorBean
    }
}