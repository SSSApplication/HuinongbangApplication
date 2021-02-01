package com.hnb.huinongbang.ui.common

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.hnb.huinongbang.logic.Repository
import com.hnb.huinongbang.logic.model.Product
import com.hnb.huinongbang.logic.model.ProductImage
import com.hnb.huinongbang.logic.model.PropertyValue
import com.hnb.huinongbang.logic.model.Review

class ProductViewModel : ViewModel() {
    var pid = 0
    lateinit var product : Product
    var productDetailImages = ArrayList<ProductImage>() //详情图片
    var bannerData: MutableList<BannerDataBean> = ArrayList() //展示轮播图
    var propertyValues = ArrayList<PropertyValue>() //产品属性链表
    var reviews = ArrayList<Review>() //评论链表

    private val pidLiveData = MutableLiveData<Int>()

    val productLiveData = Transformations.switchMap(pidLiveData){ pid ->
        Repository.product(pid)
    }
    val propertyValuesLiveData = Transformations.switchMap(pidLiveData){ pid ->
        Repository.propertyValues(pid)
    }
    val reviewsLiveData = Transformations.switchMap(pidLiveData){ pid ->
        Repository.reviews(pid)
    }
    fun getdata(pid: Int){
        pidLiveData.value = pid
    }
}