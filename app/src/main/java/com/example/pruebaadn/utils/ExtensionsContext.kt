package com.example.pruebaadn.utils

import android.annotation.SuppressLint
import android.content.Context
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import com.example.pruebaadn.base.App
import java.text.SimpleDateFormat
import java.util.*

@SuppressLint("SimpleDateFormat")
fun Context.hideKeyboard(editText: EditText) {
    val imm: InputMethodManager? = this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
    imm?.hideSoftInputFromWindow(editText.getWindowToken(), 0)
}

fun Context.createToast(mess: String){
    Toast.makeText(this, mess, Toast.LENGTH_LONG).show()
}
