package com.example.vue_kotlin

import android.app.Activity
import android.content.Context
import android.content.Intent
import java.util.*

public typealias popCallBack = (obj:Any) -> Unit

public class Router {

    private val activityStack: Stack<Activity> = Stack()
    public var block:popCallBack? = null

    companion object {

        public val instance: Router by lazy { Router() }

        public fun <T> push(clazz: Class<T>){

            val topAc = instance.topActivity()
            val intent = Intent()
            intent.setClass(topAc,clazz)
            intent.putExtra("data","")
            topAc.startActivity(intent)
        }
        public fun <T> push(clazz: Class<T>,className:String){

            val topAc = instance.topActivity()
            val intent = Intent()
            intent.setClass(topAc,clazz)
            val name = className.split('$')[0]
            intent.putExtra("className",name)
            intent.putExtra("data","")
            topAc.startActivity(intent)
        }
        public fun pop(){

            val topAc = instance.topActivity()
            topAc.finish()
        }
        public fun <T> push(clazz:Class<T>,className:String,data:String){

            val topAc = instance.topActivity()
            val intent = Intent()
            intent.putExtra("data",data)
            val name = className.split('$')[0]
            intent.putExtra("className",name)
            intent.setClass(topAc,clazz)
            topAc.startActivity(intent)


        }
        public fun <T> push(clazz:Class<T>,className:String,data:String,block: popCallBack){

            val topAc = instance.topActivity()
            val intent = Intent()
            instance.block = block
            intent.putExtra("data",data)
            val name = className.split('$')[0]
            intent.putExtra("className",name)
            intent.setClass(topAc,clazz)
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