package com.hnb.huinongbang.ui.planting.doctormodule

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.hnb.huinongbang.logic.Repository
import com.hnb.huinongbang.logic.model.*
import com.hnb.huinongbang.util.LogUtil

class DoctorViewModel : ViewModel() {
    //获取专家信息
    private val typeLiveData = MutableLiveData<Int>()
    var doctorInformationList = ArrayList<DoctorInformation>()
    val doctorInformationListLivepData =Transformations.switchMap(typeLiveData){Repository.doctorInformation()}

    fun getData(type: Int){
        typeLiveData.value = type
    }


    //获取专家评论链表
    lateinit var doctorCommentsList:DoctorComments
    private val idLiveData = MutableLiveData<Int>()
    val doctorCommentsListLivepData =Transformations.switchMap(idLiveData){
            id->Repository.doctorComments(id)

    }
    fun getComments(id: Int){
        idLiveData.value = id
    }

    //评论回复功能
    private val doctorCommentBackDataLiveData = MutableLiveData<DoctorCommentBack>()
    val doctorCommentBackLiveData = Transformations.switchMap(doctorCommentBackDataLiveData){doctorCommentBack ->
        Repository.doctorCommentBack(doctorCommentBack)
    }
    //外部调用函数
    fun doctorCommentBack(doctorCommentBack: DoctorCommentBack){
        doctorCommentBackDataLiveData.value =doctorCommentBack
    }

}