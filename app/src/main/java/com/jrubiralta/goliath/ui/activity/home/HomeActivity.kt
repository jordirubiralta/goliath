package com.jrubiralta.goliath.ui.activity.home

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
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
import com.jrubiralta.goliath.ui.adapter.TransactionListAdapter
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

    private val transactionListAdapter = TransactionListAdapter()

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
        rv.adapter = transactionListAdapter
        rv.layoutManager = LinearLayoutManager(applicationContext)
    }

    private fun initListeners() {
    }

    private fun initData() {
        val list = mutableListOf<TransactionListItemView>()
        list.add(TransactionListItemView(sku = "AAA", amount = 2.3, currency = CurrencyType.EUR))
        list.add(TransactionListItemView(sku = "AAA", amount = 2.3, currency = CurrencyType.EUR))
        list.add(TransactionListItemView(sku = "AAA", amount = 2.3, currency = CurrencyType.EUR))
        list.add(TransactionListItemView(sku = "AAA", amount = 2.3, currency = CurrencyType.EUR))
        transactionListAdapter.replace(list)
    }

}

interface HomeListener {
}