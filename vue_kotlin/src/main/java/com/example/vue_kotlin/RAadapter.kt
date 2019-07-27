package com.example.vue_kotlin

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlin.math.log10

typealias IndexBlock = (Int) -> Unit

public class RAdapter : RecyclerView.Adapter<RHolder>() {

    public var items = emptyList<VueData>()
    public var indexBlock:IndexBlock? = null
    private var indexVue:Vue? = null
    private var vId:String? = null

    public override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RHolder {
        var aHolder = Vue.vueComponents[viewType]
        val holderClass = aHolder?.replace("class","")?.replace(" ","")
        val view: View = LayoutInflater.from(parent?.context).inflate(viewType, parent, false)
        val holder = Class.forName(holderClass).getConstructor(View::class.java).newInstance(view) as RHolder

        return holder
    }

    override fun getItemCount(): Int = items.size

    override fun getItemViewType(position: Int): Int {
        val item = items[position]
        return item.layoutIdentity
    }

    override fun onBindViewHolder(holder: RHolder, position: Int) {
        holder.setData(items[position])
        holder.v_to {

            indexBlock?.invoke(position)
            indexVue?.v_index(vId,position)
        }


    }

    public fun v_didSelect(ob:IndexBlock){

        indexBlock = ob
    }

    public fun v_index(vId:String,vue:Vue?){

        this.vId = vId
        indexVue = vue
    }

    //v-list
    public fun v_array(vId:String,vue: Vue?){

        vue?.v_arr(vId,{ it:MutableList<VueData> ->

            this.items = it
            this.notifyDataSetChanged()
        })


    }


}


