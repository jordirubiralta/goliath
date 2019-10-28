package com.jrubiralta.goliath.ui.activity.product

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.provider
import com.jrubiralta.domain.model.Transaction
import com.jrubiralta.goliath.R
import com.jrubiralta.goliath.presenter.product.ProductDetailPresenter
import com.jrubiralta.goliath.presenter.product.ProductDetailPresenterImpl
import com.jrubiralta.goliath.ui.activity.BaseActivity
import com.jrubiralta.goliath.ui.adapter.TransactionListAdapter
import com.jrubiralta.goliath.ui.view.product.ProductDetailView
import kotlinx.android.synthetic.main.activity_product_detail.*

class ProductDetailActivity
    : BaseActivity<ProductDetailPresenter, ProductDetailView>(),
    ProductDetailView {

    companion object {
        const val KEY_SKU = "KEY_SKU"
    }

    override val presenter: ProductDetailPresenter by instance()

    override val layoutResourceId = R.layout.activity_product_detail

    override val activityModule: Kodein.Module = Kodein.Module {
        bind<ProductDetailPresenter>() with provider {
            ProductDetailPresenterImpl(
                view = this@ProductDetailActivity,
                getRatesUseCase = instance(),
                getProductTransactionsUseCase = instance())
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
        presenter.getRates()
        presenter.setProductList()
    }

    override fun getProduct(): String = intent?.extras?.getString(KEY_SKU)
        ?: ""

    override fun updateList(list: List<Transaction>) {
        transactionListAdapter.replace(list.toMutableList())
    }
}
