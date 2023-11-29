package com.kajin.ui.home.homesecond

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.kajin.common.extensions.clickableWithDebounce
import com.kajin.ui.components.ObserveSingleEvent
import com.kajin.ui.components.TitleBar
import com.kajin.ui.components.TitleBarClickEvent

@Composable
fun HomeSecond(navController: NavController) {
    val vm: HomeSecondVM = hiltViewModel()
    LaunchedEffect(Unit) {
        vm.sendAction(HomeSecondAction.GetPointInfo)
    }
    val context = LocalContext.current

    val uiState by vm.containerState.uiState.collectAsState()

    ObserveSingleEvent(vm.containerState.singleEvent, LocalLifecycleOwner.current) {
        when (it) {
            is HomeSecondSingleEvent.Loading -> {
                if (it.isShowLoading) {
                    Toast.makeText(context, "加载中...", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, "请求完成...", Toast.LENGTH_SHORT).show()
                }
            }

            is HomeSecondSingleEvent.Tips -> {
                Toast.makeText(context, "提示 ${it.message}", Toast.LENGTH_SHORT).show()
            }
        }
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
        // 根据请求状态显示页面
        when (val currentState = uiState) {
            is HomeSecondDataState.BusinessError -> {
                BusinessErrorView(text = currentState.msg) {
                    vm.sendTips("click")
                }
            }

            is HomeSecondDataState.NetworkError -> {
                NetworkErrorView(text = currentState.msg) {
                    vm.sendTips("click")
                }
            }

            is HomeSecondDataState.Success -> {
                SuccessInfo(text = currentState.pointInfo) {
                    vm.sendTips("click")
                }

            }
        }

    }
}

@Composable
fun NetworkErrorView(text: String, onclick: () -> Unit) {
    Text(
        text = text,
        modifier = Modifier
            .fillMaxSize()
            .clickableWithDebounce { onclick() },
        textAlign = TextAlign.Center
    )
}

@Composable
fun BusinessErrorView(text: String, onclick: () -> Unit) {
    Text(
        text = text,
        modifier = Modifier
            .fillMaxSize()
            .clickableWithDebounce { onclick() },
        textAlign = TextAlign.Center
    )
}

@Composable
fun SuccessInfo(text: String, onclick: () -> Unit) {
    Text(
        text = text,
        modifier = Modifier
            .fillMaxSize()
            .clickableWithDebounce { onclick() },
        textAlign = TextAlign.Center
    )
}