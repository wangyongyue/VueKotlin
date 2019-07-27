package com.example.vue_kotlin

import android.graphics.Bitmap

public typealias VueBlock = () -> Unit


public typealias VueTextBlock = (String) -> Unit
public typealias VueImgBlock = (Bitmap) -> Unit
public typealias VueIfBlock = (Boolean) -> Unit

public typealias VueOnBlock = () -> Unit
public typealias VueInputBlock = (String) -> Unit
public typealias VueIndexBlock = (Int) -> Unit
public typealias VueArrayBlock = (MutableList<VueData>) -> Unit


open class Vue{


    companion object{
        public var vueComponents = mutableMapOf<Int,String>()
        public fun register(aXML:Int,toHolder:String){

            vueComponents.set(aXML,toHolder)

        }

    }

    //text
    public var blockTextDic = mutableMapOf<String,VueTextBlock>()
    public fun v_text(vId:String?,v:()->String?){

        blockTextDic[vId]?.invoke(v()!!)

    }
    public fun v_text(vId:String?,v:VueTextBlock?){

        blockTextDic.set(vId!!,v!!)

    }
    //image
    public var blockImgDic = mutableMapOf<String,VueImgBlock>()
    public fun v_image(vId:String?,v:() -> Bitmap?){

        blockImgDic[vId]?.invoke(v()!!)

    }
    public fun v_image(vId:String?,v:VueImgBlock?){

        blockImgDic.set(vId!!,v!!)

    }

    //if
    public var blockIfDic = mutableMapOf<String,VueIfBlock>()
    public fun v_if(vId:String?,v:() -> Boolean?){

        blockIfDic[vId]?.invoke(v()!!)
    }
    public fun v_if(vId:String?,v:VueIfBlock?){

        blockIfDic.set(vId!!,v!!)

    }

    //on
    public var blockOnDic = mutableMapOf<String,VueOnBlock>()
    public fun v_on(vId:String?,v:VueOnBlock?){

        blockOnDic.set(vId!!,v!!)

    }
    public fun v_on(vId:String?){

        blockOnDic[vId]?.invoke()

    }

    //input
    public var blockInputDic = mutableMapOf<String,VueInputBlock>()
    public fun v_input(vId:String?,v:VueInputBlock?){

        blockInputDic.set(vId!!,v!!)

    }
    public fun v_input(vId:String?,text:String){

        blockInputDic[vId]?.invoke(text)

    }

    //index
    public var blockIndexDic = mutableMapOf<String,VueIndexBlock>()
    public fun v_index(vId:String?,v:VueIndexBlock?){

        blockIndexDic.set(vId!!,v!!)

    }
    public fun v_index(vId:String?,index:Int){

        blockIndexDic[vId]?.invoke(index)

    }


    //array
    public var blockArrayDic = mutableMapOf<String,VueArrayBlock>()
    public fun v_array(vId:String?,v:() -> MutableList<VueData>?){

        blockArrayDic[vId]?.invoke(v()!!)

    }
    public fun v_arr(vId:String?,v:VueArrayBlock?){

        blockArrayDic.set(vId!!,v!!)

    }

    //
    open fun v_start(){}
    open fun v_viewController():Class<Any>?{

        return null
    }



}