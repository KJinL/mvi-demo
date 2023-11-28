package com.kajin.common.network

import com.kajin.model.response.BaseResponse
import com.kajin.model.response.PointRes
import retrofit2.http.GET

interface PointService {
    @GET("no-auth/point/info")
    suspend fun pointInfo(): BaseResponse<PointRes>

    companion object {
        fun instance(): PointService {
            return Network.createService(PointService::class.java)
        }
    }
}