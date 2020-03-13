package com.mufeng.mvvmlib.utilcode.ext

import com.google.gson.Gson
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

/**
 * @创建者 MuFeng-T
 * @创建时间 2019/11/21 14:10
 * @描述
 */

object GsonUtils {

    val INSTANCE: Gson by lazy { Gson() }
}

fun <T> T.toJson(): String {
    return GsonUtils.INSTANCE.toJson(this)
}

inline fun <reified T> String.fromJson(): T {
    return GsonUtils.INSTANCE.fromJson(this, T::class.java)
}

inline fun <reified T> String.fromJsonToList(): List<T> {
    return GsonUtils.INSTANCE.fromJson<List<T>>(this,
        ParameterizedTypeImpl(T::class.java)
    )
}

class ParameterizedTypeImpl(private val clzz: Class<*>) : ParameterizedType {
    override fun getRawType(): Type = List::class.java

    override fun getOwnerType(): Type? = null

    override fun getActualTypeArguments(): Array<Type> = arrayOf(clzz)
}