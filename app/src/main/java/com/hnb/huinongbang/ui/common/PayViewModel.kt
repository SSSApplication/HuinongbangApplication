package com.hnb.huinongbang.ui.common

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.hnb.huinongbang.logic.Repository
import com.hnb.huinongbang.logic.model.PayForDonationData
import com.hnb.huinongbang.logic.model.PayForShopping

class PayViewModel : ViewModel() {


    private val deductionLiveData = MutableLiveData<PayForDonationData>()
    private val shoppingLiveData = MutableLiveData<PayForShopping>()

    val deductionResult = Transformations.switchMap(deductionLiveData){ data ->
        Repository.payForDonation(data)
    }
    val shoppingResult = Transformations.switchMap(shoppingLiveData){data ->
        Repository.payForShopping(data)
    }
    //扣除慧农币
    fun deduction(data: PayForDonationData) {
        deductionLiveData.value = data
    }
    //购物付款
    fun shopping(data: PayForShopping) {
        shoppingLiveData.value = data
    }
}