package com.hnb.huinongbang.ui.common

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.hnb.huinongbang.R
import com.hnb.huinongbang.logic.model.PropertyValue
import com.hnb.huinongbang.util.LogUtil

class PropertyValuesAdapter (private val activity: Activity, val propertyValueList: List<PropertyValue>) : RecyclerView.Adapter<PropertyValuesAdapter.ViewHolder>(){
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val property: TextView = view.findViewById(R.id.property)
        val value: TextView = view.findViewById(R.id.value)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PropertyValuesAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.propertyvalue_recycle_item, parent, false)
        val holder = ViewHolder(view)
        return holder
    }

    override fun onBindViewHolder(holder: PropertyValuesAdapter.ViewHolder, position: Int) {
        val propertyValue = propertyValueList[position]
        holder.property.text = propertyValue.property.name
        holder.value.text = propertyValue.value
        LogUtil.d("属性内容", "${position} ${propertyValue}")
    }

    override fun getItemCount() = propertyValueList.size
}