package com.jrubiralta.domain.interactor.transactions

import com.jrubiralta.domain.model.Transaction
import com.jrubiralta.domain.repository.TransactionRepository
import com.jrubiralta.goliath.domain.executor.Executor
import com.jrubiralta.goliath.domain.interactor.SingleInteractor
import io.reactivex.Single

class SaveTransactionsUseCase(
    private val repository: TransactionRepository,
    executor: Executor)
    : SingleInteractor<Int>(executor = executor) {

    fun execute(list: List<Transaction>, onSuccess: (Int) -> Unit, onError: (Throwable) -> Unit) {
        super.execute(onSuccess = onSuccess, onError = onError, single = Single.defer {repository.saveTransactions(list)})
    }
}