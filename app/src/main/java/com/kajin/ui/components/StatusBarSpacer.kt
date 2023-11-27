package com.kajin.ui.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/**
 * 顶部导航栏pdding
 */
@Composable
fun StatusBarSpacer() {
    Spacer(modifier = Modifier.systemBarsPadding())
}