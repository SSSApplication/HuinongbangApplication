package com.hnb.huinongbang.logic.model

import java.util.*

data class ReviewResponse(val code: Int, val data: List<Review>, val message: String)

data class Review(val id: Int, //id
                  val content: String, //内容
                  val createDate: Date, //日期
                  val users: User //用户
)

data class AddReviewData(
    val oid: String,
    val pid: String,
    val uid: String,
    val content: String
)