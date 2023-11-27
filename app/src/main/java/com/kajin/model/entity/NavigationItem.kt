package com.kajin.model.entity

import android.graphics.drawable.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector

/**
 * 首页底部导航栏item对象
 */
data class NavigationItem(
    /**
     * 标题
     */
    val title: String,
    /**
     * 选中的icon
     */
    val selectedIcon: Int,
    /**
     *  未选中的icon
     */
    val unselectedIcon: Int,
    /**
     * 是否有新消息
     */
    val hasNews: Boolean = false

)
