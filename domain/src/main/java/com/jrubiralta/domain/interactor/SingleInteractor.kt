package com.jrubiralta.goliath.domain.interactor

import com.jrubiralta.goliath.domain.executor.Executor
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver

abstract class SingleInteractor<T : Any>(
    private val executor: Executor,
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()) {

    fun execute(onSuccess: (T) -> Unit,
                onError: (Throwable) -> Unit,
                single: Single<T>): Single<T> {
        val singleWithSchedulers = single
            .subscribeOn(executor.new())
            .observeOn(executor.main())

        compositeDisposable.add(singleWithSchedulers
            .subscribeWith(object : DisposableSingleObserver<T>() {
                override fun onError(e: Throwable) {
                    onError(e)
                }

                override fun onSuccess(t: T) {
                    onSuccess(t)
                }

            }))

        return singleWithSchedulers
    }

    fun clear() {
        compositeDisposable.clear()
    }
}