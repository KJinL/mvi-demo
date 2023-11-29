package com.kajin.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.repeatOnLifecycle
import com.kajin.common.base.IUiSingleEvent
import kotlinx.coroutines.flow.Flow

/**
 * 用于监听一次性事件变化的助手函数
 */
@Composable
fun <T : IUiSingleEvent> ObserveSingleEvent(
    singleEventFlow: Flow<T>,
    lifecycleOwner: LifecycleOwner,
    lifecycleState: Lifecycle.State = Lifecycle.State.STARTED,
    action: (T) -> Unit
) {
    LaunchedEffect(lifecycleOwner, singleEventFlow) {
        lifecycleOwner.repeatOnLifecycle(lifecycleState) {
            singleEventFlow.collect {
                action(it)
            }
        }
    }
}
