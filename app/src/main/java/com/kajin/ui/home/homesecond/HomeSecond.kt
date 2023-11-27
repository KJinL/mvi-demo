package com.kajin.ui.home.homesecond

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.kajin.ui.components.TitleBar
import com.kajin.ui.components.TitleBarClickEvent


@Composable
fun HomeSecond(navController: NavController, vm: HomeSecondVM = viewModel()) {
    LaunchedEffect(Unit) {
        vm.getPointInfo()
    }
    TitleBar(title = "Home2", onClick = {
        when (it) {
            TitleBarClickEvent.LeftClick -> {
                navController.popBackStack()
            }

            TitleBarClickEvent.TitleClick -> {}
        }
    })
    Text(
        text = "Home2",
        modifier = Modifier.fillMaxSize(),
        textAlign = TextAlign.Center
    )
}