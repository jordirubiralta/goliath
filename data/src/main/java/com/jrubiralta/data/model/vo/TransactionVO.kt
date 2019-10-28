package com.jrubiralta.data.model.vo

import com.jrubiralta.goliath.domain.constants.Constants.Companion.EMPTY_STRING
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class TransactionVO(
    @PrimaryKey var id: String = EMPTY_STRING,
    var sku: String = EMPTY_STRING,
    var amount: String = EMPTY_STRING,
    var currency: String = EMPTY_STRING) : RealmObject()
