package com.hnb.huinongbang.ui.planting.doctormodule

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hnb.huinongbang.R
import com.hnb.huinongbang.ui.planting.PlantingCategoryAdapter
import com.hnb.huinongbang.ui.planting.PlantingViewModel
import com.hnb.huinongbang.ui.planting.SmallPlantingCategoryAdapter
import com.hnb.huinongbang.util.LogUtil
import com.hnb.huinongbang.util.ToastUtil
import kotlinx.android.synthetic.main.activity_doctor.*
import kotlinx.android.synthetic.main.activity_small_planting.*
import kotlinx.android.synthetic.main.fragment_planting.*

class DoctorActivity : AppCompatActivity() {

    val viewModel by lazy { ViewModelProviders.of(this).get(DoctorViewModel::class.java) }

    private lateinit var doctorCategoryAdapter: DoctorCategoryAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_doctor)
        refreshdata()
        //分类
        viewModel.doctorInformationListLivepData.observe(this, Observer { result ->
            val doctorInformation = result.getOrNull()
            if (doctorInformation != null) {
                viewModel.doctorInformationList.clear()
                viewModel.doctorInformationList.addAll(doctorInformation)
                doctorCategoryAdapter= DoctorCategoryAdapter(this,doctorInformation)
                doctorRecycler.layoutManager =
                    GridLayoutManager(this, 1, RecyclerView.VERTICAL, false)
                doctorRecycler.adapter=doctorCategoryAdapter

                doctorRefresh.isRefreshing = false
            } else {
                ToastUtil.show("没有分类")
                result.exceptionOrNull()?.printStackTrace()
            }
        })

        //下拉刷新
        doctorRefresh.setColorSchemeResources(R.color.colorPrimary)
        doctorRefresh.setOnRefreshListener {
            refreshdata()
        }
    }


    fun refreshdata() {
        viewModel.getData(0)
        doctorRefresh.isRefreshing = true
    }

}