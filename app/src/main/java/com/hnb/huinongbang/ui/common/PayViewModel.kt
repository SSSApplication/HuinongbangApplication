package com.hnb.huinongbang.ui.common

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.hnb.huinongbang.logic.Repository
import com.hnb.huinongbang.logic.model.PayForDonationData

class PayViewModel : ViewModel() {
    //扣除慧农币

    private val deductionLiveData = MutableLiveData<PayForDonationData>()
    val deductionResult = Transformations.switchMap(deductionLiveData){ data ->
        Repository.payForDonation(data)
    }
    fun deduction(data: PayForDonationData) {
        deductionLiveData.value = data
    }
}