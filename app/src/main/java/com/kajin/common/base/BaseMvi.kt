package com.kajin.common.base

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * 重复事件,可以重复消费
 */
interface IUiState

/**
 * 一次性事件,只能消费一次
 */
interface IUiSingleEvent{

}


/**
 * 状态容器
 */
interface Container<STATE : IUiState, SINGLE_EVENT : IUiSingleEvent> {
    /**
     * ui状态流
     */
    val uiState: StateFlow<STATE>

    /**
     * 单次事件流
     */
    val singleEvent: SharedFlow<SINGLE_EVENT>
}


/**
 * 定义容器实现类，让子类实现两个方法
 */
interface MutableContainer<STATE : IUiState, SINGLE_EVENT : IUiSingleEvent> :
    Container<STATE, SINGLE_EVENT> {

    /*
     * 更新状态
     */
    fun updateState(action: STATE.() -> STATE)

    /**
     * 发送事件
     */
    fun sendEvent(event: SINGLE_EVENT)
}

/**
 * 容器实现
 */
class ContainerImpl<STATE : IUiState, SINGLE_EVENT : IUiSingleEvent>(
    initialState: STATE,
    private val parentScope: CoroutineScope,
) : MutableContainer<STATE, SINGLE_EVENT> {

    private val _state = MutableStateFlow(initialState)

    private val _singleEvent = MutableSharedFlow<SINGLE_EVENT>()

    override val uiState: StateFlow<STATE> = _state

    override val singleEvent: SharedFlow<SINGLE_EVENT> = _singleEvent.asSharedFlow()

    override fun updateState(action: STATE.() -> STATE) {
        _state.update { action(_state.value) }
    }

    override fun sendEvent(event: SINGLE_EVENT) {
        parentScope.launch {
            _singleEvent.emit(event)
        }
    }
}

