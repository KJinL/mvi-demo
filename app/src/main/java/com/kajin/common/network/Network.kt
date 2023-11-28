package com.kajin.common.network

import com.kajin.common.constants.Constants
import com.kajin.common.constants.ResultCodeEnum
import com.kajin.model.response.BaseResponse
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit


sealed class ApiResponse<out T> {
    data class Success<out T>(val response: T) : ApiResponse<T>()
    data class BusinessError<out T>(val response: T) : ApiResponse<T>()
    data class NetworkError(val errMsg: String) : ApiResponse<Nothing>()
}

object Network {
    private val retrofit: Retrofit

    init {
        OkHttpClient.Builder().addInterceptor(RequestInterceptor())
            .addInterceptor(ResponseInterceptor()).connectTimeout(5, TimeUnit.SECONDS).build()
            .apply {
                retrofit =
                    Retrofit.Builder().baseUrl(Constants.Network.BASE_URL).addConverterFactory(
                        MoshiConverterFactory.create(
                            Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
                        )
                    ).client(this).build()
            }
    }

    suspend fun <T> callData(apiCall: suspend () -> BaseResponse<T>): ApiResponse<BaseResponse<T>> {
        try {
            val response = apiCall.invoke()
            if (response.code != ResultCodeEnum.SUCCESS.code) {
                return ApiResponse.BusinessError(response)
            } else {
                return ApiResponse.Success(response)
            }
        } catch (e: Exception) {
            return ApiResponse.NetworkError("网络连接错误,请检查网络...")
        }
    }

    fun <T> createService(clazz: Class<T>): T {
        return retrofit.create(clazz)
    }
}

/**
 * 请求拦截器
 */
class RequestInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest: Request = chain.request()
        return chain.proceed(originalRequest)
    }

}

/**
 * 响应拦截器
 */
class ResponseInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalResponse: Response = chain.proceed(chain.request())
        return originalResponse
    }
}