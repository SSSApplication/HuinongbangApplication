package com.hnb.huinongbang.logic.model

import java.io.Serializable
import java.text.SimpleDateFormat
import java.util.*

//用户信息类
//code(返回状态) user（用户）
data class UserResponse(val code: Int, val data: User, val message: String)

data class User (val user_ID: Int,  //id
                val user_phone: String, //电话号码
                val user_password: String, //密码
                val user_name: String, //用户名称
                val user_nickname: String, //用户昵称
                val user_sex: String, //用户性别
                val user_birthday: Date, //用户生日
                val user_address: String, //用户住址
                val user_introduce: String, //个人介绍
                val user_identitysign: Int, //是否实名认证
                val user_poorsign: Int, //是否贫困认证
                val user_doctorsign: Int, //是否专家认证
                val user_ptopsign: Int, //是否发布点对点资助
                val user_admin: Int,  //是否管理-员
                val integral: Float, //积分
                val money: Float, //钱
                val user_identityNumber: String, //身份证号
                val user_identityType: String, //用户身份证类型
                val identityReason: String, //身份证审核备注
                val poorReason: String, //贫困认证审核备注
                val doctorReason: String, //专家审核备注
                val ptopReason: String //点对点审核备注
){
    fun getUser_birthday_year(): String? {
        val birthday: Date = user_birthday
        val sdf = SimpleDateFormat("yyyy")
        return sdf.format(birthday)
    }

    fun getUser_birthday_month(): String? {
        val birthday: Date = user_birthday
        val sdf = SimpleDateFormat("MM")
        return sdf.format(birthday)
    }

    fun getUser_birthday_day(): String? {
        val birthday: Date = user_birthday
        val sdf = SimpleDateFormat("dd")
        return sdf.format(birthday)
    }
    fun getAnonymousName(): String? { //获取匿名
        if (null == user_nickname) return "  "
        if (user_nickname.length <= 1) return "*"
        if (user_nickname.length == 2) return user_nickname.substring(0, 1) + "*"
        val cs = user_nickname.toCharArray()
        for (i in 1 until cs.size - 1) {
            cs[i] = '*'
        }
        return String(cs)
    }
}

//登陆时传输到服务器的数据
data class LoginData(val name: String, val password: String)

//注册时传输到服务器的数据
data class RegisterData(val phone: String, val password: String)

//更新我的信息时传输到服务器的数据
data class UpdateMyInformationData(
    val user_name: String,
    val user_nickname: String,
    val user_sex: String,
    val user_birthday: String,
    val user_address: String,
    val user_introduce: String,
    val phone: String,
    val password: String
)

//更改密码时传输到服务器的数据
data class ChangePasswordData(val phone: String, val oldPassword: String, val newPassword: String)

//实名认证时传输到服务器的数据
data class IdentityData(
    val phone: String,
    val password: String,
    val name: String,
    val type: String,
    val number: String
)

//获取购物车信息
data class GetCartData(
    val uid: String,
    val type: String
)

//获取订单信息
data class GetOrderData(
    val uid: String,
    val type: String
)