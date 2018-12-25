package com.mufeng.mvvmlib.http

import android.net.ParseException

import com.google.gson.JsonParseException
import com.google.gson.stream.MalformedJsonException
import com.mufeng.mvvmlib.bean.ErrorBean
import org.apache.http.conn.ConnectTimeoutException
import org.json.JSONException
import retrofit2.HttpException
import java.net.ConnectException

/**
 * 错误信息处理
 */
object ExceptionHandle {

    //==========================约定异常
    /**
     * 未知错误
     */
    const val UNKNOWN = 1000
    /**
     * 解析错误
     */
    const val PARSE_ERROR = 1001
    /**
     * 网络错误
     */
    const val NETWORD_ERROR = 1002
    /**
     * 协议出错
     */
    const val HTTP_ERROR = 1003

    /**
     * 证书出错
     */
    const val SSL_ERROR = 1005

    /**
     * 连接超时
     */
    const val TIMEOUT_ERROR = 1006
    //=======================================

    private val UNAUTHORIZED = 401
    private val FORBIDDEN = 403
    private val NOT_FOUND = 404
    private val REQUEST_TIMEOUT = 408
    private val INTERNAL_SERVER_ERROR = 500
    private val SERVICE_UNAVAILABLE = 503

    fun handleException(throwable: Throwable): ErrorBean {
        val errorBean: ErrorBean
        when (throwable) {
            is HttpException -> {
                when (throwable.code()) {
                    UNAUTHORIZED -> errorBean = ErrorBean(UNAUTHORIZED, "操作未授权")
                    FORBIDDEN -> errorBean = ErrorBean(FORBIDDEN, "请求被拒绝")
                    NOT_FOUND -> errorBean = ErrorBean(NOT_FOUND, "资源不存在")
                    REQUEST_TIMEOUT -> errorBean = ErrorBean(REQUEST_TIMEOUT, "服务器执行超时")
                    INTERNAL_SERVER_ERROR -> errorBean = ErrorBean(INTERNAL_SERVER_ERROR, "服务器内部错误")
                    SERVICE_UNAVAILABLE -> errorBean = ErrorBean(SERVICE_UNAVAILABLE, "服务器不可用")
                    else -> errorBean = ErrorBean(HTTP_ERROR, "网络错误")
                }
                return errorBean
            }
            is JsonParseException, is JSONException, is ParseException, is MalformedJsonException -> {
                errorBean = ErrorBean(PARSE_ERROR, "解析错误")
                return errorBean
            }
            is ConnectException -> {
                errorBean = ErrorBean(NETWORD_ERROR, "连接失败")
                return errorBean
            }
            is javax.net.ssl.SSLException -> {
                errorBean = ErrorBean(SSL_ERROR, "证书验证失败")
                return errorBean
            }
            is ConnectTimeoutException, is java.net.SocketTimeoutException -> {
                errorBean = ErrorBean(TIMEOUT_ERROR, "连接超时")
                return errorBean
            }
            is java.net.UnknownHostException -> {
                errorBean = ErrorBean(TIMEOUT_ERROR, "主机地址未知")
                return errorBean
            }
            is ServerException -> {
                errorBean = ErrorBean(throwable.code, throwable.localizedMessage)
                return errorBean
            }
            else -> {
                errorBean = ErrorBean(UNKNOWN, "未知错误")
                return errorBean
            }
        }
    }

}
