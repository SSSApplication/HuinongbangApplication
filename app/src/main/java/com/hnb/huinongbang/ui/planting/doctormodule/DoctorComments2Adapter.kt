package com.hnb.huinongbang.ui.planting.doctormodule

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hnb.huinongbang.R
import com.hnb.huinongbang.logic.model.Comment
import com.hnb.huinongbang.logic.model.CommentList
import com.hnb.huinongbang.logic.model.DoctorFile
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.activity_doctor_comments.*

//根评论的数量
class DoctorComments2Adapter (val activity: Activity, val commentNodeList: List<Comment>) : RecyclerView.Adapter<DoctorComments2Adapter.ViewHolder>() {
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var commentUserName2:TextView=itemView.findViewById(R.id.commentUserName2)
        var userComment2:TextView=itemView.findViewById(R.id.userComment2)
        var userCommentTime2:TextView=itemView.findViewById(R.id.userCommentTime2)
         }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.doctorcomments_recycle_item2, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val commentNode = commentNodeList[position]
        holder.commentUserName2.text =commentNode.dUser_ID.user_name
        holder.userComment2.text =commentNode.discuss_Values
        holder.userCommentTime2.text=commentNode.discuss_CreateTime.toString()


    }

    override fun getItemCount() = commentNodeList.size
}

