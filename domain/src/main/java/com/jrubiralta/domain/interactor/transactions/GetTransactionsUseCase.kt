package com.jrubiralta.domain.interactor.transactions

import com.jrubiralta.domain.model.TransactionList
import com.jrubiralta.domain.repository.TransactionRepository
import com.jrubiralta.goliath.domain.executor.Executor
import com.jrubiralta.goliath.domain.interactor.SingleInteractor
import io.reactivex.Single

class GetTransactionsUseCase(
    private val repository: TransactionRepository,
    executor: Executor)
    : SingleInteractor<TransactionList>(executor = executor) {

    fun execute(onSuccess: (TransactionList) -> Unit, onError: (Throwable) -> Unit) {
        super.execute(onSuccess = onSuccess, onError = onError, single = repository.getTransaction())
    }
}