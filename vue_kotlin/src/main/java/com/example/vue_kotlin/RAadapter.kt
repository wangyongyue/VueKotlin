package com.example.vue_kotlin

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

typealias IndexBlock = (Int) -> Unit

public class RAdapter(var mapHolder : Map<Int,String>) : RecyclerView.Adapter<RHolder>() {

    public var items = emptyList<VueData>()
    public var indexBlock:IndexBlock? = null
    private var indexVue:Vue? = null
    public override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RHolder {

        val holderClass = mapHolder[viewType]?.replace("class","")?.replace(" ","")
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
        holder.v_selectOb.v_on {

            indexBlock?.invoke(position)
            indexVue?.v_index?.invoke(position)
        }

    }

    public fun v_didSelect(ob:IndexBlock){

        indexBlock = ob
    }

    public fun v_index(vue:Vue){

        indexVue = vue
    }

    //v-list
    public fun v_list(vue: Vue){

        vue.setupVue {

            this.items = vue.v_list!!
            this.notifyDataSetChanged()
        }

    }


}


