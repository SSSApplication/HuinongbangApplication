package com.hnb.huinongbang.ui.planting.doctormodule

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.*
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.hnb.huinongbang.R
import com.hnb.huinongbang.logic.Repository
import com.hnb.huinongbang.logic.model.Comment
import com.hnb.huinongbang.logic.model.CommentList
import com.hnb.huinongbang.logic.model.DoctorCommentBack
import com.hnb.huinongbang.logic.model.DoctorFile
import de.hdodenhof.circleimageview.CircleImageView
import androidx.lifecycle.ViewModelProviders.of as of1

//根评论的数量
class DoctorComments1Adapter (val activity: Activity, private val commentRootList: List<CommentList>,viewModel:DoctorViewModel) : RecyclerView.Adapter<DoctorComments1Adapter.ViewHolder>(),CommentsOnItemClickListener2 {
    private lateinit var doctorComments2Adapter: DoctorComments2Adapter

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var comment1UserName:TextView=itemView.findViewById(R.id.comment1UserName)
        var userComment1:TextView=itemView.findViewById(R.id.userComment1)
        var userCommentTime1:TextView=itemView.findViewById(R.id.userCommentTime1)
        var doctorCommentsRecyclerViewSmall: RecyclerView = itemView.findViewById(R.id.doctorCommentsRecyclerViewSmall)
    }

    //点击事件通过OnItemClickListerner监听
    private var commentsOnItemClickListener1:CommentsOnItemClickListener1? = null

    fun setOnItemClickListener1(onItemClickListener:CommentsOnItemClickListener1?){
        commentsOnItemClickListener1 = onItemClickListener
    }
    //点击事件通过OnItemClickListerner2监听
    private var commentsOnItemClickListener2:CommentsOnItemClickListener2? = null

    fun setOnItemClickListener2(onItemClickListener2:CommentsOnItemClickListener2?){
        commentsOnItemClickListener2 = onItemClickListener2
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

        //item点击事件监听
        holder.itemView.setOnClickListener {
            commentsOnItemClickListener1?.onItemClick1(commentRoot,position)
        }

        //适配小聊天RecycleView
        doctorComments2Adapter= DoctorComments2Adapter(this.activity,commentRoot.nodes)
        holder.doctorCommentsRecyclerViewSmall.layoutManager =
            GridLayoutManager(this.activity, 1, RecyclerView.VERTICAL, false)
        doctorComments2Adapter.setOnItemClickListener(this)
        holder.doctorCommentsRecyclerViewSmall.adapter=doctorComments2Adapter

    }

    override fun getItemCount() = commentRootList.size

    //回复评论
    override fun onItemClick2(comment: Comment, position: Int) {
        commentsOnItemClickListener2?.onItemClick2(comment,position)
    }



}

