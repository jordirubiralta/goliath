package com.jrubiralta.data.mapper

import com.jrubiralta.data.model.vo.TransactionVO
import com.jrubiralta.domain.model.Transaction
import com.jrubiralta.goliath.domain.constants.Constants.Companion.DEFAULT_DOUBLE
import com.jrubiralta.goliath.domain.constants.Constants.Companion.EMPTY_STRING

fun Transaction.toVo(): TransactionVO =
    TransactionVO(
        id = sku + amount + currency,
        sku = sku,
        amount = amount,
        currency = currency)

fun TransactionVO.toModel(): Transaction =
    Transaction(
        sku = sku,
        amount = amount,
        currency = currency)