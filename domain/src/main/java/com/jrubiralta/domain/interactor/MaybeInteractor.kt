package com.jrubiralta.goliath.domain.interactor

import com.jrubiralta.goliath.domain.executor.Executor
import io.reactivex.Maybe
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableMaybeObserver

abstract class MaybeInteractor<T : Any>(
        private val executor: Executor,
        private val compositeDisposable: CompositeDisposable = CompositeDisposable()) {

    fun execute(onSuccess: (T) -> Unit,
                onEmpty: () -> Unit,
                onError: (Throwable) -> Unit,
                maybe: Maybe<T>): Maybe<T> {
        val maybeWithSchedulers = maybe
                .subscribeOn(executor.new())
                .observeOn(executor.main())

        compositeDisposable.add(maybeWithSchedulers
                .subscribeWith(object : DisposableMaybeObserver<T>() {
                    override fun onError(e: Throwable) {
                        onError(e)
                    }

                    override fun onSuccess(t: T) {
                        onSuccess(t)
                    }

                    override fun onComplete() {
                        onEmpty()
                    }
                }))

        return maybeWithSchedulers
    }

    fun clear() {
        compositeDisposable.clear()
    }

}
