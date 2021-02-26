package com.hnb.huinongbang.logic.model


import java.util.*



//专家个人信息（通过点击专家按钮进入）
data class DoctorInformationResponse(val code: Int, val data: List<DoctorInformation>, val message: String)

data class DoctorInformation(
    val names:String,
    val doctorfile:List<DoctorFile>//专家详细简介
)
data class DoctorFile(
    //专家id，个人账号信息，单位，专业领域，个人介绍，创建时间
    var id:Int,
    var user_ID: User,
    var belong: String,
    var speciality: String,
    var introduce: String,
    var createtime: Date,
    var sign:Int
)


