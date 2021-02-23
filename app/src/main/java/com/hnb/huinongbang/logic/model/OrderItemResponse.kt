package com.hnb.huinongbang.logic.model

data class OrderItemResponse(val code: Int, val data: List<OrderItem>, val message: String)

data class OrderItem(val number: Int, val product: Product, val id: Int, val type: Int)

data class CreateOrderResponse(val code: Int, val data: Int, val message: String)

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