package com.hnb.huinongbang.ui.my

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hnb.huinongbang.HNBApplication
import com.hnb.huinongbang.R
import com.hnb.huinongbang.logic.model.Order
import com.hnb.huinongbang.logic.network.ServiceCreator
import com.hnb.huinongbang.ui.common.PayActivity
import com.hnb.huinongbang.util.LogUtil

class OrderAdapter(private val activity: OrderActivity, val orderList: List<Order>): RecyclerView.Adapter<OrderAdapter.ViewHolder>() {
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val orderImg: ImageView = view.findViewById(R.id.order_img)
        val orderTitle: TextView = view.findViewById(R.id.order_title)
        val buyNumber: TextView = view.findViewById(R.id.buy_number)
        val payPrice: TextView = view.findViewById(R.id.pay_price)
        val orderStatus: TextView = view.findViewById(R.id.order_status)
        val buyNow: Button = view.findViewById(R.id.buy_now)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.order_recycler_item, parent, false)
        val holder = ViewHolder(view)

        return holder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val order = orderList[position]
        LogUtil.d("order", "${order}")
        val orderItem = order.orderItems[0]
        val product = orderItem?.product
        var imageUrl = ServiceCreator.firstProductImage + product?.firstProductImage?.id.toString() + ".jpg"
        LogUtil.d("imageName", "${imageUrl}")
        Glide.with(HNBApplication.context).load(imageUrl).into(holder.orderImg)
        holder.orderTitle.text = product?.name
        holder.buyNumber.text = orderItem?.number.toString()
        holder.payPrice.text = order.total.toString()
        when(order.status) {
            "waitPay" -> {
                holder.orderStatus.text = "待付款"
            }
            "waitDelivery" -> {
                holder.orderStatus.text = "待发货"
            }
            "waitConfirm" -> {
                holder.orderStatus.text = "待收货"
            }
            "waitReview" -> {
                holder.orderStatus.text = "待评价"
            }
        }
        if (order.status == "waitPay"){
            holder.buyNow.visibility = View.VISIBLE
        }
        holder.buyNow.setOnClickListener {
            val intent = Intent(HNBApplication.context, PayActivity::class.java).apply {
                putExtra("oid",order.id)
                putExtra("total",order.total)
                putExtra("type",order.type)
            }
            activity.startActivity(intent)
        }
    }

    override fun getItemCount() = orderList.size
}