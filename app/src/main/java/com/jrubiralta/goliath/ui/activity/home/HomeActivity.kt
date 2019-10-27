package com.jrubiralta.goliath.ui.activity.home

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.provider
import com.jrubiralta.domain.model.Transaction
import com.jrubiralta.goliath.R
import com.jrubiralta.goliath.presenter.home.HomePresenter
import com.jrubiralta.goliath.presenter.home.HomePresenterImpl
import com.jrubiralta.goliath.router.Router
import com.jrubiralta.goliath.router.android.NavParams
import com.jrubiralta.goliath.ui.activity.BaseActivity
import com.jrubiralta.goliath.ui.adapter.ProductsListAdapter
import com.jrubiralta.goliath.ui.view.home.HomeView
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity
    : BaseActivity<HomePresenter, HomeView>(),
        HomeView,
        HomeListener {

    override val presenter: HomePresenter by instance()

    override val layoutResourceId = R.layout.activity_products

    override val activityModule: Kodein.Module = Kodein.Module {
        bind<HomePresenter>() with provider {
            HomePresenterImpl(
                view = this@HomeActivity,
                getTransactionsUseCase = instance()
            )
        }
    }

    private val productsListAdapter = ProductsListAdapter() {
        Router.openProductActivity(NavParams(this@HomeActivity, false))
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
        rv.adapter = productsListAdapter
        rv.layoutManager = LinearLayoutManager(applicationContext)
    }

    private fun initListeners() {
    }

    private fun initData() {
        presenter.getTransactions()
    }

    override fun sortList(transactionList: List<Transaction>): List<Transaction> {
        return transactionList.sortedBy { it.sku }
    }

    override fun updateList(transactionList: List<Transaction>) {
        val list = mutableListOf<String>()
        transactionList.map {
            val id = it.sku
            if (!list.contains(id)) { list.add(id) }
        }
        productsListAdapter.replace(list)
    }

    override fun sumTransactions(transactionList: List<Transaction>) {
        var total = 0.0
        transactionList.map { total += it.amount.toDouble() }
        tv_total_sum.text = total.toString()
    }
}

interface HomeListener {
}