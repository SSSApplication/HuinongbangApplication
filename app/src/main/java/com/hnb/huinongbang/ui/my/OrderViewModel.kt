 package com.hnb.huinongbang.ui.my

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.hnb.huinongbang.logic.Repository
import com.hnb.huinongbang.logic.model.ChangePasswordData
import com.hnb.huinongbang.logic.model.GetCartData
import com.hnb.huinongbang.logic.model.GetOrderData

 class OrderViewModel : ViewModel() {
    private val orderLiveData = MutableLiveData<GetOrderData>()
    val getOrderResult = Transformations.switchMap(orderLiveData){ data ->
        Repository.getOrder(data)
    }
    //外部调用函数
    fun getOrder(data: GetOrderData){
        orderLiveData.value = data
    }
}