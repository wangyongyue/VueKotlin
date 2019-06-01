package com.example.vue_kotlin

import android.view.View

open class CHolder(viewItem: View) {

    public val v_selectOb = Vue()
    open fun setData(data: VueData){}
}