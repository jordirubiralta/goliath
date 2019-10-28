package com.jrubiralta.data.mapper

import com.jrubiralta.data.model.dto.RatesDTO
import com.jrubiralta.data.model.dto.TransactionDTO
import com.jrubiralta.domain.model.Rates
import com.jrubiralta.domain.model.Transaction
import com.jrubiralta.goliath.domain.constants.Constants

fun TransactionDTO.toModel() : Transaction =
    Transaction(
        sku = sku ?: Constants.EMPTY_STRING,
        amount = amount ?: Constants.DEFAULT_DOUBLE,
        currency = currency ?: Constants.EMPTY_STRING)


fun RatesDTO.toModel() : Rates =
    Rates(
        from = from ?: Constants.EMPTY_STRING,
        to = to ?: Constants.EMPTY_STRING,
        rate = rate ?: Constants.DEFAULT_DOUBLE)

