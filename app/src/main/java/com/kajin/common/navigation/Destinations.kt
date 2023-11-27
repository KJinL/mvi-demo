package com.kajin.common.navigation

sealed class Destinations(val route: String) {
    // 首页
    object MainScreen : Destinations("MainScreen")
    // home2页面
    object Home2 : Destinations("Home2")
    // Mine页面
    object Mine : Destinations("Mine")
    // Home页面
    object Home : Destinations("Home")


}
