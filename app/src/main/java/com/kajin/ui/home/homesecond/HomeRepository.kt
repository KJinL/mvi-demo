package com.kajin.ui.home.homesecond

import com.kajin.common.network.ApiResponse
import com.kajin.common.network.Network
import com.kajin.common.network.PointService
import com.kajin.model.response.BaseResponse
import com.kajin.model.response.PointRes

class HomeRepository {
    private val pointService by lazy {
        PointService.instance()
    }

    suspend fun getPointInfo(): ApiResponse<BaseResponse<PointRes>> {
        return Network.callData { pointService.pointInfo() }
    }
}
