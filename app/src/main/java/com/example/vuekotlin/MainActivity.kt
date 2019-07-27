package com.example.vuekotlin

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.TouchDelegate
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.vue_kotlin.*
import kotlinx.android.synthetic.main.activity_main.*


val vID = "arrayId"

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        addActivity()


        Vue.register(R.layout.layout_item,RUserViewHolder::class.java.toString())



        var vue = Main()
        recycler.layoutManager = LinearLayoutManager(this)
        var ad =  RAdapter()
        ad.v_array(vID,vue)
        recycler.adapter = ad
        vue.v_start()

        ad.v_didSelect {
            Toast.makeText(this,"$it",Toast.LENGTH_SHORT).show()

            Router.push(Main2())
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        removeActivity()
    }
}
class Main:Vue(){


    override fun v_start() {
        super.v_start()

        var items = mutableListOf<VueData>()
        for (i in 1..12){
            items.add(UserData("今天下雨了$i"))
        }

        this.v_array(vID,{

            return@v_array items
        })



    }
}


class UserData(var name:String): VueData {

    override val layoutIdentity: Int
        get() = R.layout.layout_item

    override var v_identifier: Int = 0



}
class RUserViewHolder(viewItem: View) : RHolder(viewItem){

    val textView: TextView = viewItem.findViewById(R.id.text)
    val editView: EditText = viewItem.findViewById(R.id.edit)

    override fun setData(item: VueData){

        if (item is UserData){

            var model = item as? UserData
            textView.setText(model?.name)
            editView.v_change {
                textView.text = it
            }

            textView.setOnClickListener {

                model?.v_identifier = 1

                v_to()

            }

        }

    }

}
