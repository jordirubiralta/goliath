package com.jrubiralta.data.mapper

import com.jrubiralta.data.model.TransactionDTO
import com.jrubiralta.domain.model.Transaction
import com.jrubiralta.goliath.domain.constants.Constants

fun TransactionDTO.toModel() : Transaction =
    Transaction(
        sku = sku ?: Constants.EMPTY_STRING,
        amount = amount ?: Constants.EMPTY_STRING,
        currency = currency ?: Constants.EMPTY_STRING)
