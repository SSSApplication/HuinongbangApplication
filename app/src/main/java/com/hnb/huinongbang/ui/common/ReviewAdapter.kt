package com.hnb.huinongbang.ui.common

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.hnb.huinongbang.R
import com.hnb.huinongbang.logic.model.Review
import com.hnb.huinongbang.util.LogUtil
import java.text.SimpleDateFormat

class ReviewAdapter (private val activity: Activity, val reviewList: List<Review>) : RecyclerView.Adapter<ReviewAdapter.ViewHolder>(){
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val anonymousname: TextView = view.findViewById(R.id.anonymousname)
        val content: TextView = view.findViewById(R.id.content)
        val date: TextView = view.findViewById(R.id.date)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.review_recycle_item, parent, false)
        val holder = ViewHolder(view)
        return holder
    }

    override fun onBindViewHolder(holder: ReviewAdapter.ViewHolder, position: Int) {
        val review = reviewList[position]
        holder.anonymousname.text = review.users.user_name
        holder.content.text = review.content
        val date = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        val newDate = date.format(review.createDate)
        holder.date.text = newDate.toString()
        LogUtil.d("评论详情", "${position} ${reviewList[position]}")
    }

    override fun getItemCount() = reviewList.size
}