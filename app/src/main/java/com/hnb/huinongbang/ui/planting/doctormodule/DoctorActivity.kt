package com.hnb.huinongbang.ui.planting.doctormodule

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hnb.huinongbang.R
import com.hnb.huinongbang.util.ToastUtil
import kotlinx.android.synthetic.main.activity_doctor.*

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

        doctorRecycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                //解决滑动冲突
                doctorRefresh.isEnabled=false
            }
        })

    }


    fun refreshdata() {
        doctorRefresh.isRefreshing = true
        viewModel.getData(0)
        doctorRefresh.isRefreshing = false
    }

}