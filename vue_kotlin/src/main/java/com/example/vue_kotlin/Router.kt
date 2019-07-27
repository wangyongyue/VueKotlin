package com.example.vue_kotlin

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import java.util.*

public typealias popCallBack = (obj:Any) -> Unit

public class Router {

    private val activityStack: Stack<Activity> = Stack()
    public var block:popCallBack? = null

    companion object {

        public val instance: Router by lazy { Router() }


        public fun push(m: Vue){

            val topAc = instance.topActivity()
            val className = m::javaClass.toString()
            var mClass = m.v_viewController()
            val intent = Intent()
            intent.setClass(topAc,mClass)
            val name = className.split('$')[0]
            intent.putExtra("className",className)
            intent.putExtra("data","")
            topAc.startActivity(intent)
        }

        public fun pop(){

            val topAc = instance.topActivity()
            topAc.finish()
        }
        public fun push(m:Vue,data:String){

            val topAc = instance.topActivity()
            val className = m::javaClass.toString()
            var mClass = m.v_viewController()
            val intent = Intent()
            intent.setClass(topAc,mClass)
            val name = className.split('$')[0]
            intent.putExtra("className",className)
            intent.putExtra("data",data)
            topAc.startActivity(intent)

        }
        public fun push(m:Vue,data:String,block: popCallBack){

            val topAc = instance.topActivity()
            val className = m::javaClass.toString()
            var mClass = m.v_viewController()
            val intent = Intent()
            instance.block = block
            intent.setClass(topAc,mClass)
            val name = className.split('$')[0]
            intent.putExtra("className",className)
            intent.putExtra("data",data)
            topAc.startActivity(intent)


        }
        public fun pop(data:Any){

            val topAc = instance.topActivity()
            instance.block?.invoke(data)
            topAc.finish()
            instance.block = null

        }



    }


    public fun addActivity(activity: Activity) {
        activityStack.add(activity)
    }
    public fun removeActivity(activity: Activity) {
        if (activityStack.contains(activity)) {
            activity.finish()
            activityStack.remove(activity)
        }
    }
    public fun topActivity(): Activity {
        return activityStack.lastElement()
    }
    public fun clearActivity() {
        for (activity in activityStack) {
            activity.finish()
        }
        activityStack.clear()
    }
    public fun exitApp(context: Context) {
        clearActivity()
//        val activityManager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
//        activityManager.killBackgroundProcesses(context.packageName)
        System.exit(0)
    }






}