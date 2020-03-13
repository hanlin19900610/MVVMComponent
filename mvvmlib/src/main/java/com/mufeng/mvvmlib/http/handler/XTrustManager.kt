package com.mufeng.mvvmlib.http.handler

import java.security.cert.X509Certificate
import javax.net.ssl.X509TrustManager

/**
 * @创建者 MuFeng-T
 * @创建时间 2020/2/21 17:21
 * @描述
 */
class XTrustManager : X509TrustManager {

    override fun checkClientTrusted(chain: Array<out X509Certificate>?, authType: String?) {
    }

    override fun checkServerTrusted(chain: Array<out X509Certificate>?, authType: String?) {
    }

    override fun getAcceptedIssuers(): Array<X509Certificate> {
        return emptyArray()
    }
}