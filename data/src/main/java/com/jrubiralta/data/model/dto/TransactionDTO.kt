package com.jrubiralta.data.model.dto

import com.google.gson.annotations.SerializedName
import com.jrubiralta.goliath.domain.constants.Constants.Companion.DEFAULT_DOUBLE

class TransactionDTO(
    @SerializedName("sku") val sku: String? = null,
    @SerializedName("amount") val amount: Double? = DEFAULT_DOUBLE,
    @SerializedName("currency") val currency: String? = null) {

}