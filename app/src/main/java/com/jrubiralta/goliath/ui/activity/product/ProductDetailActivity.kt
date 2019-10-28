package com.jrubiralta.goliath.ui.activity.product

import android.os.Bundle
import android.provider.SyncStateContract
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.provider
import com.jrubiralta.domain.model.Rates
import com.jrubiralta.domain.model.Transaction
import com.jrubiralta.goliath.R
import com.jrubiralta.goliath.domain.constants.Constants
import com.jrubiralta.goliath.model.CurrencyType
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

    private val ratesList: MutableList<Rates> = mutableListOf()

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
    }

    override fun getProduct(): String = intent?.extras?.getString(KEY_SKU)
        ?: ""

    override fun updateList(list: List<Transaction>) {
        var total = 0.0
        val finalList = mutableListOf<Transaction>()
        list.map {
            var conversion = ""
            if (it.currency == "EUR") {
                total += it.amount.toDouble()
                finalList.add(it)
            } else {
                conversion = changeCurrencyToEur(it.amount, it.currency)
                finalList.add(Transaction(it.currency, "EUR", conversion))
                total += conversion.toDouble()
            }
        }
        tv_total_sum.text = total.toString()
        transactionListAdapter.replace(finalList)
    }

    override fun changeCurrencyToEur(amount: String, currency: String): String {
        val conversion = ratesList.filter { currency == it.from }.first().rate.toDouble() * amount.toDouble()
        return "%.2f".format(conversion)
    }

    override fun setRatesList(currency: String, list: List<Rates>) {
        if (list.filter { it.from == currency && it.to == "EUR" }.isNotEmpty()) {
            ratesList.add(list.filter { it.from == currency && it.to == "EUR" }.first())
        } else {
            val rate = list.filter { it.from == currency }.first()
            if (list.filter { it.from == rate.to && it.to == "EUR"}.isNotEmpty()) {
                val conversion = rate.rate.toDouble()*
                        list.filter { it.from == rate.to && it.to == "EUR"}.first().rate.toDouble()
                ratesList.add(Rates(currency, "EUR", "%.2f".format(conversion)))
            } else {
                val rateAux = list.filter { it.from == rate.to && it.to != rate.from && it.to != currency}.first()
                val conversion = rate.rate.toDouble()*
                        rateAux.rate.toDouble()*
                        list.filter { it.from == rateAux.to && it.to == "EUR" }.first().rate.toDouble()
                ratesList.add(Rates(currency, "EUR", "%.2f".format(conversion)))
            }
        }
    }
}
