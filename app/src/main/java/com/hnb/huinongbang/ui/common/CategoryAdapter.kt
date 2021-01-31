package com.hnb.huinongbang.ui.common

import android.content.Context
import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.hnb.huinongbang.HNBApplication.Companion.context
import com.hnb.huinongbang.R
import com.hnb.huinongbang.logic.model.Category
import com.hnb.huinongbang.util.LogUtil


class CategoryAdapter(private val fragment: Fragment, val categoryList: List<Category>) : RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        //val categoryname: TextView = view.findViewById(R.id.categoryname)
        //分类列表
        var categorybutton: Button = itemView.findViewById(R.id.categorybutton)
    }

    /* class ResourceMan {
         companion object {
             fun getResId(variableName:String, c:Class<*>):Int{
                 val idField: Field = c.getDeclaredField(variableName)
                 return idField.getInt(idField)
             }
         }
     }*/
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.category_recycle_item, parent, false)
        val holder = ViewHolder(view)
        holder.categorybutton.setOnClickListener {
            val position = holder.adapterPosition
            LogUtil.d("categoryListposition", "${position} ${categoryList[position]}")
        }

        holder.categorybutton.setOnClickListener {
            val position = holder.adapterPosition
            LogUtil.d("categoryListposition", "${position} ${categoryList[position]}")
        }
        return holder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val category = categoryList[position]

        holder.categorybutton.text = category.name
        //设置图片,图片名为id.png
        val r: Resources = context.resources;
        var name = "shoping"+category.id.toString()
        val sqq = this.getResId(name, context)
        holder.categorybutton.setCompoundDrawablesWithIntrinsicBounds(0, sqq, 0, 0)
        LogUtil.d("分类", "${position} ${categoryList[position]}")
    }

    override fun getItemCount() = categoryList.size

    //根据String获取id:R.id.String
    fun getResId(name: String, context: Context): Int {
        val r: Resources = context.resources;
        val sqq = r.getIdentifier(name, "drawable", "com.hnb.huinongbang")
        return sqq
    }

}