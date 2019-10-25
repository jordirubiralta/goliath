package com.jrubiralta.goliath.ui.adapter

import android.view.View
import com.jrubiralta.goliath.R
import com.jrubiralta.goliath.model.TransactionListItemView
import kotlinx.android.synthetic.main.item_transaction_list.view.*

class TransactionListAdapter
    : BaseAdapter<TransactionListItemView>() {

    override val itemLayoutId: Int = R.layout.item_transaction_list

    override fun viewHolder(view: View): BaseViewHolder<TransactionListItemView> = ServiceLightItemViewHolder(view)

    class ServiceLightItemViewHolder(itemView: View)
        : BaseViewHolder<TransactionListItemView>(itemView) {

        override fun bind(model: TransactionListItemView) {
            itemView.tv_sku.text = model.sku
            itemView.tv_amount.text = model.amount.toString()
            itemView.tv_currency.text = "EUR"
        }
    }
}