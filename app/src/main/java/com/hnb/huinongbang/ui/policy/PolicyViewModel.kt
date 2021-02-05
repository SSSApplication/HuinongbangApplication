package com.hnb.huinongbang.ui.policy

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.hnb.huinongbang.logic.Repository
import com.hnb.huinongbang.logic.model.Classify
import com.hnb.huinongbang.logic.model.Policy

class PolicyViewModel : ViewModel() {
    var classifyList = ArrayList<Classify>()
    var policyList = ArrayList<Policy>()

    private val typeLiveData = MutableLiveData<Int>()
    val classifiesLiveData = Transformations.switchMap(typeLiveData){type ->
        Repository.policyClassify(type)
    }
    val policiesLiveData = Transformations.switchMap(typeLiveData){type ->
        Repository.policy(type)
    }

    fun getData(type: Int){
        typeLiveData.value = type
    }
}