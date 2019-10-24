package com.jrubiralta.goliath.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

abstract class BaseAdapter<T>(
        private val items: MutableList<T> = mutableListOf(),
        private val onItemClickListener: (T) -> Unit = {},
        private val onLongClickListener: (T) -> Unit = {}
) : RecyclerView.Adapter<BaseAdapter.BaseViewHolder<T>>() {

    abstract val itemLayoutId: Int

    abstract fun viewHolder(view: View): BaseAdapter.BaseViewHolder<T>

    override fun getItemCount(): Int = items.size

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): BaseViewHolder<T> {
        val view = LayoutInflater.from(parent?.context).inflate(itemLayoutId, parent, false)

        val viewHolder = viewHolder(view)
        viewHolder.onItemClickListener = { onItemClickListener(items[it]) }
        viewHolder.onLongClickListener = { onLongClickListener(items[it]) }
        return viewHolder
    }

    override fun onBindViewHolder(holder: BaseViewHolder<T>?, position: Int) {
        holder?.bind(items[position])
    }

    fun add(item: T) {
        items.add(item)
        notifyItemInserted(items.lastIndex)
    }

    fun add(item: T, position: Int) {
        items.add(position, item)
        notifyItemInserted(position)
    }

    fun remove(item: T) {
        if (items.contains(item)) {
            val index = items.indexOf(item)
            items.removeAt(index)
            notifyItemRemoved(index)
        }
    }

    fun update(item: T) {
        if (items.contains(item)) {
            val index = items.indexOf(item)
            items[index] = item
            notifyItemChanged(index)
        }
    }

    fun contains(item: T): Boolean = items.contains(item)

    fun clear() {
        items.clear()
        notifyDataSetChanged()
    }

    fun addAll(newItems: MutableList<T>) {
        items.addAll(newItems)
        notifyDataSetChanged()
    }

    fun replace(newItems: MutableList<T>) {
        clear()
        addAll(newItems)
    }

    protected fun get(position: Int): T = items[position]

    abstract class BaseViewHolder<in T>(itemView: View,
                                        var onItemClickListener: (Int) -> Unit = {},
                                        var onLongClickListener: (Int) -> Unit = {}) : RecyclerView.ViewHolder(itemView) {

        init {
            itemView.setOnClickListener { onItemClickListener(adapterPosition) }
            itemView.setOnLongClickListener {
                onLongClickListener(adapterPosition)
                true
            }
        }

        abstract fun bind(model: T)
    }
}