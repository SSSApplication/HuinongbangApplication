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
import com.hnb.huinongbang.logic.model.CommentList
import com.hnb.huinongbang.logic.model.DoctorFile
import de.hdodenhof.circleimageview.CircleImageView
//根评论的数量
class DoctorComments1Adapter (val activity: Activity, private val commentRootList: List<CommentList>) : RecyclerView.Adapter<DoctorComments1Adapter.ViewHolder>() {
    private lateinit var doctorComments2Adapter: DoctorComments2Adapter
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var comment1UserName:TextView=itemView.findViewById(R.id.comment1UserName)
        var userComment1:TextView=itemView.findViewById(R.id.userComment1)
        var userCommentTime1:TextView=itemView.findViewById(R.id.userCommentTime1)
        var doctorCommentsRecyclerViewSmall: RecyclerView = itemView.findViewById(R.id.doctorCommentsRecyclerViewSmall)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.doctorcomments_recycle_item1, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val commentRoot = commentRootList[position]
        holder.comment1UserName.text =commentRoot.root.dUser_ID.user_name
        holder.userComment1.text =commentRoot.root.discuss_Values
        holder.userCommentTime1.text=commentRoot.root.discuss_CreateTime.toString()

        //适配小聊天RecycleView
        doctorComments2Adapter= DoctorComments2Adapter(this.activity,commentRoot.nodes)
        holder.doctorCommentsRecyclerViewSmall.layoutManager =
            GridLayoutManager(this.activity, 1, RecyclerView.VERTICAL, false)
        holder.doctorCommentsRecyclerViewSmall.adapter=doctorComments2Adapter
    }

    override fun getItemCount() = commentRootList.size
}

