package com.kajin.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kajin.common.navigation.Destinations
import com.kajin.ui.MainFrame
import com.kajin.ui.home.homesecond.HomeSecond

@Composable
fun NaviHostApp() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Destinations.MainScreen.route) {
        composable(Destinations.MainScreen.route) {
            MainFrame(navController)
        }
        composable(Destinations.Home2.route) {
            HomeSecond(navController)
        }
    }
}

@Preview
@Composable
fun NaviHostAppPreview() {
    NaviHostApp()
}