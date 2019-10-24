package com.enel.edist.presenter.splash

import com.jrubiralta.goliath.presenter.BasePresenterImpl
import com.jrubiralta.goliath.presenter.splash.SplashPresenter
import com.jrubiralta.goliath.ui.view.splash.SplashView
import io.reactivex.disposables.Disposable

class SplashPresenterImpl(
    view: SplashView)
    : BasePresenterImpl<SplashView>(view),
    SplashPresenter {

    companion object {
        const val TIME_WAIT: Long = 500
    }

    private var subscribe: Disposable? = null

    override fun pause() {
        super.pause()
        subscribe?.dispose()
    }

    override fun destroy() {
        super.destroy()
    }
}
