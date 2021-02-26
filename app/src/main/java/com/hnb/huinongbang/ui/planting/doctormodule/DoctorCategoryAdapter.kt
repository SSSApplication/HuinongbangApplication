package com.hnb.huinongbang.ui.planting.doctormodule

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hnb.huinongbang.R
import com.hnb.huinongbang.logic.model.DoctorInformation
import kotlinx.android.synthetic.main.activity_doctor.*

class DoctorCategoryAdapter (val activity: Activity, val doctorCategoryList: List<DoctorInformation>) : RecyclerView.Adapter<DoctorCategoryAdapter.ViewHolder>() {

    private lateinit var doctorItemAdapter: DoctorItemAdapter

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val doctorName: TextView = itemView.findViewById(R.id.doctor_category)
        var doctorItemRecycler: RecyclerView = itemView.findViewById(R.id.doctorItemRecycler)

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.doctorcategory_recycle_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val doctorInformation = doctorCategoryList[position]
        holder.doctorName.text =doctorInformation.names
        doctorItemAdapter= DoctorItemAdapter(this.activity,doctorInformation.doctorfile)
        holder.doctorItemRecycler.layoutManager =GridLayoutManager(this.activity,1,RecyclerView.VERTICAL, false)
        holder.doctorItemRecycler.adapter=doctorItemAdapter
    }

    override fun getItemCount() = doctorCategoryList.size
}

