package com.mufeng.mvvmlib.http.handler

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

/**
 * @创建者 MuFeng-T
 * @创建时间 2020/2/21 17:53
 * @描述
 */
open class RequestViewModel : ViewModel() {

    private fun <Response> api(apiDSL: ViewModelDsl<Response>.() -> Unit) {
        ViewModelDsl<Response>().apply(apiDSL).launch(viewModelScope)
    }

    @JvmOverloads
    protected fun <Response> apiCallback(
        request: suspend () -> Response,
        onResponse: ((Response) -> Unit),
        onStart: (() -> Boolean)? = null,
        onError: ((java.lang.Exception) -> Boolean)? = null,
        onFinally: (() -> Boolean)? = null
    ) {

        api<Response> {

            onRequest {
                request.invoke()
            }

            onResponse {
                onResponse.invoke(it)
            }

            onStart {
                val override = onStart?.invoke()
                if (override == null || !override) {
                    onApiStart()
                }
                false
            }

            onError {
                val override = onError?.invoke(it)
                if (override == null || !override) {
                    onApiError(it)
                }
                false

            }

            onFinally {
                val override = onFinally?.invoke()
                if (override == null || !override) {
                    onApiFinally()
                }
                false
            }
        }
    }


    protected fun <Response> apiDSL(apiDSL: ViewModelDsl<Response>.() -> Unit) {
        val dsl = ViewModelDsl<Response>()
        api<Response> {
            onRequest {
                dsl.apply(apiDSL).request()
            }

            onResponse {
                dsl.apply(apiDSL).onResponse?.invoke(it)
            }

            onStart {
                val override = dsl.apply(apiDSL).onStart?.invoke()
                if (override == null || !override) {
                    onApiStart()

                }
                override
            }

            onError { error ->
                val override = dsl.apply(apiDSL).onError?.invoke(error)
                if (override == null || !override) {
                    onApiError(error)
                }
                override
            }

            onFinally {
                val override = dsl.apply(apiDSL).onFinally?.invoke()
                if (override == null || !override) {
                    onApiFinally()
                }
                override
            }
        }
    }


    protected fun <Response> apiLiveData(
        context: CoroutineContext = EmptyCoroutineContext,
        timeoutInMs: Long = 3000L,
        request: suspend () -> Response
    ): LiveData<Result<Response>> {

        return androidx.lifecycle.liveData(context, timeoutInMs) {
            emit(Result.Start())
            try {
                emit(withContext(Dispatchers.IO) {
                    Result.Response(request())
                })
            } catch (e: Exception) {
                e.printStackTrace()
                emit(Result.Error(e))
            } finally {
                emit(Result.Finally())
            }
        }
    }

    protected open fun onApiStart() {}

    protected open fun onApiError(e: Throwable?) {}

    protected open fun onApiFinally() {}

}

/**
 * Result必须加泛型 不然response的泛型就会被擦除!!
 * damn it
 */
sealed class Result<T> {
    class Start<T> : Result<T>()
    class Finally<T> : Result<T>()
    data class Response<T>(val response: T) : Result<T>()
    data class Error<T>(val exception: Exception) : Result<T>()
}