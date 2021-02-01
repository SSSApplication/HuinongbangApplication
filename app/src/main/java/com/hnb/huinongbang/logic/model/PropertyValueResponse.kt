package com.hnb.huinongbang.logic.model

data class PropertyValueResponse(val code: Int, val data: List<PropertyValue>, val message: String)

data class PropertyValue(val id: Int, //id
                         val property: Property, //属性
                         val value: String  //属性值
)

data class Property(val id: Int, //id
                    val name: String //属性名
)