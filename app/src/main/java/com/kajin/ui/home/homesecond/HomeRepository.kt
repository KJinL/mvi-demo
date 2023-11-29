package com.kajin.ui.home.homesecond

import com.kajin.common.network.ApiResponse
import com.kajin.common.network.Network
import com.kajin.common.network.apis.PointApi
import com.kajin.model.response.BaseResponse
import com.kajin.model.response.PointRes
import javax.inject.Inject

class HomeRepository @Inject constructor(){
    private val pointApi by lazy {
        PointApi.instance()
    }

    suspend fun getPointInfo(): ApiResponse<BaseResponse<PointRes>> {
        return Network.callData { pointApi.pointInfo() }
    }
}
