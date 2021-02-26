package com.hnb.huinongbang.logic.model


import java.util.*

data class OrderItemResponse(val code: Int, val data: List<OrderItem>, val message: String)

data class OrderItem(val number: Int, val product: Product, val id: Int, val type: Int)

data class Order(
    val orderCode: String,
    val address: String,
    val post: String,
    val receiver: String,
    val mobile: String,
    val userMessage: String,
    val createDate: Date,
    val payDate: Date,
    val deliveryDate: Date,
    val postCode: String,
    val postnu: String,
    val confirmDate: Date,
    val user: User,
    val id: Int,
    val orderItems: List<OrderItem>,
    val total: Float,
    val totalNumber: Int,
    val status: String,
    val type: Int,
    val sid: Int
)

data class CreateOrderResponse(
    val code: Int,
    val data: Order,
    val message: String
)

data class GetOrderItemData(
    val pid: String,
    val type: String,
    val uid: String,
    val num: String
)

data class CreateOrderData(
    val uid: String,
    val oiid: Array<String>,
    val address: String,
    val post: String,
    val receiver: String,
    val mobile: String,
    val userMessage: String,
    val type: String
)

data class PayForDonationData(
    val oid: String,
    val uid: String,
    val total: String
)