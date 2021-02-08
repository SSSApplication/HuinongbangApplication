package com.hnb.huinongbang.ui.policy

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.hnb.huinongbang.R
import com.hnb.huinongbang.logic.model.Classify
import com.hnb.huinongbang.util.LogUtil

class ClassifyAdapter (private val fragment: PolicyFragment, val classifyList: List<Classify>): RecyclerView.Adapter<ClassifyAdapter.ViewHolder>() {


    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val policyClassify: TextView = view.findViewById(R.id.policyClassify)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.policy_classify_item, parent, false)
        val holder = ViewHolder(view)
        holder.policyClassify.setOnClickListener {
            val position = holder.adapterPosition
            LogUtil.d("classifyListposition", "${position} ${classifyList[position]}")
            fragment.showpolicy(classifyList[position].policy)
        }
        return holder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val classify = classifyList[position]
        holder.policyClassify.text = classify.names
        LogUtil.d("分类", "${position} ${classify}")
//        classify.policy 获取对应分类的文章
    }

    override fun getItemCount() = classifyList.size

}