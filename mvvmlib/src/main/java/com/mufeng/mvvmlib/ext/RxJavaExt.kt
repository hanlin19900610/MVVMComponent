package com.mufeng.mvvmlib.ext

import io.reactivex.CompletableTransformer
import io.reactivex.FlowableTransformer
import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

//线程切换
fun <T> io_main() : ObservableTransformer<T, T> {
    return ObservableTransformer { upstream ->
        upstream.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }
}

fun  io_main_completable() : CompletableTransformer {
    return CompletableTransformer { upstream ->
        upstream.subscribeOn(Schedulers.io())
            .unsubscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}


//线程切换
fun <T> io_main_flowable() : FlowableTransformer<T, T> {
    return FlowableTransformer { upstream ->
        upstream.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }
}