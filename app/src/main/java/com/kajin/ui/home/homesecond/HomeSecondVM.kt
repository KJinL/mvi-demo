package com.kajin.ui.home.homesecond

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.kajin.common.network.PointService
import com.kajin.model.response.PointRes

class HomeSecondVM : ViewModel() {
    val pointService = PointService.instance()

    var pointInfo by mutableStateOf(PointRes())
        private set

    suspend fun getPointInfo() {
            val pointInfoRes = pointService.pointInfo()
            if (pointInfoRes.code == 200) {
                pointInfo = pointInfoRes.data
                Log.e("error ===> " , pointInfo.toString())
            }
    }
}