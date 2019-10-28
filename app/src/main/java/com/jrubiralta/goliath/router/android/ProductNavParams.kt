package com.jrubiralta.goliath.router.android

import android.app.Activity
import com.jrubiralta.domain.model.Transaction

class ProductNavParams(
    activity: Activity,
    val sku: String,
    finishActivity: Boolean = true)
    : NavParams(activity, finishActivity) {
}