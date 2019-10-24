package com.jrubiralta.goliath.ui.activity.home

import android.os.Bundle
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.provider
import com.jrubiralta.goliath.R
import com.jrubiralta.goliath.model.CurrencyType
import com.jrubiralta.goliath.model.TransactionListItemView
import com.jrubiralta.goliath.presenter.home.HomePresenter
import com.jrubiralta.goliath.presenter.home.HomePresenterImpl
import com.jrubiralta.goliath.ui.activity.BaseActivity
import com.jrubiralta.goliath.ui.view.home.HomeView
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity
    : BaseActivity<HomePresenter, HomeView>(),
        HomeView,
        HomeListener {

    override val presenter: HomePresenter by instance()

    override val layoutResourceId = R.layout.activity_home

    override val activityModule: Kodein.Module = Kodein.Module {
        bind<HomePresenter>() with provider {
            HomePresenterImpl(
                view = this@HomeActivity
            )
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
    }

    private fun initListeners() {
    }

    private fun initData() {

    }

    override fun onBackPressed() {

    }
}

interface HomeListener {
}