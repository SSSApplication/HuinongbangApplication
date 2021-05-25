package com.hnb.huinongbang.ui.common

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.hnb.huinongbang.HNBApplication
import com.hnb.huinongbang.R
import com.hnb.huinongbang.logic.Repository
import com.hnb.huinongbang.logic.model.PayForDonationData
import com.hnb.huinongbang.logic.model.PayForShopping
import com.hnb.huinongbang.ui.my.MyInformationActivity
import com.hnb.huinongbang.ui.my.OrderActivity
import com.hnb.huinongbang.util.LogUtil
import com.hnb.huinongbang.util.ToastUtil
import kotlinx.android.synthetic.main.activity_create_order.*
import kotlinx.android.synthetic.main.activity_pay.*

class PayActivity : AppCompatActivity() {
    val viewModel by lazy { ViewModelProviders.of(this).get(PayViewModel::class.java)}
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pay)

        var total = intent.getFloatExtra("total",0F)
        var oid = intent.getIntExtra("oid",0)
        var type = intent.getIntExtra("type",-1)

        when (type) {
            0 -> {
                //购物模块
                shouldPay.text = "应支付:" + total + "元"
                //慧农币余额隐藏
                balance.visibility = View.GONE
                //二维码显示
//                QRCode.visibility = View.VISIBLE
                //扣除慧农币按钮隐藏
                deduction.visibility = View.VISIBLE
            }
            1 -> {
                //捐赠模块
                shouldPay.text = "应支付:" + total + "慧农币"
                //慧农币余额显示
                balance.text =  "当前余额：" + Repository.getUser().money.toString() + "慧农币"
                balance.visibility = View.VISIBLE
                //二维码隐藏
//                QRCode.visibility = View.GONE
                //扣除慧农币按钮显示
                deduction.visibility = View.VISIBLE
            }
            else -> {
                //未知跳转
                shouldPay.text = "出错了，请重试"
            }
        }

        //下拉刷新
        refresh()
        //设置点击事件监听器
        setClickListener(total,oid,type)
    }

    //下拉刷新
    fun refresh(){
        payRefresh.setColorSchemeResources(R.color.colorPrimary)
        payRefresh.setOnRefreshListener {
            getData()
        }
    }

    fun getData() {
        payRefresh.isRefreshing = false
    }

    //设置点击事件监听器
    fun setClickListener(total : Float,oid : Int,type : Int){
        deduction.setOnClickListener {
            when (type) {
                0 -> {
                    //购物
                    viewModel.shopping(
                        PayForShopping(
                            oid.toString(),
                            total.toString()
                        )
                    )
                    viewModel.shoppingResult.observe(this, Observer { result ->
                        val response = result.getOrNull()
                        if (response != null){
                            LogUtil.d("付款", "${response}")
                            ToastUtil.show("付款成功")
                            val intent = Intent(HNBApplication.context, OrderActivity::class.java).apply {
                                putExtra("type", 0)
                            }
                            startActivity(intent)
                        }else{
                            ToastUtil.show("付款失败")
                            result.exceptionOrNull()?.printStackTrace()
                        }
                    })
                }
                1 -> {
                    //捐赠
                    //付款慧农币
                    viewModel.deduction(
                        PayForDonationData(
                            oid.toString(),
                            Repository.getUser().user_ID.toString(),
                            total.toString()
                        )
                    )
                    //监听付款慧农币订单返回结果
                    viewModel.deductionResult.observe(this, Observer { result ->
                        val response = result.getOrNull()
                        if (response != null){
                            LogUtil.d("付款", "${response}")
                            ToastUtil.show("付款成功")
                            val intent = Intent(HNBApplication.context, OrderActivity::class.java).apply {
                                putExtra("type", 1)
                            }
                            startActivity(intent)
                        }else{
                            ToastUtil.show("付款失败")
                            result.exceptionOrNull()?.printStackTrace()
                        }
                    })
                }
                else -> {
                    //未知
                    ToastUtil.show("信息缺失，请尝试刷新页面")
                }
            }
        }
    }
}