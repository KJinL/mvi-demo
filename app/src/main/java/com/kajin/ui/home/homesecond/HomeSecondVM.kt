package com.kajin.ui.home.homesecond

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.kajin.common.network.ApiResponse

class HomeSecondVM : ViewModel() {
    val repository = HomeRepository()

    // 创建 MutableState 对象
    private val _pointInfoState: MutableState<String> = mutableStateOf("请求中")

    // 将 MutableState 对象公开为 State 对象
    val pointInfoState: MutableState<String>
        get() = _pointInfoState

    suspend fun getPointInfo() {
        when (val callData = repository.getPointInfo()) {
            is ApiResponse.BusinessError -> {
                Log.e(
                    "HomeRepository",
                    "业务错误 ===> ${callData.response.msg}"
                )
                _pointInfoState.value = "业务错误${callData.response.msg}"
            }

            is ApiResponse.NetworkError -> {
                Log.e(
                    "HomeRepository",
                    "网络错误 ===> ${callData.errMsg}"
                )
                _pointInfoState.value = "网络错误${callData.errMsg}"

            }

            is ApiResponse.Success -> {
                val response = callData.response
                Log.e("HomeRepository", "请求成功 ===> ${response.toString()}")
                _pointInfoState.value = "请求成功${response}"
            }
        }

    }
}