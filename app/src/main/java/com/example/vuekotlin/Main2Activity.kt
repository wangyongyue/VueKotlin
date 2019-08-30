package com.example.vuekotlin

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.example.vue_kotlin.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_item.*

val testId = "testId"

class Main2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        addActivity()

        var v = vue
        recycler.layoutManager = LinearLayoutManager(this)
        var ad =  RAdapter()
        ad.v_array(vID,v)
        recycler.adapter = ad
        v.v_start()


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
        this.v_array(vID,{

            return@v_array items
        })


    }
}