package com.hnb.huinongbang.ui.planting.doctormodule

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.hnb.huinongbang.logic.Repository
import com.hnb.huinongbang.logic.model.DoctorInformation
import com.hnb.huinongbang.logic.model.PlantsNews
import com.hnb.huinongbang.logic.model.PlantsNewsOfCategory

class DoctorViewModel : ViewModel() {
    //获取专家信息
    private val typeLiveData = MutableLiveData<Int>()
    var doctorInformationList = ArrayList<DoctorInformation>()


    val doctorInformationListLivepData =Transformations.switchMap(typeLiveData){Repository.doctorInformation()}

    fun getData(type: Int){
        typeLiveData.value = type
    }

}