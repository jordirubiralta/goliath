package com.jrubiralta.goliath.router

import android.app.Activity
import android.content.Intent
import com.jrubiralta.goliath.router.android.NavParams
import com.jrubiralta.goliath.ui.activity.home.HomeActivity

object Router {

    fun openHomeActivity(params: NavParams) {
        val intent = Intent(params.activity, HomeActivity::class.java)
        openActivity(params.activity, intent, params.finishActivity)
    }

    private fun openActivity(activity: Activity, intent: Intent, finishActivity: Boolean = true) {
        activity.startActivity(intent)
        if (finishActivity) activity.finish()
    }

}