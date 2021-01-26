package com.hnb.huinongbang.ui.policy

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PolicyViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "这是我的政策"
    }
    val text: LiveData<String> = _text
}