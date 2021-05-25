package com.hnb.huinongbang.ui.my

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hnb.huinongbang.HNBApplication
import com.hnb.huinongbang.R
import com.hnb.huinongbang.logic.model.OrderItem
import com.hnb.huinongbang.logic.network.ServiceCreator
import com.hnb.huinongbang.ui.common.ProductActivity
import com.hnb.huinongbang.util.LogUtil
import com.hnb.huinongbang.util.ToastUtil
import kotlinx.android.synthetic.main.activity_order.*

class CartAdapter(private val activity: CartActivity, val orderItemList: List<OrderItem>): RecyclerView.Adapter<CartAdapter.ViewHolder>() {
    val viewModel  = ViewModelProviders.of(activity).get(CartViewModel::class.java)
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val img: ImageView = view.findViewById(R.id.cartImage)
        val title: TextView = view.findViewById(R.id.product_name)
        val number: TextView = view.findViewById(R.id.cart_number)
        val price: TextView = view.findViewById(R.id.cart_price)
        val delete: Button = view.findViewById(R.id.delete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cart_recycler_item, parent, false)
        val holder = ViewHolder(view)

        return holder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val orderItem = orderItemList[position]
        val product = orderItem.product
        LogUtil.d("order", "${orderItem}")
        var imageUrl = ServiceCreator.firstProductImage + product?.firstProductImage?.id.toString() + ".jpg"
        LogUtil.d("imageName", "${imageUrl}")
        Glide.with(HNBApplication.context).load(imageUrl).into(holder.img)
        holder.title.text = product.name
        holder.number.text = orderItem.number.toString()
        holder.price.text = (orderItem.number*product.promotePrice).toString()

        holder.itemView.setOnClickListener {
            val intent = Intent(HNBApplication.context, ProductActivity::class.java).apply {
                putExtra("pid",product.id)
            }
            activity.startActivity(intent)
        }
        holder.delete.setOnClickListener {
            viewModel.delete(orderItem.id)
        }
        viewModel.deleteCartResult.observe(this.activity, { result ->
            val list = result.getOrNull()
            if (list != null) {
                LogUtil.d("list","${list}")
                ToastUtil.show("删除成功")
            } else {
                ToastUtil.show("删除失败")
            }
        })
    }

    override fun getItemCount() = orderItemList.size
}