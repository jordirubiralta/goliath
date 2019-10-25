package com.jrubiralta.data.mapper

import com.jrubiralta.data.model.TransactionDTO
import com.jrubiralta.data.model.TransactionListDTO
import com.jrubiralta.domain.model.Transaction
import com.jrubiralta.domain.model.TransactionList
import com.jrubiralta.goliath.domain.constants.Constants

fun TransactionDTO.toModel() : Transaction =
    Transaction(
        sku = sku ?: Constants.EMPTY_STRING,
        amount = amount ?: Constants.EMPTY_STRING,
        currency = currency ?: Constants.EMPTY_STRING)

fun TransactionListDTO.toModel() : TransactionList =
    TransactionList(list = listDTO.map { it.toModel() })