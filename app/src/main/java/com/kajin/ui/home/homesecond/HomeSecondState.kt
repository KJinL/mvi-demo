package com.kajin.ui.home.homesecond

import com.kajin.common.base.IUiSingleEvent
import com.kajin.common.base.IUiState

sealed class HomeSecondDataState : IUiState {
    data class Success(val pointInfo: String) : HomeSecondDataState()
    data class NetworkError(val msg: String) : HomeSecondDataState()
    data class BusinessError(val msg: String) : HomeSecondDataState()
}

sealed class HomeSecondSingleEvent : IUiSingleEvent {
    data class Loading(val isShowLoading: Boolean) : HomeSecondSingleEvent()
    data class Tips(val message: String) : HomeSecondSingleEvent()
}


sealed class HomeSecondAction {
    object GetPointInfo : HomeSecondAction()
}