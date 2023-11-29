package com.kajin.common.extensions

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kajin.common.base.ContainerImpl
import com.kajin.common.base.IUiSingleEvent
import com.kajin.common.base.IUiState
import com.kajin.common.base.MutableContainer
import kotlinx.coroutines.CoroutineScope

fun <STATE : IUiState, SINGLE_EVENT : IUiSingleEvent> ViewModel.containers(
    initState: STATE
): Lazy<MutableContainer<STATE, SINGLE_EVENT>> {
    return ContainerLazy(initState, viewModelScope)
}

class ContainerLazy<STATE : IUiState, SINGLE_EVENT : IUiSingleEvent>(
    initState: STATE,
    viewModelScope: CoroutineScope
) :
    Lazy<MutableContainer<STATE, SINGLE_EVENT>> {

    private var cached: MutableContainer<STATE, SINGLE_EVENT>? = null

    override val value: MutableContainer<STATE, SINGLE_EVENT> = cached
        ?: ContainerImpl<STATE, SINGLE_EVENT>(initState, viewModelScope).also {
            cached = it
        }

    override fun isInitialized() = cached != null

}
