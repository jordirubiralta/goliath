package com.jrubiralta.data.model.dto

import com.google.gson.annotations.SerializedName

class RatesDTO(
    @SerializedName("from") val from: String? = null,
    @SerializedName("to") val to: String? = null,
    @SerializedName("rate") val rate: Double? = null) {

}