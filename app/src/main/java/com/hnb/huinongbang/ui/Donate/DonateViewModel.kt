package com.hnb.huinongbang.ui.Donate

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DonateViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "这是捐赠模块"
    }
    val text: LiveData<String> = _text
}