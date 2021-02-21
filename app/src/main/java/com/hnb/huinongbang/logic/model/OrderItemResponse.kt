package com.hnb.huinongbang.logic.model

data class OrderItemResponse(val code: Int, val data: List<OrderItem>, val message: String)

data class OrderItem(val number: Int, val product: Product, val id: Int, val type: Int)

data class CreateOrderResponse(val code: Int, val data: Int, val message: String)