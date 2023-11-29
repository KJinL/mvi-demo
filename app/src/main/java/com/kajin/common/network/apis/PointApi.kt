package com.kajin.common.network.apis

import com.kajin.common.network.Network
import com.kajin.model.response.BaseResponse
import com.kajin.model.response.PointRes
import retrofit2.http.GET

interface PointApi {
    @GET("no-auth/point/info")
    suspend fun pointInfo(): BaseResponse<PointRes>

    companion object {
        fun instance(): PointApi {
            return Network.createService(PointApi::class.java)
        }
    }
}