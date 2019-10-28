package com.jrubiralta.domain.interactor.transactions

import com.jrubiralta.domain.repository.TransactionRepository
import com.jrubiralta.goliath.domain.executor.Executor
import com.jrubiralta.goliath.domain.interactor.SingleInteractor

class ClearAllDataBaseUseCase(
    private val repository: TransactionRepository,
    executor: Executor
)
    : SingleInteractor<Boolean>(executor = executor) {

    fun execute(onSuccess: (Boolean) -> Unit, onError: (Throwable) -> Unit) {
        super.execute(onSuccess = onSuccess, onError = onError, single = repository.clearAllDataBase())
    }
}