package com.jrubiralta.goliath.ui.activity.product

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
import com.jrubiralta.goliath.model.CurrencyType
import com.jrubiralta.goliath.presenter.home.HomePresenter
import com.jrubiralta.goliath.presenter.home.HomePresenterImpl
import com.jrubiralta.goliath.presenter.product.ProductDetailPresenter
import com.jrubiralta.goliath.presenter.product.ProductDetailPresenterImpl
import com.jrubiralta.goliath.ui.activity.BaseActivity
import com.jrubiralta.goliath.ui.adapter.DefaultSpinnerAdapter
import com.jrubiralta.goliath.ui.adapter.TransactionListAdapter
import com.jrubiralta.goliath.ui.view.home.HomeView
import com.jrubiralta.goliath.ui.view.product.ProductDetailView
import kotlinx.android.synthetic.main.activity_home.*

class ProductDetailActivity
    : BaseActivity<ProductDetailPresenter, ProductDetailView>(),
    ProductDetailView,
    ProductDetailListener {

    companion object {
        const val KEY_LIST = "KEY_LIST"
    }

    override val presenter: ProductDetailPresenter by instance()

    override val layoutResourceId = R.layout.activity_home

    override val activityModule: Kodein.Module = Kodein.Module {
        bind<ProductDetailPresenter>() with provider {
            ProductDetailPresenterImpl(
                view = this@ProductDetailActivity)
        }
    }

    private val currencyList = mutableListOf(CurrencyType.EUR.toString(), CurrencyType.AUD.toString(), CurrencyType.CAD.toString(), CurrencyType.USD.toString())

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
    }



}

interface ProductDetailListener {
}