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
import com.jrubiralta.domain.interactor.transactions.GetTransactionsUseCase
import com.jrubiralta.domain.model.TransactionList
import com.jrubiralta.goliath.R
import com.jrubiralta.goliath.model.CurrencyType
import com.jrubiralta.goliath.model.TransactionListItemView
import com.jrubiralta.goliath.presenter.home.HomePresenter
import com.jrubiralta.goliath.presenter.home.HomePresenterImpl
import com.jrubiralta.goliath.ui.activity.BaseActivity
import com.jrubiralta.goliath.ui.adapter.DefaultSpinnerAdapter
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
                view = this@HomeActivity,
                getTransactionsUseCase = instance()
            )
        }
    }

    private val currencyList = mutableListOf(CurrencyType.EUR.toString(), CurrencyType.AUD.toString(), CurrencyType.CAD.toString(), CurrencyType.USD.toString())

    private val transactionListAdapter = TransactionListAdapter()

    private lateinit var currencySpinnerAdapter: DefaultSpinnerAdapter

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

        currencySpinnerAdapter = DefaultSpinnerAdapter(applicationContext, currencyList)
        spinner.adapter = currencySpinnerAdapter
    }

    private fun initListeners() {
        spinner.onItemSelectedListener = itemTransactionSelected
    }

    private fun initData() {
        presenter.getTransactions()
    }

    private val itemTransactionSelected = object : AdapterView.OnItemSelectedListener {
        override fun onItemSelected(parent: AdapterView<*>?, view: View?, pos: Int, id: Long) {
            val text = parent?.getItemAtPosition(pos).toString()
            Toast.makeText(parent?.context, text,Toast.LENGTH_LONG)
        }
        override fun onNothingSelected(arg0: AdapterView<*>) {}

    }

    override fun updateList(transactionList: TransactionList) {
//        transactionListAdapter.replace(transactionList.list)
    }

}

interface HomeListener {
}