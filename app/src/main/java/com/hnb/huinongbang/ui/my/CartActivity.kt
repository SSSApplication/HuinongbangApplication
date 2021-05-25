package com.hnb.huinongbang.ui.my

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.hnb.huinongbang.R
import com.hnb.huinongbang.logic.Repository
import com.hnb.huinongbang.logic.model.*
import com.hnb.huinongbang.util.LogUtil
import com.hnb.huinongbang.util.ToastUtil
import kotlinx.android.synthetic.main.activity_cart.*
import kotlinx.android.synthetic.main.activity_create_order.*
import kotlinx.android.synthetic.main.activity_order.*

class CartActivity : AppCompatActivity() {

    val viewModel by lazy { ViewModelProviders.of(this).get(CartViewModel::class.java)}
    private lateinit var cartAdapter: CartAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        //获取用户类
        val user = Repository.getUser()
        LogUtil.d("user","${user}")

        val type = intent.getIntExtra("type", -1)

        return_cart.setOnClickListener {
            onBackPressed()
        }
        viewModel.getCart(
            GetCartData(
                user.user_ID.toString(),
                type.toString()
            )
        )
        //监听提交结果
        viewModel.getCartResult.observe(this, { result ->
            val list = result.getOrNull()
            if (list != null) {
                viewModel.cartList.clear()
                viewModel.cartList.addAll(list)
                val layoutManager = LinearLayoutManager(this)
                cart_recycler.layoutManager = layoutManager
                cartAdapter = CartAdapter(this, viewModel.cartList)
                cart_recycler.adapter = cartAdapter
            } else {
                ToastUtil.show("获取失败")
            }
            cartRefresh.isRefreshing = false
        })
        cartRefresh.setColorSchemeResources(R.color.color_price)
        cartRefresh.setOnRefreshListener {
            viewModel.getCart(
                GetCartData(
                    user.user_ID.toString(),
                    type.toString()
                )
            )
            cartRefresh.isRefreshing = true
        }
    }


    //删除购物车中的一项
    fun delete(oiid:Int){
        viewModel.delete(oiid)
        //监听提交结果
        viewModel.deleteCartResult.observe(this, { result ->
            val list = result.getOrNull()
            if (list != null) {
                ToastUtil.show("删除成功")
            } else {
                ToastUtil.show("删除失败")
            }
        })
    }
}