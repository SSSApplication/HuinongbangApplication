package com.hnb.huinongbang.ui.Planting

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PlantingViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "这是我的种植"
    }
    val text: LiveData<String> = _text
}