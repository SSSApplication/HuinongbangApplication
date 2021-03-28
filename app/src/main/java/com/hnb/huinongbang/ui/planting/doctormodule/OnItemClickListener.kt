package com.hnb.huinongbang.ui.planting.doctormodule

import com.hnb.huinongbang.logic.model.Comment
import com.hnb.huinongbang.logic.model.CommentList

interface CommentsOnItemClickListener1{
    fun onItemClick1(commentRoot: CommentList, position: Int)
}
interface CommentsOnItemClickListener2{
    fun onItemClick2(comment: Comment, position: Int)
}