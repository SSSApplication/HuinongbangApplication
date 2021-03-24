package com.hnb.huinongbang.ui.my

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.hnb.huinongbang.logic.Repository
import com.hnb.huinongbang.logic.model.ChangePasswordData
import com.hnb.huinongbang.logic.model.GetCartData

class CartViewModel : ViewModel() {
    private val cartLiveData = MutableLiveData<GetCartData>()
    val getCartResult = Transformations.switchMap(cartLiveData){ data ->
        Repository.getCart(data)
    }
    //外部调用函数
    fun getCart(data: GetCartData){
        cartLiveData.value = data
    }
}