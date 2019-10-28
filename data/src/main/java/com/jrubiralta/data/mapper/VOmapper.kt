package com.jrubiralta.data.mapper

import com.jrubiralta.data.model.vo.TransactionVO
import com.jrubiralta.domain.model.Transaction
import com.jrubiralta.goliath.domain.constants.Constants.Companion.EMPTY_STRING

fun Transaction.toVo(): TransactionVO =
    TransactionVO(
        id = sku + amount + currency,
        sku = sku ?: EMPTY_STRING,
        amount = amount ?: EMPTY_STRING,
        currency = currency ?: EMPTY_STRING)

fun TransactionVO.toModel(): Transaction =
    Transaction(
        sku = sku,
        amount = amount,
        currency = currency)