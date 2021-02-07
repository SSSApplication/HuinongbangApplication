package com.hnb.huinongbang.ui.planting

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.hnb.huinongbang.R
import com.hnb.huinongbang.logic.model.PlantingCategory
import com.hnb.huinongbang.util.LogUtil

class SmallPlantingCategoryAdapter (private val fragment: Fragment, val smallPlantingCategoryList: PlantingCategory) : RecyclerView.Adapter<SmallPlantingCategoryAdapter.ViewHolder>() {
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        //分类列表
        var smallPlantingCategoryButton: Button = itemView.findViewById(R.id.smallPlantingCategoryButton)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.smallplantingcategory_recycler_item, parent, false)
        val holder = ViewHolder(view)
        return holder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val plantingCategory = smallPlantingCategoryList.plant[position]
        val plantsCategoryClass = smallPlantingCategoryList.names

        holder.smallPlantingCategoryButton.text = plantingCategory



        holder.smallPlantingCategoryButton.setOnClickListener {
           val intent: Intent =Intent(this.fragment.context,SmallPlantingActivity::class.java).apply { putExtra("plantsCategoryName",plantingCategory);putExtra("plantsCategoryClass",plantsCategoryClass)}
            this.fragment.startActivity(intent)

        }

    }

    override fun getItemCount() = smallPlantingCategoryList.plant.size



}