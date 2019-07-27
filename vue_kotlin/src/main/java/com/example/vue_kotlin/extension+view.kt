package com.example.vue_kotlin

import android.graphics.Bitmap
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.*


public interface VueData{

    val layoutIdentity:Int
    var v_identifier:Int

}


public val AppCompatActivity.params:String
    get() {return this.intent.getStringExtra("data")}

public val AppCompatActivity.className:String
    get() {return this.intent.getStringExtra("className")}

public fun AppCompatActivity.addActivity(){

    Router.instance.addActivity(this)

}
public fun AppCompatActivity.removeActivity(){

    Router.instance.removeActivity(this)

}

//{{ text }}
public fun TextView.v_text(vId:String?,vue: Vue?){

    vue?.v_text(vId,{ it:String ->

        this.text = it

    })

}

//v-if
public fun TextView.v_if(vId:String?,vue:Vue?){

    vue?.v_if(vId,{ it:Boolean ->
        if (it){
            this.visibility = View.GONE
        }else{

            this.visibility = View.VISIBLE

        }
    })

}

//v-image
public fun ImageView.v_image(vId:String?,vue: Vue?){

    vue?.v_image(vId,{ it:Bitmap ->

        this.setImageBitmap(it)
    })

}

//v-if
public fun ImageView.v_if(vId:String?,vue: Vue){

    vue?.v_if(vId,{ it:Boolean ->

        if (it){
            this.visibility = View.GONE
        }else{
            this.visibility = View.VISIBLE
        }
    })


}

//v-image
public fun ImageButton.v_image(vId:String?,vue: Vue?){

    vue?.v_image(vId,{ it:Bitmap ->

        this.setImageBitmap(it)
    })


}

//v-if
public fun ImageButton.v_if(vId:String?,vue: Vue?){

    vue?.v_if(vId,{ it:Boolean ->
        if (it){
            this.visibility = View.GONE
        }else{

            this.visibility = View.VISIBLE

        }
    })

}
//v-on
public fun ImageButton.v_on(vId:String?,vue: Vue?){

    this.setOnClickListener {

        vue?.v_on(vId)
    }

}

//v-click
public fun ImageButton.v_click(vue:VueBlock){

    this.setOnClickListener {

        vue()
    }


}


//{{ text }}
public fun Button.v_text(vId:String?,vue: Vue?){

    vue?.v_text(vId,{ it:String ->

        this.text = it
    })


}


//v-if
public fun Button.v_if(vId:String?,vue: Vue?){

    vue?.v_if(vId,{ it:Boolean ->
        if (it){
            this.visibility = View.GONE
        }else{

            this.visibility = View.VISIBLE

        }
    })

}
//v-on

public fun Button.v_on(vId:String?,vue: Vue?){

    this.setOnClickListener {

        vue?.v_on(vId)

    }

}

//v-click
public fun Button.v_click(vue:VueBlock){

    this.setOnClickListener {

        vue()
    }

}


//{{ text }}
public fun EditText.v_text(vId:String?,vue: Vue?){

    vue?.v_text(vId,{it:String ->

        this.setText(it)
    })


}

//v-if
public fun EditText.v_if(vId:String?,vue: Vue?){

    vue?.v_if(vId,{ it:Boolean ->
        if (it){
            this.visibility = View.GONE
        }else{

            this.visibility = View.VISIBLE

        }
    })

}
//v-input

public fun EditText.v_input(vId:String?,vue: Vue?){

    this.addTextChangedListener(EditWatcher(vId!!,vue!!))

}
internal class EditWatcher(var vId:String,var vue:Vue) : TextWatcher {
    override fun afterTextChanged(s: Editable) {

    }
    override fun beforeTextChanged(s:CharSequence, start:Int, count:Int, after:Int) {

    }
    override fun onTextChanged(s:CharSequence, start:Int, count:Int, after:Int) {

        vue.v_input(vId,s.toString())
    }
}

//v-change
public typealias changeBlock = (String) -> Unit
public fun EditText.v_change(vue: changeBlock){

    this.addTextChangedListener(ChangeWatcher(vue))

}
internal class ChangeWatcher(var vue:changeBlock) : TextWatcher {
    override fun afterTextChanged(s: Editable) {

    }
    override fun beforeTextChanged(s:CharSequence, start:Int, count:Int, after:Int) {

    }
    override fun onTextChanged(s:CharSequence, start:Int, count:Int, after:Int) {

        vue.invoke(s.toString())
    }
}
