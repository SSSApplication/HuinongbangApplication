package com.hnb.huinongbang.ui.common

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.hnb.huinongbang.R
import com.hnb.huinongbang.logic.model.Category
import com.hnb.huinongbang.util.LogUtil

class CategoryAdapter(private val fragment: Fragment, val categoryList: List<Category>) : RecyclerView.Adapter<CategoryAdapter.ViewHolder>(){

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val categoryname: TextView = view.findViewById(R.id.categoryname)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.category_recycle_item, parent, false)
        val holder = ViewHolder(view)
        holder.categoryname.setOnClickListener {
            val position = holder.adapterPosition
            LogUtil.d("categoryListposition", "${position} ${categoryList[position]}")
        }
        return holder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val category = categoryList[position]
        holder.categoryname.text =category.name
        LogUtil.d("分类", "${position} ${categoryList[position]}")
    }

    override fun getItemCount() = categoryList.size

}