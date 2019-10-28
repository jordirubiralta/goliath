package com.jrubiralta.goliath.presenter.splash

import com.jrubiralta.goliath.presenter.BasePresenter
import com.jrubiralta.goliath.ui.view.splash.SplashView

interface SplashPresenter
    : BasePresenter<SplashView> {

    fun clearDataBase()

}
