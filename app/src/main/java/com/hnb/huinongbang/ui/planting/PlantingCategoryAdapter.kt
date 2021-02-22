package com.hnb.huinongbang.ui.planting


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hnb.huinongbang.R
import com.hnb.huinongbang.logic.model.PlantingCategory
import com.hnb.huinongbang.util.LogUtil


class PlantingCategoryAdapter(
    private val fragment: Fragment,
    val plantingCategoryList: List<PlantingCategory>
) : RecyclerView.Adapter<PlantingCategoryAdapter.ViewHolder>() {
    private lateinit var smallPlantingCategoryAdapter: SmallPlantingCategoryAdapter

    /**
     * 标记展开的item
     */
    private var opened = -1

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {
        var plantingCategoryButton: Button = itemView.findViewById(R.id.plantingcategorybutton)
        var smallPlantingCategoryRecycler: RecyclerView =
            itemView.findViewById(R.id.smallplantingcategoryRecycler)

        init {
            plantingCategoryButton.setOnClickListener(this)
        }

        /**
         * 此方法实现列表数据的绑定和item的展开/关闭
         */
        fun bindView( position: Int, plantingCategory: PlantingCategory) {
            plantingCategoryButton.text = plantingCategory.names
            val smallPlantingCategorydd = plantingCategoryList[position]
            val length=smallPlantingCategorydd.plant.size
            smallPlantingCategoryAdapter =
                SmallPlantingCategoryAdapter(fragment, smallPlantingCategorydd)
            if(length<6){
                smallPlantingCategoryRecycler.layoutManager =
                    GridLayoutManager(fragment.context, 1, RecyclerView.VERTICAL, false)

            }else if(length<11){
                smallPlantingCategoryRecycler.layoutManager =
                    GridLayoutManager(fragment.context, 2, RecyclerView.VERTICAL, false)

            }else {
                smallPlantingCategoryRecycler.layoutManager =
                    GridLayoutManager(fragment.context, 5, RecyclerView.VERTICAL, false)

            }
           smallPlantingCategoryRecycler.adapter = smallPlantingCategoryAdapter
            if (position == opened) {
                plantingCategoryButton.isActivated=true
                smallPlantingCategoryRecycler.visibility = View.VISIBLE
            } else {
                plantingCategoryButton.isActivated=false
                smallPlantingCategoryRecycler.visibility = View.GONE

            }
        }

        //点击事件
        override fun onClick(v: View?) {
            if (opened == adapterPosition) {
                //当点击的item已经被展开了, 就关闭.并控制按钮的点击颜色
                opened = -1

                plantingCategoryButton.isActivated=true
                notifyItemChanged(adapterPosition)
            } else {
                plantingCategoryButton.isActivated=false
                val oldOpened = opened
                opened = adapterPosition
                notifyItemChanged(oldOpened)
                notifyItemChanged(opened)
            }
        }


    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.plantingcategory_recycle_item, parent, false)
        val holder = ViewHolder(view)



        return holder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        //val plantingCategory = plantingCategoryList[position]

        holder.bindView(position,plantingCategoryList[position])

        LogUtil.d("分类", "${position} ${plantingCategoryList[position]}")
    }

    override fun getItemCount() = plantingCategoryList.size


}