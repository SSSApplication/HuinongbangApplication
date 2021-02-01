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
        holder.anonymousname.text = review.user.getAnonymousName()
        holder.content.text = review.content
        holder.date.text = review.date.toString()
        LogUtil.d("评论详情", "${position} ${reviewList[position]}")
    }

    override fun getItemCount() = reviewList.size
}