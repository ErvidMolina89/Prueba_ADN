package com.example.pruebaadn.utils

import android.annotation.SuppressLint
import android.content.Context
import android.content.ContextWrapper
import android.view.Gravity
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.pruebaadn.base.App
import java.text.SimpleDateFormat
import java.util.*

@SuppressLint("SimpleDateFormat")
fun Context.hideKeyboard(editText: EditText) {
    val imm: InputMethodManager? = this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
    imm?.hideSoftInputFromWindow(editText.getWindowToken(), 0)
}

fun Context.createToast(mess: String){
    val toast = Toast.makeText(this, mess, Toast.LENGTH_LONG)
    toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0)
    toast.show()
}

fun Context.showDialoAddVehicle(){
    if(!isAValidContextToDisplayMessage(this)){ return }
    if(this !is AppCompatActivity){
        getContextValid(this).showDialoAddVehicle()
        return
    }
    runOnUiThread {
        DialogAddVehicle.getInstance().showDialogue(supportFragmentManager, TagsDialogue.DialogAddVehicle.getTags())
    }
}

private fun isAValidContextToDisplayMessage(contex: Context) : Boolean{
    return contex is AppCompatActivity || contex is ContextWrapper
}

private fun getContextValid(contex : Context) : Context{
    var EndContex : Context = contex
    while((EndContex !is AppCompatActivity) && (EndContex is ContextWrapper)){
        EndContex = (EndContex as ContextWrapper).baseContext
    }
    return EndContex
}