package com.example.domain.util

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

@SuppressLint("SimpleDateFormat")
fun String.convertToFormatDate() : Date {
    val convert = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
    return convert.parse(this)
}

