package com.hnb.huinongbang.ui.planting.doctormodule

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.hnb.huinongbang.R
import com.hnb.huinongbang.logic.model.DoctorFile
import com.hnb.huinongbang.logic.model.DoctorInformation
import com.hnb.huinongbang.ui.common.ShowhtmlActivity
import com.hnb.huinongbang.util.LogUtil

import de.hdodenhof.circleimageview.CircleImageView

class DoctorItemAdapter (val activity: Activity, val doctorFileList: List<DoctorFile>) : RecyclerView.Adapter<DoctorItemAdapter.ViewHolder>() {
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var doctorHead: CircleImageView = itemView.findViewById(R.id.doctorHead)
        val doctorName: TextView = itemView.findViewById(R.id.doctor_Name)
        var doctorInformation: TextView = itemView.findViewById(R.id.doctor_information)
        var doctorBelong: TextView = itemView.findViewById(R.id.doctor_belong)
        var doctorIntroduce: TextView = itemView.findViewById(R.id.doctor_introduce)
        var doctorButton: Button = itemView.findViewById(R.id.doctor_button)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.doctor_recycle_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val doctorInformation = doctorFileList[position]
        holder.doctorName.text =doctorInformation.user_ID.user_name
        holder.doctorBelong.text =doctorInformation.belong
        holder.doctorIntroduce.text =doctorInformation.introduce
        holder.doctorButton.setOnClickListener {
            val doctorId=doctorInformation.user_ID.user_ID.toString()
            val intent1 = Intent(this.activity, DoctorCommentsActivity::class.java).apply { putExtra("doctorId",doctorId)}

            this.activity.startActivity(intent1)
        }

        //holder.plantData.text="发表时间:"+plantsNews.plant_CreateTime.toString()


    }

    override fun getItemCount() = doctorFileList.size
    }


