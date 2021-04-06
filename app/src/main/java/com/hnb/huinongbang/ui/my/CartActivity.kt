package com.hnb.huinongbang.ui.my

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.hnb.huinongbang.HNBApplication
import com.hnb.huinongbang.R
import com.hnb.huinongbang.logic.Repository
import com.hnb.huinongbang.logic.model.*
import com.hnb.huinongbang.util.LogUtil
import com.hnb.huinongbang.util.ToastUtil
import kotlinx.android.synthetic.main.activity_cart.*
import kotlinx.android.synthetic.main.activity_cart.pay
import kotlinx.android.synthetic.main.activity_create_order.*

class CartActivity : AppCompatActivity() {

    val viewModel by lazy { ViewModelProviders.of(this).get(CartViewModel::class.java)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        //获取用户类
        val user = Repository.getUser()

        val type = intent.getIntExtra("type", -1)


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

                //支付
                pay.setOnClickListener {
                    LogUtil.d("返回1", "${list}")
                    //获取oiid
                    val oiidList = ArrayList<String>()
                    for(orderItem in list){
                        oiidList.add(orderItem.id.toString())
                    }
                    LogUtil.d("oiidList","${oiidList}")
                    val oiid = Array(oiidList.size){"0"}
                    oiidList.toArray(oiid)
                    LogUtil.d("oiid","${oiid[0]}+${arrayOf("d13")[0]}")
                    viewModel.buy(BuyCartData(
                        oiid,
                        type.toString()
                    ))
                    //监听提交结果
                    viewModel.buyCartResult.observe(this, { result ->
                        val response = result.getOrNull()
                        if (response != null) {
                            if(type == 0){
                                ToastUtil.show("购买请求成功："+response.message+"元")
                            }else{
                                ToastUtil.show("捐赠请求成功："+response.message+"慧农币")
                            }
                        } else {
                            ToastUtil.show("购买请求失败")
                        }
                    })
                }
            } else {
                ToastUtil.show("获取失败")
            }
        })
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