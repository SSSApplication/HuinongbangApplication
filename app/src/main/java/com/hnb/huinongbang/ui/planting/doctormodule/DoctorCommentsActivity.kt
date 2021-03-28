package com.hnb.huinongbang.ui.planting.doctormodule

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.observe
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.hnb.huinongbang.BottomActivity
import com.hnb.huinongbang.HNBApplication
import com.hnb.huinongbang.R
import com.hnb.huinongbang.logic.Repository
import com.hnb.huinongbang.logic.model.*
import com.hnb.huinongbang.util.LogUtil
import com.hnb.huinongbang.util.ToastUtil
import kotlinx.android.synthetic.main.activity_doctor.*
import kotlinx.android.synthetic.main.activity_doctor_comments.*
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_small_planting.*
import kotlinx.android.synthetic.main.dialog_bottom_sheet.*
import kotlinx.android.synthetic.main.doctorcomments_recycle_item1.*

class DoctorCommentsActivity : AppCompatActivity(), CommentsOnItemClickListener1, CommentsOnItemClickListener2{
    val viewModel by lazy { ViewModelProviders.of(this).get(DoctorViewModel::class.java) }
    private lateinit var doctorComments1Adapter: DoctorComments1Adapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_doctor_comments)
        val doctorCommentsName: TextView = findViewById(R.id.doctorCommentsName)
        val doctorCommentsBelong: TextView = findViewById(R.id.doctorCommentsBelong)
        val doctorCommentsIntroduce: TextView = findViewById(R.id.doctorCommentsIntroduce)
        val doctorId = intent.getStringExtra("doctorId")

        viewModel.getComments(Integer.parseInt(doctorId))
        viewModel.doctorCommentsListLivepData.observe(this, Observer { result ->
            val doctorComments = result.getOrNull()
            if (doctorComments != null) {
                viewModel.doctorCommentsList = doctorComments

                //专家个人信息小界面数据
                doctorCommentsName.text = viewModel.doctorCommentsList.doctor.user_ID.user_name
                doctorCommentsBelong.text = viewModel.doctorCommentsList.doctor.belong
                doctorCommentsIntroduce.text = viewModel.doctorCommentsList.doctor.introduce

                //专家提问按钮
                askForDoctorButton.setOnClickListener {
                    showBottomSheetDialog1(
                        this,
                        "提问@${viewModel.doctorCommentsList.doctor.user_ID.user_name}专家",
                        viewModel.doctorCommentsList.doctor.user_ID.user_ID
                    )
                }
                //聊天界面
                doctorComments1Adapter =
                    DoctorComments1Adapter(this, viewModel.doctorCommentsList.disess,viewModel)
                doctorComments1Adapter.setOnItemClickListener1(this)
                doctorComments1Adapter.setOnItemClickListener2(this)
                doctorCommentsRecyclerViewBig.layoutManager =
                    GridLayoutManager(this, 1, RecyclerView.VERTICAL, false)
                doctorCommentsRecyclerViewBig.adapter = doctorComments1Adapter

            } else {
                ToastUtil.show("没有分类")
                result.exceptionOrNull()?.printStackTrace()
            }
        })

        doctorCommentsRefresh.setOnRefreshListener {
            refreshdata(viewModel.doctorCommentsList.doctor.user_ID.user_ID)
        }

    }

    //回复提问
    override fun onItemClick1(commentRoot: CommentList, position: Int) {
        showBottomSheetDialog2(
            this,
            "回复@${commentRoot.root.dUser_ID.user_name}",
            commentRoot.root.dUserup_ID.user_ID,
            commentRoot.root.discuss_ID
        )
    }

    //回复提问
    override fun onItemClick2(comment: Comment, position: Int) {
        showBottomSheetDialog2(
            this,
            "回复@${comment.dUser_ID.user_name}",
            comment.dUserup_ID.user_ID,
            comment.discuss_ID
        )
    }

    private fun showBottomSheetDialog1(context: Context, callContentInfo: String, dUserup_ID: Int) {
        val bottomSheetDialog: BottomSheetDialog = BottomSheetDialog(context)
        val dialogView: View =
            LayoutInflater.from(context).inflate(R.layout.dialog_bottom_sheet, null)
        val callContent: EditText = dialogView.findViewById(R.id.callContent)
        val callContentButton: TextView = dialogView.findViewById(R.id.callContentButton)
        callContent?.hint = callContentInfo
        callContentButton?.setOnClickListener {
            val doctorCommentBack = DoctorCommentBack(
                Repository.getUser().user_ID,
                dUserup_ID,
                0,
                callContent.text.toString()
            )
            viewModel.doctorCommentBack(doctorCommentBack)
            viewModel.doctorCommentBackLiveData.observe(this, { result ->
            })
            refreshdata(dUserup_ID)
            Toast.makeText(context, "回复成功成功:${callContent.text}", Toast.LENGTH_SHORT).show()
            bottomSheetDialog.dismiss()
        }
        if (dialogView != null) {
            bottomSheetDialog.setContentView(dialogView)
        }
        bottomSheetDialog.show()
    }

    private fun showBottomSheetDialog2(
        context: Context,
        callContentInfo: String,
        dUserup_ID: Int,
        Discuss_root: Int
    ) {
        val bottomSheetDialog: BottomSheetDialog = BottomSheetDialog(context)
        val dialogView: View =
            LayoutInflater.from(context).inflate(R.layout.dialog_bottom_sheet, null)
        val callContent: EditText = dialogView.findViewById(R.id.callContent)
        val callContentButton: TextView = dialogView.findViewById(R.id.callContentButton)
        callContent?.hint = callContentInfo
        callContentButton?.setOnClickListener {
            val doctorCommentBack = DoctorCommentBack(
                Repository.getUser().user_ID,
                dUserup_ID,
                Discuss_root,
                callContent.text.toString()
            )
            viewModel.doctorCommentBack(doctorCommentBack)
            viewModel.doctorCommentBackLiveData.observe(this, { result ->
            })
            refreshdata(dUserup_ID)
            Toast.makeText(context, "回复成功成功:${callContent.text}", Toast.LENGTH_SHORT).show()
            bottomSheetDialog.dismiss()
        }
        if (dialogView != null) {
            bottomSheetDialog.setContentView(dialogView)
        }
        bottomSheetDialog.show()
    }

    //发表帖子或者回复后刷新界面
    fun refreshdata(id: Int) {
        doctorCommentsRefresh.isRefreshing = true
        viewModel.getComments(id)
        doctorCommentsRefresh.isRefreshing = false
    }


}