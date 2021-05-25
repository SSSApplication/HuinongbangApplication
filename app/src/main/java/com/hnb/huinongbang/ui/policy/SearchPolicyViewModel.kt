package com.hnb.huinongbang.ui.policy

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.hnb.huinongbang.logic.Repository
import com.hnb.huinongbang.logic.model.Policy

class SearchPolicyViewModel : ViewModel() {
    var policyList = ArrayList<Policy>()
    private val searchLiveData = MutableLiveData<String>()
    val searchResult = Transformations.switchMap(searchLiveData){ keyword ->
        Repository.policySearch(keyword)
    }
    //外部调用函数
    fun search(keyword: String){
        searchLiveData.value = keyword
    }
}