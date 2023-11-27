package com.kajin.common.extensions

import androidx.compose.foundation.clickable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import kotlinx.coroutines.delay

/**
 * modifier点击事件防抖拓展函数
 * @param enabled 是否开启防抖
 * @param delay 防抖事件
 * @param onClick 点击事件
 */
fun Modifier.clickableWithDebounce(
    enabled: Boolean = true,
    delay: Long = 300,
    onClick: () -> Unit
) =
    composed {
        var clicked by remember {
            mutableStateOf(!enabled)
        }
        LaunchedEffect(key1 = clicked, block = {
            if (clicked) {
                delay(delay)
                clicked = !clicked
            }
        })

        Modifier.clickable(if (enabled) !clicked else false) {
            clicked = !clicked
            onClick()
        }
    }
