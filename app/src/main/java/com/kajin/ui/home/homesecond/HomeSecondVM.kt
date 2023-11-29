package com.kajin.ui.home.homesecond

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kajin.common.base.Container
import com.kajin.common.extensions.containers
import com.kajin.common.network.ApiResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeSecondVM @Inject constructor(
    private val repository: HomeRepository,
) : ViewModel() {

    /**
     * 创建状态容器
     */
    private val _containerState by containers<HomeSecondDataState, HomeSecondSingleEvent>(
        HomeSecondDataState.Success("")
    )
    val containerState: Container<HomeSecondDataState, HomeSecondSingleEvent> = _containerState

    /**
     * 事件分发
     */
    fun sendAction(action: HomeSecondAction) {
        when (action) {
            HomeSecondAction.GetPointInfo -> getPointInfo()
        }
    }

    private fun getPointInfo() {
        viewModelScope.launch {
            _containerState.sendEvent(HomeSecondSingleEvent.Loading(true))
            when (val callData = repository.getPointInfo()) {
                is ApiResponse.BusinessError -> {
                    _containerState.updateState {
                        HomeSecondDataState.BusinessError("业务错误 ===> ${callData.response.msg}")
                    }
                }

                is ApiResponse.NetworkError -> {
                    _containerState.updateState {
                        HomeSecondDataState.NetworkError("网络错误 ===> ")
                    }
                }

                is ApiResponse.Success -> {
                    _containerState.updateState {
                        HomeSecondDataState.Success("请求成功 ===> ${callData.response.data}")
                    }
                }
            }
            _containerState.sendEvent(HomeSecondSingleEvent.Loading(false))
        }

    }

    fun sendTips(s: String) {
        viewModelScope.launch {
            _containerState.sendEvent(HomeSecondSingleEvent.Tips(s))
        }
    }
}