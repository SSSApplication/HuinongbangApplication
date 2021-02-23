package com.hnb.huinongbang.ui.common

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.hnb.huinongbang.HNBApplication
import com.hnb.huinongbang.R
import com.hnb.huinongbang.logic.Repository
import com.hnb.huinongbang.logic.model.CreateOrderData
import com.hnb.huinongbang.logic.model.GetOrderItemData
import com.hnb.huinongbang.logic.model.OrderItem
import com.hnb.huinongbang.logic.model.Product
import com.hnb.huinongbang.util.LogUtil
import com.hnb.huinongbang.util.ToastUtil
import kotlinx.android.synthetic.main.activity_create_order.*

class CreateOrderActivity : AppCompatActivity() {
    val viewModel by lazy { ViewModelProviders.of(this).get(CreateOrderViewModel::class.java)}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_order)

        var pid = intent.getIntExtra("pid",0)
        var type = intent.getIntExtra("type",-1)

        //下拉刷新
        createOrderRefresh.setColorSchemeResources(R.color.colorPrimary)
        createOrderRefresh.setOnRefreshListener {
            getData()
        }

        //设置点击事件监听器
        setClickListener(pid,type)
    }

    fun getData() {
        createOrderRefresh.isRefreshing = false
    }
    //设置监听器函数
    fun setClickListener(pid : Int,type : Int) {
        //付款
        pay.setOnClickListener {
            if(
                address.text.toString() == "" ||
//                post.text.toString() == "" ||
//                userMessage.text.toString() == "" ||
                receiver.text.toString() == "" ||
                mobile.text.toString() == "" ||
                num.text.toString() == ""
            ){
                ToastUtil.show("请完善信息")
            }else{
                //准备信息
                viewModel.buy(
                    GetOrderItemData(
                        pid.toString(),
                        type.toString(),
                        Repository.getUser().user_ID.toString(),
                        num.text.toString()
                    )
                )
                //监听准备信息返回结果
                viewModel.buyResult.observe(this, Observer { result ->
                    val values = result.getOrNull()
                    if (values != null){
                        LogUtil.d("返回1", "${values}")
                        //获取oiid
                        val oiidList = ArrayList<String>()
                        for(orderItem in values.data){
                            oiidList.add(orderItem.id.toString())
                        }
                        LogUtil.d("oiidList","${oiidList}")
                        val oiid = Array(oiidList.size){"0"}
                        oiidList.toArray(oiid)
                        LogUtil.d("oiid","${oiid[0]}+${arrayOf("d13")[0]}")
                        //创建订单
                        viewModel.pay(
                            CreateOrderData(
                                Repository.getUser().user_ID.toString(),
                                oiid,
                                address.text.toString(),
                                post.text.toString(),
                                receiver.text.toString(),
                                mobile.text.toString(),
                                userMessage.text.toString(),
                                type.toString()
                            )
                        )
                        //监听创建订单返回结果
                        viewModel.payResult.observe(this, Observer { result ->
                            val response = result.getOrNull()
                            if (response != null){
                                LogUtil.d("返回2", "${response}")
                                val intent = Intent(HNBApplication.context, PayActivity::class.java).apply {
                                    putExtra("total", response.message.toFloat())
                                    putExtra("oid", response.data.id)
                                    putExtra("type", response.data.type)
                                }
                                startActivity(intent)
                            }else{
                                ToastUtil.show("订单生成失败2")
                                result.exceptionOrNull()?.printStackTrace()
                            }
                        })
                    }else{
                        ToastUtil.show("订单生成失败1")
                        result.exceptionOrNull()?.printStackTrace()
                    }
                })
            }
        }
    }
}