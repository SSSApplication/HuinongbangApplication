package com.hnb.huinongbang.ui.planting.doctormodule

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hnb.huinongbang.R
import com.hnb.huinongbang.util.LogUtil
import com.hnb.huinongbang.util.ToastUtil
import kotlinx.android.synthetic.main.activity_doctor.*
import kotlinx.android.synthetic.main.activity_doctor_comments.*
import kotlinx.android.synthetic.main.doctorcomments_recycle_item1.*

class DoctorCommentsActivity : AppCompatActivity() {
    val viewModel by lazy { ViewModelProviders.of(this).get(DoctorViewModel::class.java) }
    private lateinit var doctorComments1Adapter: DoctorComments1Adapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_doctor_comments)
        val doctorCommentsName:TextView=findViewById(R.id.doctorCommentsName)
        val doctorCommentsBelong:TextView=findViewById(R.id.doctorCommentsBelong)
        val doctorCommentsIntroduce:TextView=findViewById(R.id.doctorCommentsIntroduce)

        val doctorId = intent.getStringExtra("doctorId")

        viewModel.getComments(Integer.parseInt(doctorId))
        viewModel.doctorCommentsListLivepData.observe(this, Observer { result ->
            val doctorComments = result.getOrNull()
            if (doctorComments != null) {

                viewModel.doctorCommentsList = doctorComments

                //专家个人信息小界面数据
                doctorCommentsName.text=viewModel.doctorCommentsList.doctor.user_ID.user_name
                doctorCommentsBelong.text=viewModel.doctorCommentsList.doctor.belong
                doctorCommentsIntroduce.text=viewModel.doctorCommentsList.doctor.introduce

                //聊天界面
                doctorComments1Adapter= DoctorComments1Adapter(this,viewModel.doctorCommentsList.disess)
                doctorCommentsRecyclerViewBig.layoutManager =
                    GridLayoutManager(this, 1, RecyclerView.VERTICAL, false)
                doctorCommentsRecyclerViewBig.adapter=doctorComments1Adapter


            } else {
                ToastUtil.show("没有分类")
                result.exceptionOrNull()?.printStackTrace()
            }
        })


        //评论小界面数据


    }


}