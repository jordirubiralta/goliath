package com.jrubiralta.goliath.ui.adapter

import com.jrubiralta.domain.model.Transaction
import com.jrubiralta.goliath.R
import android.view.View
import kotlinx.android.synthetic.main.item_product.view.*

class ProductsListAdapter(onItemClick: (String) -> Unit)
    : BaseAdapter<String>(onItemClickListener = onItemClick) {

    override val itemLayoutId: Int = R.layout.item_product

    override fun viewHolder(view: View): BaseViewHolder<String> = ServiceLightItemViewHolder(view)

    class ServiceLightItemViewHolder(itemView: View)
        : BaseViewHolder<String>(itemView) {

        override fun bind(model: String) {
            itemView.tv_product_id.text = model
        }
    }
}