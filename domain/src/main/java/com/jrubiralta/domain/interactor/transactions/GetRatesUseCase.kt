package com.jrubiralta.domain.interactor.transactions

import com.jrubiralta.domain.model.Rates
import com.jrubiralta.domain.repository.TransactionRepository
import com.jrubiralta.goliath.domain.executor.Executor
import com.jrubiralta.goliath.domain.interactor.SingleInteractor


class GetRatesUseCase(
    private val repository: TransactionRepository,
    executor: Executor
)
    : SingleInteractor<List<Rates>>(executor = executor) {

    fun execute(onSuccess: (List<Rates>) -> Unit, onError: (Throwable) -> Unit) {
        super.execute(onSuccess = onSuccess, onError = onError, single = repository.getRates())
    }
}