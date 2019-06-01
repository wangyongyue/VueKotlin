package com.example.vue_kotlin

import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
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
public fun TextView.v_text(vue: Vue){

    vue.setupVue {

        this.text = vue.v_text
    }
}

//v-if
public fun TextView.v_if(vue:Vue){

    vue.setupVue {

        this.visibility = vue.v_if!!
    }

}

//v-image
public fun ImageView.v_image(vue: Vue){

    vue.setupVue {

        this.setImageBitmap(vue.v_image)
    }
}

//v-if
public fun ImageView.v_if(vue: Vue){

    vue.setupVue {

        this.visibility = vue.v_if!!
    }

}

//v-image
public fun ImageButton.v_image(vue: Vue){

    vue.setupVue {

        this.setImageBitmap(vue.v_image)
    }

}

//v-if
public fun ImageButton.v_if(vue: Vue){

    vue.setupVue {

        this.visibility = vue.v_if!!
    }

}
//v-on
public fun ImageButton.v_on(vue: Vue){

    this.setOnClickListener {

        vue.v_on?.invoke()

    }

}

//v-click
public fun ImageButton.v_click(vue:VueBlock){

    this.setOnClickListener {

        vue()
    }

}


//{{ text }}
public fun Button.v_text(vue:Vue){

    vue.setupVue {
        this.text = vue.v_text
    }

}


//v-if
public fun Button.v_if(vue:Vue){

    vue.setupVue {

        this.visibility = vue.v_if!!
    }

}
//v-on

public fun Button.v_on(vue:Vue){

    this.setOnClickListener {

        vue.v_on?.invoke()

    }

}

//v-click
public fun Button.v_click(vue:VueBlock){

    this.setOnClickListener {

        vue()
    }

}


//{{ text }}
public fun EditText.v_text(vue: Vue){

    vue.setupVue {

        this.text = text
    }

}

//v-if
public fun EditText.v_if(vue: Vue){

    vue.setupVue {

        this.visibility = vue.v_if!!
    }

}
//v-input

public fun EditText.v_input(vue: Vue){

    this.addTextChangedListener(EditWatcher(vue))

}
internal class EditWatcher(var vue:Vue) : TextWatcher {
    override fun afterTextChanged(s: Editable) {

    }
    override fun beforeTextChanged(s:CharSequence, start:Int, count:Int, after:Int) {

    }
    override fun onTextChanged(s:CharSequence, start:Int, count:Int, after:Int) {

        vue.v_input?.invoke(s.toString())
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
