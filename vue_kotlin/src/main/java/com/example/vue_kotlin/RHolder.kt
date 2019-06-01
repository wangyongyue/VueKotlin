package com.example.vue_kotlin

import android.support.v7.widget.RecyclerView
import android.view.View

open class RHolder(viewItem: View) : RecyclerView.ViewHolder(viewItem){

    public val v_selectOb = Vue()
    open fun setData(item: VueData){}

}

