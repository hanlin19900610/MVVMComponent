package com.mufeng.mvvmlib.gson


import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.reflect.Type

class CustomizeGsonConverterFactory private constructor(private val gson : Gson) : Converter.Factory() {

    override fun responseBodyConverter(
        type : Type?, annotations : Array<Annotation>?,
        retrofit : Retrofit?) : Converter<ResponseBody, *>? {
        val adapter = gson.getAdapter(TypeToken.get(type !!))
        return CustomizeGsonResponseBodyConverter(gson, adapter)
    }

    override fun requestBodyConverter(type : Type?, parameterAnnotations : Array<Annotation>?, methodAnnotations : Array<Annotation>?, retrofit : Retrofit?) : Converter<*, RequestBody>? {
        val adapter = gson.getAdapter(TypeToken.get(type !!))
        return CustomizeGsonRequestBodyConverter(gson, adapter)
    }

    companion object {

        @JvmOverloads
        fun create(gson : Gson = Gson()) : CustomizeGsonConverterFactory {
            return CustomizeGsonConverterFactory(gson)
        }
    }

}
