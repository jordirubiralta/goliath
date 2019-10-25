package com.jrubiralta.data.model

import com.google.gson.annotations.SerializedName

class TransactionDTO(
    @SerializedName("sku") val sku: String? = null,
    @SerializedName("amount") val amount: String? = null,
    @SerializedName("currency") val currency: String? = null) {

}