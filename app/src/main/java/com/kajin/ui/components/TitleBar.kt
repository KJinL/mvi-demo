package com.kajin.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kajin.R
import com.kajin.common.extensions.clickableWithDebounce

/**
 * 默认标题栏点击事件
 */
sealed class TitleBarClickEvent {
    /**
     * 左边按钮的点击
     */
    object LeftClick : TitleBarClickEvent()

    /**
     * 标题的点击
     */
    object TitleClick : TitleBarClickEvent()
}


/**
 * 标题栏控件
 * @param title 标题
 * @param isPaddingStatusBr 是否设置状态栏padding
 * @param isShowLeftButton 是否心事左边按钮
 * @param leftButton 自定义左边按钮,如果传入此参数，需要自己实现点击事件
 * @param rightButton 自定义右边按钮，如果传入此参数需要自己实现点击事件
 * @param onClick 点击事件
 * @param content 完全自定义状态栏
 */
@Composable
fun DefaultTitleBar(
    title: String = "",
    isShowLeftButton: Boolean = true,
    isPaddingStatus: Boolean = true,
    leftButton: @Composable (() -> Unit)? = null,
    rightButton: @Composable (() -> Unit)? = null,
    onClick: (TitleBarClickEvent) -> Unit = {},
    content: @Composable (() -> Unit)? = null
) {

    Box(modifier = Modifier.fillMaxWidth()
        .let { if (isPaddingStatus) it.systemBarsPadding() else it }) {
        // 如果有content则直接使用content
        if (content != null) {
            content()
        } else {
            Box(modifier = Modifier
                .fillMaxWidth()
                .let { if (isPaddingStatus) it.systemBarsPadding() else it }
                .height(52.dp)) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .height(52.dp)
                        .fillMaxWidth()
                ) {
                    Text(
                        text = title,
                        style = TextStyle(fontSize = 22.sp, fontWeight = FontWeight.Bold),
                        modifier = Modifier.clickableWithDebounce { onClick(TitleBarClickEvent.TitleClick) },
                        textAlign = TextAlign.Center
                    )
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .height(52.dp)
                        .fillMaxWidth()
                ) {
                    if (isShowLeftButton) {
                        Box(contentAlignment = Alignment.Center, modifier = Modifier.size(50.dp)) {
                            leftButton?.invoke() ?: run {
                                // 如果 leftButton 为 null，创建默认按钮
                                Image(painter = painterResource(id = R.mipmap.ic_back_arrow),
                                    contentDescription = "返回",
                                    modifier = Modifier
                                        .clickableWithDebounce { onClick(TitleBarClickEvent.LeftClick) }
                                        .width(9.71.dp)
                                        .height(16.98.dp))
                            }
                        }
                    }

                    Box(contentAlignment = Alignment.Center) {
                        rightButton?.let {
                            it()
                        }
                    }
                }
            }
//    Box(modifier = Modifier
//        .fillMaxWidth()
//        .let { if (isPaddingStatus) it.systemBarsPadding() else it }) {
//        // 如果有content则直接使用content
//        if (content != null) {
//            content()
//        } else {
//
//            Row(
//                verticalAlignment = Alignment.CenterVertically,
//                horizontalArrangement = Arrangement.SpaceBetween,
//                modifier = Modifier
//                    .height(52.dp)
//                    .fillMaxWidth()
//            ) {
//                if (isShowLeftButton) {
//                    Box(modifier = Modifier.size(50.dp), contentAlignment = Alignment.Center) {
//                        leftButton?.invoke() ?: run {
//                            // 如果 leftButton 为 null，创建默认按钮
//                            Image(
//                                painter = painterResource(id = R.mipmap.ic_back_arrow),
//                                contentDescription = "返回",
//                                modifier = Modifier
//                                    .clickable { onClick(TitleBarClickEvent.LeftClick) }
//                                    .width(9.71.dp)
//                                    .height(16.98.dp)
//                            )
//                        }
//                    }
//                }
//                Text(
//                    text = title,
//                    style = TextStyle(fontSize = 22.sp, fontWeight = FontWeight.Bold),
//                    modifier = Modifier
//                        .weight(1f)
//                        .clickable { onClick(TitleBarClickEvent.TitleClick) },
//                    textAlign = TextAlign.Center
//                )
//
//                Box(modifier = Modifier.size(50.dp), contentAlignment = Alignment.Center) {
//                    rightButton?.let {
//                        it()
//                    }
//                }
//            }
//
//
//        }
//    }
        }
    }
}


/**
 * 标题栏控件
 * @param title 标题
 * @param isPaddingStatusBr 是否设置状态栏padding
 * @param isShowLeftButton 是否心事左边按钮
 * @param leftButton 自定义左边按钮,如果传入此参数，需要自己实现点击事件
 * @param rightButton 自定义右边按钮，如果传入此参数需要自己实现点击事件
 * @param onClick 点击事件
 */
@Composable
fun TitleBar(
    title: String = "标题",
    isShowLeftButton: Boolean = true,
    isPaddingStatusBr: Boolean = true,
    leftButton: @Composable (() -> Unit)? = null,
    rightButton: @Composable (() -> Unit)? = null,
    onClick: (TitleBarClickEvent) -> Unit = {},
) {
    DefaultTitleBar(
        title, isPaddingStatusBr, isShowLeftButton, leftButton, rightButton, onClick
    )
}

/**
 * 自定义标题栏控件
 * @param isPaddingStatus 是否增加状态栏padding
 * @param content 自定义控件
 */
@Composable
fun TitleBar(
    isPaddingStatus: Boolean = true, content: @Composable () -> Unit
) {
    DefaultTitleBar(isPaddingStatus = isPaddingStatus) {
        content()
    }
}


@Preview
@Composable
fun TitleBarPreview() {
    DefaultTitleBar("这是标题")
}