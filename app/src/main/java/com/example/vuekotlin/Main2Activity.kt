package com.example.vuekotlin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.example.vue_kotlin.*
import kotlinx.android.synthetic.main.activity_main.*

class Main2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        addActivity()

        var vue = Main2()
        Vue.register(R.layout.layout_item,RUserViewHolder::class.java.toString())
        recycler.layoutManager = LinearLayoutManager(this)
        var ad =  RAdapter()
        ad.v_array(vID,vue)
        recycler.adapter = ad
        vue.v_start()

        ad.v_didSelect {
            Toast.makeText(this,"$it", Toast.LENGTH_SHORT).show()
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        removeActivity()
    }
}
class Main2: Vue(){

    override fun v_viewController(): Class<Any>? {

        return Main2Activity::class.java as Class<Any>
    }

    override fun v_start() {
        super.v_start()

        var items = mutableListOf<VueData>()
        for (i in 1..6){
            items.add(UserData("今天还会下雨吗$i"))
        }
        this.va_array(vID,{

            return@va_array items

        })


    }
}