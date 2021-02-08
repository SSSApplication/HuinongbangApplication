package com.hnb.huinongbang.ui.planting

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hnb.huinongbang.R
import com.hnb.huinongbang.logic.Repository
import com.hnb.huinongbang.logic.model.PlantsNewsOfCategory
import com.hnb.huinongbang.util.LogUtil
import com.hnb.huinongbang.util.ToastUtil
import kotlinx.android.synthetic.main.activity_product.*
import kotlinx.android.synthetic.main.activity_small_planting.*
import kotlinx.android.synthetic.main.fragment_planting.*

class SmallPlantingActivity : AppCompatActivity() {
    val viewModel by lazy { ViewModelProviders.of(this).get(PlantingViewModel::class.java) }
    lateinit var plantsNewsOfCategory:PlantsNewsOfCategory
    private lateinit var plantsNewsAdapter: PlantsNewsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_small_planting)
        if (viewModel.plant_Name.isEmpty()) {
            viewModel.plant_Name=intent.getStringExtra("plantsCategoryName")?:""
            viewModel.plant_Class=intent.getStringExtra("plantsCategoryClass")?:""
           }
        plantsNewsOfCategory=PlantsNewsOfCategory(viewModel.plant_Class,viewModel.plant_Name)


        refreshdata(plantsNewsOfCategory)
        viewModel.plantsNewsOfCategoryLivepData.observe(this, Observer { result ->
            val plantsNews = result.getOrNull()

            if (plantsNews != null) {
                viewModel.plantsNewsOfCategoryList.clear()
                viewModel.plantsNewsOfCategoryList.addAll(plantsNews)


                //设置为一行显示
                //shoppingRecycler.layoutManager = LinearLayoutManager(this.context, RecyclerView.HORIZONTAL, false)
                plantsNewsOfCategoryRecycler.layoutManager = GridLayoutManager(this, 1, RecyclerView.VERTICAL, false)
                plantsNewsAdapter = PlantsNewsAdapter(this, viewModel.plantsNewsOfCategoryList)

                plantsNewsOfCategoryRecycler.adapter = plantsNewsAdapter

            } else {
                ToastUtil.show("没有分类")
                result.exceptionOrNull()?.printStackTrace()
            }


        })


    }
    fun refreshdata(plantsNewsOfCategory: PlantsNewsOfCategory){
        viewModel.getPlantsNewsOfCategory(plantsNewsOfCategory)
    }
}