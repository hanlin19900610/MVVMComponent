package com.mufeng.mvvmlib.basic

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

/**
 * @创建者 MuFeng-T
 * @创建时间 2019/11/6 13:50
 * @描述
 */
/**
 * Used as a wrapper for data that is exposed via a LiveData that represents an event.
 */
open class Event<out T>(private val content: T) {

    var hasBeenHandled = false
        private set // Allow external read but not write

    /**
     * Returns the content and prevents its use again.
     */
    fun getContentIfNotHandled(): T? {
        return if (hasBeenHandled) {
            null
        } else {
            hasBeenHandled = true
            content
        }
    }

    /**
     * Returns the content, even if it's already been handled.
     */
    fun peekContent(): T = content
}

class EventObserver<T>(private val onEventUnhandledContent: (T) -> Unit) : Observer<Event<T>> {
    override fun onChanged(event: Event<T>?) {
        event?.getContentIfNotHandled()?.let { value ->
            onEventUnhandledContent(value)
        }
    }
}

inline fun<T> LiveData<Event<T>>.eventObserver(
    owner: LifecycleOwner,
    crossinline onEventUnhandledContent: (T) -> Unit
): Observer<Event<T>>{
    val wrappedObserver = Observer<Event<T>>{ t ->
        t?.getContentIfNotHandled()?.let { value ->
            onEventUnhandledContent(value)
        }
    }
    observe(owner, wrappedObserver)
    return wrappedObserver
}