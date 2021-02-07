package com.hnb.huinongbang.ui.planting

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.hnb.huinongbang.R
import com.hnb.huinongbang.logic.model.PlantsNews
import com.hnb.huinongbang.util.LogUtil

class PlantsNewsAdapter (val plantsNewsList: List<PlantsNews>) : RecyclerView.Adapter<PlantsNewsAdapter.ViewHolder>() {
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        //var plantName: TextView = itemView.findViewById(R.id.plant_name)
        var plantTitle: TextView = itemView.findViewById(R.id.plant_title)
        var plantInformation: TextView = itemView.findViewById(R.id.plant_information)
        var plantData: TextView = itemView.findViewById(R.id.plant_data)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.plantsnews_recycle_item, parent, false)
        val holder = ViewHolder(view)
        /*holder.plantName.setOnClickListener {
            val position = holder.adapterPosition
            LogUtil.d("categoryListposition", "${position} ${plantsNewsList[position]}")
        }*/

        return holder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val plantsNews = plantsNewsList[position]

        //holder.plantName.text =plantsNews.plant_Name
        holder.plantTitle.text=plantsNews.plant_Title
        holder.plantInformation.text=plantsNews.plant_Values
        holder.plantData.text="发表时间:"+plantsNews.plant_CreateTime.toString()

    }

    override fun getItemCount() = plantsNewsList.size



}