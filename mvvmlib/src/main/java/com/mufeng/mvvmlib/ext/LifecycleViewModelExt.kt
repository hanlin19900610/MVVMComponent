package com.mufeng.mvvmlib.ext

import androidx.lifecycle.Lifecycle
import com.mufeng.mvvmlib.base.BaseViewModel
import com.uber.autodispose.*
import io.reactivex.*


fun <T> Observable<T>.bindLifecycle(
    lifecycleViewModel: BaseViewModel,
    lifecycleEvent: Lifecycle.Event = Lifecycle.Event.ON_DESTROY
): ObservableSubscribeProxy<T> =
        bindLifecycleEvent(
                lifecycleViewModel.lifecycleOwner
                ?: throw throwableWhenLifecycleOwnerIsNull(lifecycleViewModel),
                lifecycleEvent
        )

fun <T> Flowable<T>.bindLifecycle(
    lifecycleViewModel: BaseViewModel,
    lifecycleEvent: Lifecycle.Event = Lifecycle.Event.ON_DESTROY
): FlowableSubscribeProxy<T> =
        bindLifecycleEvent(
                lifecycleViewModel.lifecycleOwner
                ?: throw throwableWhenLifecycleOwnerIsNull(lifecycleViewModel),
                lifecycleEvent
        )

fun <T> Single<T>.bindLifecycle(
    lifecycleViewModel: BaseViewModel,
    lifecycleEvent: Lifecycle.Event = Lifecycle.Event.ON_DESTROY
): SingleSubscribeProxy<T> =
        bindLifecycleEvent(
                lifecycleViewModel.lifecycleOwner
                ?: throw throwableWhenLifecycleOwnerIsNull(lifecycleViewModel),
                lifecycleEvent
        )

fun <T> Maybe<T>.bindLifecycle(
    lifecycleViewModel: BaseViewModel,
    lifecycleEvent: Lifecycle.Event = Lifecycle.Event.ON_DESTROY
): MaybeSubscribeProxy<T> =
        bindLifecycleEvent(
                lifecycleViewModel.lifecycleOwner
                ?: throw throwableWhenLifecycleOwnerIsNull(lifecycleViewModel),
                lifecycleEvent
        )

fun Completable.bindLifecycle(
    lifecycleViewModel: BaseViewModel,
    lifecycleEvent: Lifecycle.Event = Lifecycle.Event.ON_DESTROY
): CompletableSubscribeProxy =
        bindLifecycleEvent(
                lifecycleViewModel.lifecycleOwner
                ?: throw throwableWhenLifecycleOwnerIsNull(lifecycleViewModel),
                lifecycleEvent
        )

private fun throwableWhenLifecycleOwnerIsNull(viewModel: BaseViewModel): NullPointerException =
        NullPointerException("$viewModel's lifecycleOwner is null.")