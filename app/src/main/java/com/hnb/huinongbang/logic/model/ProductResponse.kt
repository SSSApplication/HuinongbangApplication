package com.hnb.huinongbang.logic.model

import java.io.Serializable
import java.util.*

data class ProductsResponse(val code: Int, val data: List<Product>, val message: String)

data class ProductResponse(val code: Int, val data: Product, val message: String)

data class Product( val id: Int,
                    val name: String,//标题
                    val subTitle: String, //副标题
                    val orignalPrice: Float, //原始价格
                    val promotePrice: Float, //现在价格
                    val stock: Int, //库存
                    val createDate: Date, //创建日期
                    val category: Category, //分类
                    val firstProductImage: ProductImage, //展示图片第一张
                    val productSingleImages: List<ProductImage>,  //展示图片
                    val productDetailImages: List<ProductImage>, //详情图片
                    val reviewCount: Int, //评价数量
                    val saleCount: Int, //销售数量
                    val sid: Int, //卖家id
                    val type: Int //类别
)
data class ProductImage(val id: Int, //id
                        val product: Product, //产品
                        val type: String //类别，展示图片和详情图片
)