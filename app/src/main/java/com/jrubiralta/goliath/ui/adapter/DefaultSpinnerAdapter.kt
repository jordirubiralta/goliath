package com.jrubiralta.goliath.ui.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import com.jrubiralta.goliath.R
import kotlinx.android.synthetic.main.item_default_list.view.*

class DefaultSpinnerAdapter(context: Context,
                            list: MutableList<String> = mutableListOf())
    : BaseSpinnerAdapter<String>(context, list) {

    override val layoutResourceId = R.layout.item_default_list

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        val view = inflater.inflate(layoutResourceId, null)
        val model = getItem(position)

        view.tv_title.text = model

        return view
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup?): View {

        val view = inflater.inflate(layoutResourceId, null)
        val model = getItem(position)

        view.tv_title.text = model
        return view
    }

}