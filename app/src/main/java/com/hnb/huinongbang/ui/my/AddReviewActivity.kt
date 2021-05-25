package com.hnb.huinongbang.ui.my

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.hnb.huinongbang.HNBApplication
import com.hnb.huinongbang.R
import com.hnb.huinongbang.logic.Repository
import com.hnb.huinongbang.logic.model.AddReviewData
import com.hnb.huinongbang.ui.common.PayActivity
import com.hnb.huinongbang.util.LogUtil
import com.hnb.huinongbang.util.ToastUtil
import kotlinx.android.synthetic.main.activity_add_review.*

class AddReviewActivity : AppCompatActivity() {
    val viewModel by lazy { ViewModelProviders.of(this).get(OrderViewModel::class.java)}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_review)

        val oid = intent.getStringExtra("oid").toString()
        val pid = intent.getStringExtra("pid").toString()
        LogUtil.d("oid","${oid}")
        LogUtil.d("pid","${pid}")
        val uid = Repository.getUser().user_ID.toString()
        LogUtil.d("content","${reviewContent.text}")
        review.setOnClickListener {
            viewModel.addReview(
                AddReviewData(
                    oid,
                    pid,
                    uid,
                    reviewContent.text.toString()
                )
            )
        }
        return_review.setOnClickListener {
            onBackPressed()
        }

        viewModel.addReviewResult.observe(this, Observer { result ->
            val response = result.getOrNull()
            if (response != null){
                LogUtil.d("评价", "${response}")
                ToastUtil.show("评价成功")
                val intent = Intent(HNBApplication.context, OrderActivity::class.java).apply {
                    putExtra("type",0)
                }
                startActivity(intent)
            }else{
                ToastUtil.show("评价失败")
                result.exceptionOrNull()?.printStackTrace()
            }
        })
    }
}