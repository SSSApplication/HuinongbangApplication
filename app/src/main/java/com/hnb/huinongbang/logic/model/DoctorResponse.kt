package com.hnb.huinongbang.logic.model


import java.util.*


//专家个人信息（通过点击专家按钮进入）
data class DoctorInformationResponse(
    val code: Int,
    val data: List<DoctorInformation>,
    val message: String
)

data class DoctorInformation(
    val names: String,
    val doctorfile: List<DoctorFile>//专家详细简介
)

data class DoctorFile(
    //专家id，个人账号信息，单位，专业领域，个人介绍，创建时间
    var id: Int,
    var user_ID: User,
    var belong: String,
    var speciality: String,
    var introduce: String,
    var createtime: Date,
    var sign: Int
)


//根据id获得该专家下的问答评论
data class DoctorCommentsResponse(
    val code: Int,
    val data: DoctorComments,
    val message: String
)

data class DoctorComments(//后端的DiscussData
    //专家个人信息和评论链表
    val doctor: DoctorFile,
    val user:User,
    val disess: List<CommentList>

)

data class CommentList(
    //评论链表
    val root: Comment,
    val nodes: List<Comment>
)
data class Comment(//带有指针的评论类
    var discuss_ID:Int,
    var dUserup_ID: User,
    var dUser_ID: User,
    var discuss_Values: String,
    var discuss_CreateTime: Date,
    var discuss_root: Comment//子评论的父评论
)


//评论回复功能
data class DoctorCommentBack(
    val uid: Int,  //用户id
    val DUserup_ID: Int, //专家id
    val Discuss_root: Int,//子评论的父评论的评论id
    val Discuss_Values:String  //评论内容
)
data class DoctorCommentBackResponse(
    val code: Int,
    val data: String,
    val message: String
)




