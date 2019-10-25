package com.jrubiralta.goliath.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.widget.BaseAdapter

abstract class BaseSpinnerAdapter<T>(
        context: Context,
        private var items: MutableList<T> = mutableListOf())
    : BaseAdapter() {

    abstract val layoutResourceId: Int

    protected var inflater = LayoutInflater.from(context)!!

    override fun getCount(): Int {
        return items.size
    }

    override fun getItem(i: Int): T {
        return items[i]
    }

    override fun getItemId(i: Int): Long {
        return 0
    }

}