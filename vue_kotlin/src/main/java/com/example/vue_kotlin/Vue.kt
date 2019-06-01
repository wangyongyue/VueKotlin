package com.example.vue_kotlin

import android.graphics.Bitmap

public typealias VueBlock = () -> Unit
public typealias VueIndexBlock = (Int) -> Unit
public typealias VueInputBlock = (String) -> Unit

public class Vue{


    public val list = mutableListOf<VueBlock>()

    public var v_text:String? = null
    public fun v_text(v:()->String?){

        v_text = v()
        sendVueMsg()
    }

    public var v_image: Bitmap? = null
    public fun v_image(v:()-> Bitmap?){

        v_image = v()
        sendVueMsg()
    }

    public var v_blind:Map<String ,String>? = null
    public fun v_blind(v:()->Map<String ,String>?){

        v_blind = v()
        sendVueMsg()
    }

    public var v_if:Int?= null
    public fun v_if(v:()->Int?){

        v_if = v()
        sendVueMsg()
    }
    public var v_input:VueInputBlock?= null
    public fun v_input(v:VueInputBlock){

        v_input = v
    }

    public var v_on:VueBlock? = null
    public fun v_on(v:VueBlock){

        v_on = v

    }

    public var v_index:VueIndexBlock? = null
    public fun v_index(v:VueIndexBlock){

        v_index = v

    }

    public var v_list:MutableList<VueData>? = null
    public fun v_list(isPage:Boolean,v:() -> MutableList<VueData>){
        if (isPage){

            if (v_list == null){

                v_list = v()

            }else{

                v_list?.addAll(v())
            }

        }else{

            v_list = v()

        }
        sendVueMsg()
    }

    public fun setupVue(callBack:VueBlock){

        list.add(callBack)

    }
    public fun sendVueMsg(){

        for (value in list){

            value.invoke()
        }
    }



}