package com.mufeng.mvvmlib.http.handler

import okhttp3.Interceptor
import okhttp3.Response

/**
 * @创建者 MuFeng-T
 * @创建时间 2020/2/21 17:12
 * @描述
 */
class HeaderInterceptor : Interceptor{

    private var headers = hashMapOf<String, String>()


    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()
        headers.forEach { (t, u) ->
            requestBuilder.addHeader(t, u)
        }
        return chain.proceed(requestBuilder.build())
    }

    fun put(key: String, value: String): HeaderInterceptor {
        headers[key] = value
        return this
    }

    fun put(headers: HashMap<String, String>): HeaderInterceptor {
        this.headers.putAll(headers)
        return this
    }

}