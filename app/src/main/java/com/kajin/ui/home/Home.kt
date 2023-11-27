package com.kajin.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.kajin.common.extensions.clickableWithDebounce
import com.kajin.common.navigation.Destinations
import com.kajin.ui.components.StatusBarSpacer

@Composable
fun HomeScreen(navController: NavController) {
    Column {
        StatusBarSpacer()
        Text(
            text = "Home",
            modifier = Modifier
                .fillMaxSize()
                .clickableWithDebounce { navController.navigate(Destinations.Home2.route) },
            textAlign = TextAlign.Center
        )
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen(rememberNavController())
}