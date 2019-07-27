package com.example.vue_kotlin

import android.view.View

open class CHolder(viewItem: View) {

    public var v_block:VueBlock? = null
    public fun v_to(block:VueBlock){
        this.v_block = block
    }
    public fun v_to(){
        this.v_block?.invoke()
    }
    open fun setData(data: VueData){}
}