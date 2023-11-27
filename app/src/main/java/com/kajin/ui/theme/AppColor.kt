package com.kajin.ui.theme

import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color

@Stable
class AppColor(
    statusBarBg: Color = Color.White,
    statusBarTextBg: Color = Color.White,
    navigationBarContainerColor: Color = Color.White,
    backgroundColor: Color = Color.White,
    navigationBarIndicatorColor: Color = Color.Black,
    navigationBarTextSelectedColor: Color = Color.Gray,
    navigationBarTextUnSelectedColor: Color = Color.Gray,

) {
    var statusBarTextBg: Color by mutableStateOf(statusBarTextBg)
        private set
    var statusBarBg: Color by mutableStateOf(statusBarBg)
        private set
    var backgroundColor: Color by mutableStateOf(backgroundColor)
        private set
    var navigationBarContainerColor: Color by mutableStateOf(navigationBarContainerColor)
        private set
    var navigationBarTextSelectedColor: Color by mutableStateOf(navigationBarTextSelectedColor)
        private set
    var navigationBarIndicatorSelectColor: Color by mutableStateOf(navigationBarIndicatorColor)
        private set
    var navigationBarTextUnSelectedColor: Color by mutableStateOf(navigationBarTextUnSelectedColor)
        private set
}