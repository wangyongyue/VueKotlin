package com.example.vue_kotlin

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter

public class CAdapter (var context: Context) : BaseAdapter() {

    public var items = emptyList<VueData>()
    private var indexBlock:IndexBlock? = null
    private var indexVue:Vue? = null
    private var vId:String? = null
    override fun getItem(p0: Int): Any {
        return items.get(p0)
    }

    override fun getItemId(p0: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        return items.size
    }

    override fun getView(position: Int, convertView: View?, p2: ViewGroup?): View {
        var view: View
        var holder:CHolder
        val item = getItem(position) as VueData
        if (convertView == null) {
            view = View.inflate(context,item.layoutIdentity, null)
            var aHolder = Vue.vueComponents[item.layoutIdentity]
            val holderClass = aHolder?.replace("class","")?.replace(" ","")
            val aholder = Class.forName(holderClass).getConstructor(View::class.java).newInstance(view)
            var viewHolder = aholder as CHolder
            view.tag = viewHolder
            viewHolder.setData(item)
            holder = viewHolder

        } else {

            view = convertView
            val viewHolder = view.tag as CHolder
            viewHolder.setData(item)
            holder = viewHolder

        }
        holder.v_to {

            indexBlock?.invoke(position)
            indexVue?.v_index(vId,position)
        }


        return view
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

        vue?.v_array(vId,{ it:MutableList<VueData> ->

            this.items = it
            this.notifyDataSetChanged()

        })



    }

}