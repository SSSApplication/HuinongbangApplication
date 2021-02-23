package com.hnb.huinongbang.ui.common

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.hnb.huinongbang.logic.Repository
import com.hnb.huinongbang.logic.model.CreateOrderData
import com.hnb.huinongbang.logic.model.GetOrderItemData
import com.hnb.huinongbang.util.LogUtil

class CreateOrderViewModel : ViewModel() {
    //创建订单前获取必要信息
    private val buyLiveData = MutableLiveData<GetOrderItemData>()
    val buyResult = Transformations.switchMap(buyLiveData){ data ->
        LogUtil.d("测试","开始传输1")
        Repository.beforeCreateOrder(data)
    }
    fun buy(data: GetOrderItemData) {
        buyLiveData.value = data
    }

    //创建订单
    private val payLiveData = MutableLiveData<CreateOrderData>()
    val payResult = Transformations.switchMap(payLiveData){ data ->
        Repository.createOrder(data)
    }
    fun pay(data: CreateOrderData) {
        payLiveData.value = data
    }
}