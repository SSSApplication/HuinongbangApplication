 package com.hnb.huinongbang.ui.my

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.hnb.huinongbang.logic.Repository
import com.hnb.huinongbang.logic.model.*

 class OrderViewModel : ViewModel() {
     var orderList = ArrayList<Order>()
     val allOrder = 1
     val waitPay = 2
     val waitDelivery = 3
     val waitConfirm = 4
     val waitReview = 5
     var status = allOrder
    private val orderLiveData = MutableLiveData<GetOrderData>()
     private val confirmLiveData = MutableLiveData<String>()
     private val addReviewLiveData = MutableLiveData<AddReviewData>()

    val getOrderResult = Transformations.switchMap(orderLiveData){ data ->
        Repository.getOrder(data)
    }
     val confirmResult = Transformations.switchMap(confirmLiveData){oid ->
         Repository.morderConfirmed(oid)
     }
     val addReviewResult = Transformations.switchMap(addReviewLiveData){data ->
         Repository.addReview(data)
     }
    //外部调用函数
    fun getOrder(data: GetOrderData){
        orderLiveData.value = data
    }

     //确认收货
     fun confirm(oid: String){
         confirmLiveData.value = oid
     }
     //评价
     fun addReview(data: AddReviewData){
         addReviewLiveData.value = data
     }
}