package com.hnb.huinongbang.ui.policy

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.hnb.huinongbang.HNBApplication
import com.hnb.huinongbang.R
import com.hnb.huinongbang.ui.Planting.PlantingViewModel
import com.hnb.huinongbang.ui.common.BannerDataBean
import com.hnb.huinongbang.ui.common.ClassifyAdapter
import com.hnb.huinongbang.ui.common.PolicyAdapter
import com.hnb.huinongbang.util.ToastUtil
import com.youth.banner.Banner
import com.youth.banner.adapter.BannerImageAdapter
import com.youth.banner.holder.BannerImageHolder
import com.youth.banner.indicator.CircleIndicator
import kotlinx.android.synthetic.main.fragment_policy.*

class PolicyFragment : Fragment() {

    val viewModel by lazy { ViewModelProviders.of(this).get(PolicyViewModel::class.java)}

    private lateinit var policyAdapter: PolicyAdapter
    private lateinit var classifyAdapter: ClassifyAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_policy, container, false)
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        refreshBanner()
        refreshData()
        viewModel.classifiesLiveData.observe(this, {result ->
            val classifies = result.getOrNull()
            if (classifies != null){
                viewModel.classifyList.clear()
                viewModel.classifyList.addAll(classifies)
                policyClassifyRecycler.layoutManager = LinearLayoutManager(this.context, RecyclerView.HORIZONTAL, false)
                classifyAdapter = ClassifyAdapter(this, viewModel.classifyList)
                policyClassifyRecycler.adapter = classifyAdapter
            }else {
                ToastUtil.show("没有分类")
                result.exceptionOrNull()?.printStackTrace()
            }
        })

        viewModel.policiesLiveData.observe(this, {result ->
            val policies = result.getOrNull()
            if (policies != null){
                viewModel.policyList.clear()
                viewModel.policyList.addAll(policies)
                policyRecycler.layoutManager = LinearLayoutManager(this.context, RecyclerView.VERTICAL, false)
                policyAdapter = PolicyAdapter(this, viewModel.policyList)
                policyRecycler.adapter = policyAdapter
            }else {
                ToastUtil.show("没有政策")
                result.exceptionOrNull()?.printStackTrace()
            }
            policyRefresh.isRefreshing = false
        })

        policyRefresh.setColorSchemeResources(R.color.colorPrimary)
        policyRefresh.setOnRefreshListener {
            refreshData()
            refreshBanner()
        }
    }

    fun refreshData(){
        viewModel.getData(0)
        policyRefresh.isRefreshing = true
    }
    fun refreshBanner(){
        //轮播图
        var banner: Banner<BannerDataBean, BannerImageAdapter<BannerDataBean>> = activity!!.findViewById(R.id.pbanner)
        banner.setAdapter(object : BannerImageAdapter<BannerDataBean>(BannerDataBean.policyData) {
            override fun onBindView(
                holder: BannerImageHolder,
                data: BannerDataBean,
                position: Int,
                size: Int
            ) {
                Glide.with(holder.itemView)
                    .load(data.imageUrl)
                    .apply(RequestOptions.bitmapTransform(RoundedCorners(30)))
                    .into(holder.imageView)
            }
        }).addBannerLifecycleObserver(this).setIndicator(CircleIndicator(HNBApplication.context))
    }
}