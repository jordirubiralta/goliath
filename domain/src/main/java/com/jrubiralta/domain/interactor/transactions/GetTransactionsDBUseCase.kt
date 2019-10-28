package com.jrubiralta.domain.interactor.transactions

import com.jrubiralta.domain.model.Transaction
import com.jrubiralta.domain.repository.TransactionRepository
import com.jrubiralta.goliath.domain.executor.Executor
import com.jrubiralta.goliath.domain.interactor.MaybeInteractor
import io.reactivex.Maybe


class GetTransactionsDBUseCase(
    private val repository: TransactionRepository,
    executor: Executor)
    : MaybeInteractor<List<Transaction>>(executor = executor) {

    fun execute(onSuccess: (List<Transaction>) -> Unit, onEmpty: () -> Unit, onError: (Throwable) -> Unit) {
        super.execute(onSuccess, onEmpty, onError, maybe = Maybe.defer { repository.getTransactionsFromDB() })
    }
}