package com.mufeng.mvvmlib.http

import android.util.MalformedJsonException
import com.google.gson.JsonParseException
import org.apache.http.conn.ConnectTimeoutException
import org.json.JSONException
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.text.ParseException

/**
 * @创建者 MuFeng-T
 * @创建时间 2020/2/24 16:05
 * @描述
 */
class ErrorHandler {

    companion object {
        // 对应HTTP的状态码
        private const val UNAUTHORIZED = 401
        private const val FORBIDDEN = 403
        private const val NOT_FOUND = 404
        private const val REQUEST_TIMEOUT = 408
        private const val INTERNAL_SERVER_ERROR = 500
        private const val SERVICE_UNAVAILABLE = 503

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

        fun handlerException(e: Throwable?) : ApiException{
            val apiException: ApiException
            when(e) {
                is HttpException -> {
                    apiException = ApiException(e.code(),e)
                    when(e.code()){
                        UNAUTHORIZED -> apiException.displayMessage = "操作未授权"
                        FORBIDDEN -> apiException.displayMessage = "请求被拒绝"
                        NOT_FOUND -> apiException.displayMessage = "资源不存在"
                        REQUEST_TIMEOUT -> apiException.displayMessage = "服务器执行超时"
                        INTERNAL_SERVER_ERROR -> apiException.displayMessage = "服务器内部错误"
                        SERVICE_UNAVAILABLE -> apiException.displayMessage = "服务器不可用"
                        else -> apiException.displayMessage = "网络错误"
                    }
                    return apiException
                }
                is JsonParseException, is JSONException, is ParseException, is MalformedJsonException -> {
                    apiException = ApiException(PARSE_ERROR,e)
                    apiException.displayMessage = "解析错误"
                    return apiException
                }
                is ConnectException -> {
                    apiException = ApiException(NETWORD_ERROR,e)
                    apiException.displayMessage = "连接失败"
                    return apiException
                }
                is javax.net.ssl.SSLException -> {
                    apiException = ApiException(SSL_ERROR,e)
                    apiException.displayMessage = "证书验证失败"
                    return apiException
                }
                is SocketTimeoutException, is ConnectTimeoutException -> {
                    apiException = ApiException(TIMEOUT_ERROR,e)
                    apiException.displayMessage = "连接超时"
                    return apiException
                }
                is UnknownHostException -> {
                    apiException = ApiException(TIMEOUT_ERROR,e)
                    apiException.displayMessage = "主机地址未知"
                    return apiException
                }
                is ApiException -> {
                    return e
                }
                else -> {
                    apiException = ApiException(TIMEOUT_ERROR,e!!)
                    apiException.displayMessage = e.message!!
                    return apiException
                }
            }
        }
    }

}

open class ApiException(var code: Int, throwable: Throwable) : Exception(throwable){
    var displayMessage: String = ""
}

