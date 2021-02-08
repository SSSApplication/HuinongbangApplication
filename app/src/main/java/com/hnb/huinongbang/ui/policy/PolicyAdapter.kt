package com.hnb.huinongbang.ui.policy

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.hnb.huinongbang.R
import com.hnb.huinongbang.logic.model.Policy

class PolicyAdapter (private val fragment: Fragment, val policyList: List<Policy>): RecyclerView.Adapter<PolicyAdapter.ViewHolder>(){
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val policyName: TextView = view.findViewById(R.id.policyName)
        val policDate: TextView = view.findViewById(R.id.policyDate)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.policy_recycler_item, parent, false)
        //val holder = ViewHolder(view)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val policy = policyList[position]
        holder.policyName.text = policy.policy_Topic
        holder.policDate.text = policy.policy_CreateTime.toString()
    }

    override fun getItemCount() = policyList.size
}