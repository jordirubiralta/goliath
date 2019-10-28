package com.jrubiralta.goliath.ui.adapter

import android.view.View
import com.jrubiralta.domain.model.Transaction
import com.jrubiralta.goliath.R
import kotlinx.android.synthetic.main.item_transaction_list.view.*

class TransactionListAdapter
    : BaseAdapter<Transaction>() {

    override val itemLayoutId: Int = R.layout.item_transaction_list

    override fun viewHolder(view: View): BaseViewHolder<Transaction> = ServiceLightItemViewHolder(view)

    class ServiceLightItemViewHolder(itemView: View)
        : BaseViewHolder<Transaction>(itemView) {

        override fun bind(model: Transaction) {
            itemView.tv_sku.text = model.sku
            itemView.tv_amount.text = model.amount.toString()
            itemView.tv_currency.text = model.currency
        }
    }
}