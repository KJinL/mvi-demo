package com.kajin.ui.home.homesecond

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
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
    val resultState by remember {
        vm.pointInfoState
    }
    val context = LocalContext.current
    LaunchedEffect(resultState) {
        Toast.makeText(context, resultState, Toast.LENGTH_SHORT).show()
    }
    Column(modifier = Modifier.fillMaxSize()) {
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

}