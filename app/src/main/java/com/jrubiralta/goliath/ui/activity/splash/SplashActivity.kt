package com.jrubiralta.goliath.ui.activity.splash

import android.os.Bundle
import com.enel.edist.presenter.splash.SplashPresenterImpl
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.provider
import com.jrubiralta.goliath.R
import com.jrubiralta.goliath.router.Router
import com.jrubiralta.goliath.router.android.NavParams
import com.jrubiralta.goliath.presenter.splash.SplashPresenter
import com.jrubiralta.goliath.ui.activity.BaseActivity
import com.jrubiralta.goliath.ui.view.splash.SplashView
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity
    : BaseActivity<SplashPresenter, SplashView>(),
    SplashView {

    override val presenter: SplashPresenter by instance()

    override val layoutResourceId = R.layout.activity_splash

    override val activityModule: Kodein.Module = Kodein.Module {
        bind<SplashPresenter>() with provider {
            SplashPresenterImpl(
                view = this@SplashActivity,
                clearAllDataBaseUseCase = instance(),
                getTransactionsUseCase = instance(),
                saveTransactionsUseCase = instance())
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    private fun init() {
        initView()
        initListeners()
        initData()
    }

    private fun initView() {
        setContentView(layoutResourceId)
    }

    private fun initListeners() {
    }

    private fun initData() {
        presenter.clearDataBase()
    }

    override fun onBackPressed() {
    }

    override fun goToHomeActivity() {
        Router.openHomeActivity(NavParams(this@SplashActivity, true))
    }

}
