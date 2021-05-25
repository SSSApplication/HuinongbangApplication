 package com.hnb.huinongbang.ui.my

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.hnb.huinongbang.logic.Repository
import com.hnb.huinongbang.logic.model.ChangePasswordData
import com.hnb.huinongbang.logic.model.GetCartData
import com.hnb.huinongbang.logic.model.GetOrderData
import com.hnb.huinongbang.logic.model.Order

 class OrderViewModel : ViewModel() {
     var orderList = ArrayList<Order>()
     val allOrder = 1
     val waitPay = 2
     val waitDelivery = 3
     val waitConfirm = 4
     val waitReview = 5
     var status = allOrder
    private val orderLiveData = MutableLiveData<GetOrderData>()
    val getOrderResult = Transformations.switchMap(orderLiveData){ data ->
        Repository.getOrder(data)
    }
    //外部调用函数
    fun getOrder(data: GetOrderData){
        orderLiveData.value = data
    }
}