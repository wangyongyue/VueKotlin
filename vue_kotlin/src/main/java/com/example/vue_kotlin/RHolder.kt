package com.example.vue_kotlin

import android.support.v7.widget.RecyclerView
import android.view.View

open class RHolder(viewItem: View) : RecyclerView.ViewHolder(viewItem){

    public var v_block:VueBlock? = null
    public fun v_to(block:VueBlock){
        this.v_block = block
    }
    public fun v_to(){
        this.v_block?.invoke()
    }
    open fun setData(item: VueData){}

}

