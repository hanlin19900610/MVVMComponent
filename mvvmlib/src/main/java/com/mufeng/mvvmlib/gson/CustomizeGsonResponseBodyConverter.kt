package com.mufeng.mvvmlib.gson

import android.util.Log
import com.google.gson.Gson
import com.google.gson.TypeAdapter
import com.mufeng.mvvmlib.BuildConfig
import com.mufeng.mvvmlib.bean.BaseBean
import com.mufeng.mvvmlib.http.ServerException
import okhttp3.ResponseBody
import retrofit2.Converter
import java.io.IOException

class CustomizeGsonResponseBodyConverter<T> internal constructor(private val gson : Gson, private val adapter : TypeAdapter<T>) : Converter<ResponseBody, T> {

    @Throws(IOException::class)
    override fun convert(value : ResponseBody) : T {
        //把responsebody转为string
        val response = value.string()
        if (BuildConfig.DEBUG) {
            //打印后台数据
            Log.e("TAG", response)
        }
        val baseBean = gson.fromJson<BaseBean>(response, BaseBean::class.java)
        // 这里只是为了检测code是否!=1,所以只解析HttpStatus中的字段,因为只要code和message就可以了
        if (baseBean.isOk) {
            value.close()
            //抛出一个RuntimeException, 这里抛出的异常会到subscribe的onError()方法中统一处理
            throw ServerException(Throwable(baseBean.info),baseBean.status)
        }
        try {
            return adapter.fromJson(response)
        } finally {
            value.close()
        }
    }

}
