package com.hnb.huinongbang.util

import android.widget.Toast
import com.hnb.huinongbang.HNBApplication

object ToastUtil {
    fun show(message: String){
        Toast.makeText(HNBApplication.context, message, Toast.LENGTH_SHORT).show()
    }
    fun showLong(message: String){
        Toast.makeText(HNBApplication.context, message, Toast.LENGTH_LONG).show()
    }
}