package com.hnb.huinongbang.ui.planting.doctormodule

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.hnb.huinongbang.R
import com.hnb.huinongbang.logic.model.Comment
import com.hnb.huinongbang.logic.model.CommentList
import com.hnb.huinongbang.logic.model.DoctorFile
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.activity_doctor_comments.*

//根评论的数量
class DoctorComments2Adapter (val activity: Activity, val commentNodeList: List<Comment>) : RecyclerView.Adapter<DoctorComments2Adapter.ViewHolder>() {
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var itemViewss=itemView
        var commentUserName2:TextView=itemView.findViewById(R.id.commentUserName2)
        var userComment2:TextView=itemView.findViewById(R.id.userComment2)
        var userCommentTime2:TextView=itemView.findViewById(R.id.userCommentTime2)
         }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.doctorcomments_recycle_item2, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val commentNode = commentNodeList[position]
        holder.commentUserName2.text =commentNode.dUser_ID.user_name+"@"+commentNode.discuss_root.dUser_ID.user_name
        holder.userComment2.text =commentNode.discuss_Values
        holder.userCommentTime2.text=commentNode.discuss_CreateTime.toString()
        holder.itemViewss.setOnClickListener {
            showBottomSheetDialog(this.activity,"回复@${commentNode.dUser_ID.user_name}")
        }

    }

    override fun getItemCount() = commentNodeList.size

    private fun showBottomSheetDialog(context: Context, callContentInfo: String){
        val bottomSheetDialog: BottomSheetDialog = BottomSheetDialog(context)
        val dialogView: View= LayoutInflater.from(context).inflate(R.layout.dialog_bottom_sheet, null)
        val callContent: EditText = dialogView.findViewById(R.id.callContent)
        val callContentButton: TextView = dialogView.findViewById(R.id.callContentButton)
        callContent?.hint = callContentInfo
        callContentButton?.setOnClickListener {
            Toast.makeText(context,"回复成功成功:${callContent.text}",Toast.LENGTH_SHORT).show()
            bottomSheetDialog.dismiss()
        }
        if (dialogView != null) {
            bottomSheetDialog.setContentView(dialogView)
        }
        bottomSheetDialog.show()
    }
}

