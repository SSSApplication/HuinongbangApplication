package com.hnb.huinongbang.ui.planting

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.hnb.huinongbang.R
import com.hnb.huinongbang.logic.model.PlantsNews
import com.hnb.huinongbang.ui.common.ShowhtmlActivity
import com.hnb.huinongbang.util.LogUtil
import java.text.SimpleDateFormat

class PlantsNewsAdapter (val activity: Activity, val plantsNewsList: List<PlantsNews>) : RecyclerView.Adapter<PlantsNewsAdapter.ViewHolder>() {
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        //var plantName: TextView = itemView.findViewById(R.id.plant_name)
        val plantItem: View = itemView.findViewById(R.id.plantItem)
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
        holder.plantItem.setOnClickListener {
            val position = holder.adapterPosition
            LogUtil.d("plantsNewsListposition", "${position} ${plantsNewsList[position]}")
            val plantsNews = plantsNewsList[position]
            val intent = Intent(parent.context, ShowhtmlActivity::class.java).apply {
                putExtra("id", plantsNews.plant_ID)
                putExtra("m", "plant")
            }
            activity.startActivity(intent)
        }

        return holder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val plantsNews = plantsNewsList[position]

        //holder.plantName.text =plantsNews.plant_Name
        holder.plantTitle.text=plantsNews.plant_Title
        holder.plantInformation.text=plantsNews.plant_Values
        val date = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        val newDate = date.format(plantsNews.plant_CreateTime)
        holder.plantData.text="发表时间:"+newDate.toString()

    }

    override fun getItemCount() = plantsNewsList.size



}