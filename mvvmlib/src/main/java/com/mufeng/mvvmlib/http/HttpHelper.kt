package com.mufeng.mvvmlib.http

import com.mufeng.mvvmlib.util.gson.CustomizeGsonConverterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import java.util.concurrent.TimeUnit

const val TIME_OUT_SECONDS = 10
var BASE_URL = "https://api.github.com/"

val okHttpClient = OkHttpClient.Builder()
        .connectTimeout(TIME_OUT_SECONDS.toLong(), TimeUnit.SECONDS)
        .readTimeout(TIME_OUT_SECONDS.toLong(), TimeUnit.SECONDS)
        .writeTimeout(TIME_OUT_SECONDS.toLong(), TimeUnit.SECONDS)
        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        .build()

val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(CustomizeGsonConverterFactory.create())
        .build()



fun <T> apiService(clazz : Class<T>,baseUrl: String = BASE_URL): T {
    BASE_URL = baseUrl
    return retrofit.create(clazz)
}
