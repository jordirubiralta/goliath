package com.jrubiralta.data.model

import com.google.gson.annotations.SerializedName

class RatesDTO(
    @SerializedName("from") val from: String? = null,
    @SerializedName("to") val to: String? = null,
    @SerializedName("rate") val rate: String? = null) {

}