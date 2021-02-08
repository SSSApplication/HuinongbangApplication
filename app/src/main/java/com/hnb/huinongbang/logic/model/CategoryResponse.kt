package com.hnb.huinongbang.logic.model

data class CategoryResponse(val code: Int, val data: List<Category>, val message: String)

data class Category(val id: Int, //id
                    val name: String, //分类名称
                    val type: Int //类别 分辨捐赠还是购物
)
