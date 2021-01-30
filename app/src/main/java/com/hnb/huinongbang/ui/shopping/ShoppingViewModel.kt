package com.hnb.huinongbang.ui.shopping

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.hnb.huinongbang.logic.Repository
import com.hnb.huinongbang.logic.model.Category
import com.hnb.huinongbang.logic.model.Product

class ShoppingViewModel : ViewModel() {
    var categoryList = ArrayList<Category>()
    var productList = ArrayList<Product>()

    private val typeLiveData = MutableLiveData<Int>()
    val categoriesLiveData = Transformations.switchMap(typeLiveData){type ->
        Repository.categories(type)
    }
    val productsLiveData = Transformations.switchMap(typeLiveData){type ->
        Repository.products(type)
    }
    fun getdata(type: Int){
        typeLiveData.value = type
    }
}