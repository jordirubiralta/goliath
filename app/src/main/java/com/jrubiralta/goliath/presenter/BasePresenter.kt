package com.jrubiralta.goliath.presenter

import android.content.Context
import com.jrubiralta.goliath.ui.view.View

interface BasePresenter<V> where V : View {
    fun init()
    fun resume()
    fun pause()
    fun stop()
    fun destroy()
    fun getContext(): Context
}
