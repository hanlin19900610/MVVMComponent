package com.mufeng.mvvmlib.ext

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import com.uber.autodispose.*
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider
import io.reactivex.*

fun <T> Observable<T>.bindLifecycle(
    lifecycleOwner: LifecycleOwner
): ObservableSubscribeProxy<T> =
        `as`(AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from(lifecycleOwner)))


fun <T> Flowable<T>.bindLifecycle(
    lifecycleOwner: LifecycleOwner
): FlowableSubscribeProxy<T> =
        `as`(AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from(lifecycleOwner)))

fun <T> Single<T>.bindLifecycle(
    lifecycleOwner: LifecycleOwner
): SingleSubscribeProxy<T> =
        `as`(AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from(lifecycleOwner)))

fun <T> Maybe<T>.bindLifecycle(
    lifecycleOwner: LifecycleOwner
): MaybeSubscribeProxy<T> =
        `as`(AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from(lifecycleOwner)))

fun Completable.bindLifecycle(
    lifecycleOwner: LifecycleOwner
): CompletableSubscribeProxy =
        `as`(AutoDispose.autoDisposable<Unit>(AndroidLifecycleScopeProvider.from(lifecycleOwner)))

fun <T> Observable<T>.bindLifecycleEvent(
    lifecycleOwner: LifecycleOwner,
    lifecycleEvent: Lifecycle.Event = Lifecycle.Event.ON_DESTROY
): ObservableSubscribeProxy<T> =
        `as`(AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from(lifecycleOwner, lifecycleEvent)))

fun <T> Flowable<T>.bindLifecycleEvent(
    lifecycleOwner: LifecycleOwner,
    lifecycleEvent: Lifecycle.Event = Lifecycle.Event.ON_DESTROY
): FlowableSubscribeProxy<T> =
        `as`(AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from(lifecycleOwner, lifecycleEvent)))

fun <T> Single<T>.bindLifecycleEvent(
    lifecycleOwner: LifecycleOwner,
    lifecycleEvent: Lifecycle.Event = Lifecycle.Event.ON_DESTROY
): SingleSubscribeProxy<T> =
        `as`(AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from(lifecycleOwner, lifecycleEvent)))

fun <T> Maybe<T>.bindLifecycleEvent(
    lifecycleOwner: LifecycleOwner,
    lifecycleEvent: Lifecycle.Event = Lifecycle.Event.ON_DESTROY
): MaybeSubscribeProxy<T> =
        `as`(AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from(lifecycleOwner, lifecycleEvent)))

fun Completable.bindLifecycleEvent(
    lifecycleOwner: LifecycleOwner,
    lifecycleEvent: Lifecycle.Event = Lifecycle.Event.ON_DESTROY
): CompletableSubscribeProxy =
        `as`(AutoDispose.autoDisposable<Unit>(AndroidLifecycleScopeProvider.from(lifecycleOwner, lifecycleEvent)))