package com.hnb.huinongbang.ui.my

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.hnb.huinongbang.R
import com.hnb.huinongbang.logic.Repository
import com.hnb.huinongbang.logic.model.GetCartData
import com.hnb.huinongbang.logic.model.GetOrderData
import com.hnb.huinongbang.logic.model.Order
import com.hnb.huinongbang.util.LogUtil
import com.hnb.huinongbang.util.ToastUtil
import kotlinx.android.synthetic.main.activity_order.*

class OrderActivity : AppCompatActivity() {

    val viewModel by lazy { ViewModelProviders.of(this).get(OrderViewModel::class.java)}

    private lateinit var orderAdapter: OrderAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)

        //获取用户类
        val user = Repository.getUser()

        val type = intent.getIntExtra("type", -1)
        changeButtonStyle(all_order)
        viewModel.getOrder(
            GetOrderData(
                user.user_ID.toString(),
                type.toString()
            )
        )
        //监听提交结果
        viewModel.getOrderResult.observe(this, { result ->
            val list = result.getOrNull()
            if (list != null) {
                LogUtil.d("list","${list}")
                viewModel.orderList.clear()
                getOrderList(list)
                val layoutManager = LinearLayoutManager(this)
                order_recycler.layoutManager = layoutManager
                orderAdapter = OrderAdapter(this, viewModel.orderList)
                order_recycler.adapter = orderAdapter
            } else {
                ToastUtil.show("获取失败")
            }
            order_refresh.isRefreshing = false
        })
        return_order.setOnClickListener {
            onBackPressed()
        }
        all_order.setOnClickListener {
            viewModel.status = viewModel.allOrder
            changeButtonStyle(all_order)
            refreshOrderList()
        }
        wait_pay.setOnClickListener {
            viewModel.status = viewModel.waitPay
            changeButtonStyle(wait_pay)
            refreshOrderList()
        }
        wait_delivery.setOnClickListener {
            viewModel.status = viewModel.waitDelivery
            changeButtonStyle(wait_delivery)
            refreshOrderList()
        }
        wait_confirm.setOnClickListener {
            viewModel.status = viewModel.waitConfirm
            changeButtonStyle(wait_confirm)
            refreshOrderList()
        }
        wait_review.setOnClickListener {
            viewModel.status = viewModel.waitReview
            changeButtonStyle(wait_review)
            refreshOrderList()
        }
        order_refresh.setColorSchemeResources(R.color.color_price)
        refreshOrderList()
        order_refresh.setOnRefreshListener {
            refreshOrderList()
        }
    }
    fun getOrderList(orderList: List<Order>) {
        when (viewModel.status) {
            viewModel.allOrder -> {
                viewModel.orderList.addAll(orderList)
            }
            viewModel.waitPay -> {
                for (order in orderList) {
                    if (order.status == "waitPay") {
                        viewModel.orderList.add(order)
                    }
                }
            }
            viewModel.waitDelivery -> {
                for (order in orderList) {
                    if (order.status == "waitDelivery") {
                        viewModel.orderList.add(order)
                    }
                }
            }
            viewModel.waitConfirm -> {
                for (order in orderList) {
                    if (order.status == "waitConfirm"){
                        viewModel.orderList.add(order)
                    }
                }
            }
            viewModel.waitReview -> {
                for (order in orderList) {
                    if (order.status == "waitReview"){
                        viewModel.orderList.add(order)
                    }
                }
            }
        }
    }

    private fun initButtonStyle(){
        all_order.setBackgroundResource(R.drawable.bv_buynow)
        wait_pay.setBackgroundResource(R.drawable.bv_buynow)
        wait_delivery.setBackgroundResource(R.drawable.bv_buynow)
        wait_confirm.setBackgroundResource(R.drawable.bv_buynow)
        wait_review.setBackgroundResource(R.drawable.bv_buynow)
        all_order.setTextColor(Color.parseColor("#FFB400"))
        wait_pay.setTextColor(Color.parseColor("#FFB400"))
        wait_delivery.setTextColor(Color.parseColor("#FFB400"))
        wait_confirm.setTextColor(Color.parseColor("#FFB400"))
        wait_review.setTextColor(Color.parseColor("#FFB400"))
    }
    private fun changeButtonStyle(button: Button) {
        initButtonStyle()
        //change
        button.setBackgroundResource(R.drawable.bg_buttonview)
        button.setTextColor(Color.parseColor("#FFFFFF"))
    }

    fun refreshOrderList() {
        viewModel.getOrder(
            GetOrderData(
                Repository.getUser().user_ID.toString(),
                intent.getIntExtra("type", -1).toString()

        ))
        order_refresh.isRefreshing = true
    }
}