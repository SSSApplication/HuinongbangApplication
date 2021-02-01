package com.hnb.huinongbang.logic.model

import java.util.*

data class ReviewResponse(val code: Int, val data: List<Review>, val message: String)

data class Review(val id: Int, //id
                  val content: String, //内容
                  val date: Date, //日期
                  val user: User //用户
)