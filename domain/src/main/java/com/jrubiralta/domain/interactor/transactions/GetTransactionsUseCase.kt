package com.jrubiralta.domain.interactor.transactions

import com.jrubiralta.domain.model.Transaction
import com.jrubiralta.domain.repository.TransactionRepository
import com.jrubiralta.goliath.domain.executor.Executor
import com.jrubiralta.goliath.domain.interactor.SingleInteractor

class GetTransactionsUseCase(
    private val repository: TransactionRepository,
    executor: Executor)
    : SingleInteractor<List<Transaction>>(executor = executor) {

    fun execute(onSuccess: (List<Transaction>) -> Unit, onError: (Throwable) -> Unit) {
        super.execute(onSuccess = onSuccess, onError = onError, single = repository.getTransaction())
    }
}