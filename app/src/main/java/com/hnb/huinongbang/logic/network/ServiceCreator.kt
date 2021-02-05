package com.hnb.huinongbang.logic.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//Retrofit构造器
object ServiceCreator {
    //    模拟器 "http://10.0.2.2/"
    // 域名 "https://www.huinongbang.club/"
    private const val BASE_URL = "https://www.huinongbang.club/"
    private const val CDN_URL = "https://huinongbang.club/"
    val lunbo = CDN_URL+"img/lunbo/"
    val lunbo_donate = CDN_URL+"webphoto/"
    val lunbo_policy = CDN_URL+"webphoto/"
    val firstProductImage = CDN_URL + "img/productSingle_middle/"
    val productSingleImage = CDN_URL + "img/productSingle/"
    val productDetailImage = CDN_URL + "img/productDetail/"


    private val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL) //所有请求的根目录
            .addConverterFactory(GsonConverterFactory.create()) //解析数据使用的转换库 --Gson直接转成对象
            .build()  //构建出Retrofit对象

    fun <T> create(serviceClass: Class<T>): T = retrofit.create(serviceClass) //创建出相应Service接口的动态代理对象
    //示例： val appservice = ServiceCreator.create(AppService::class.java)

    inline fun <reified T> create(): T = create(T::class.java) //泛型实化
    //示例： val appservice = ServiceCreator.create<AppService>()
}