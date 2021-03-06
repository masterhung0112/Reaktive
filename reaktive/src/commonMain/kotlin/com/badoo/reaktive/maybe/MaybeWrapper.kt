package com.badoo.reaktive.maybe

import com.badoo.reaktive.annotations.UseReturnValue
import com.badoo.reaktive.disposable.Disposable
import com.badoo.reaktive.maybe.subscribe as subscribeRx

/**
 * Wrappers are normally exposed to Swift.
 * You might want to enable Objective-C generics,
 * please refer to the [documentation][https://kotlinlang.org/docs/reference/native/objc_interop.html#to-use]
 * for more information.
 * You can also extend the wrapper class if you need to expose any additional operators.
 */
open class MaybeWrapper<out T>(inner: Maybe<T>) : Maybe<T> by inner {

    @UseReturnValue
    fun subscribe(
        isThreadLocal: Boolean = false,
        onSubscribe: ((Disposable) -> Unit)? = null,
        onError: ((Throwable) -> Unit)? = null,
        onComplete: (() -> Unit)? = null,
        onSuccess: ((T) -> Unit)? = null
    ): Disposable =
        subscribeRx(
            isThreadLocal = isThreadLocal,
            onSubscribe = onSubscribe,
            onError = onError,
            onComplete = onComplete,
            onSuccess = onSuccess
        )
}

fun <T> Maybe<T>.wrap(): MaybeWrapper<T> = MaybeWrapper(this)
