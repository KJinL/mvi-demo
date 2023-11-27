package com.kajin.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.kajin.R
import com.kajin.model.entity.NavigationItem
import com.kajin.ui.home.HomeScreen
import com.kajin.ui.mine.MineScreen
import com.kajin.ui.theme.AppTheme


@Composable
fun MainFrame(navController: NavController) {
    // 导航栏item
    val navigationItems = listOf(
        NavigationItem("首页", R.mipmap.ic_home_selected, R.mipmap.ic_home_unselected),
        NavigationItem("我的", R.mipmap.ic_mine_selected, R.mipmap.ic_mine_unselected)
    )
    // 当前选中的列
    var currentNavigationIndex by remember { mutableStateOf(0) }
    Scaffold(
        bottomBar = {
            NavigationBar(
                containerColor = AppTheme.colors.navigationBarContainerColor,
                modifier = Modifier.height(85.dp),
            ) {
                navigationItems.forEachIndexed { index, it ->
                    NavigationBarItem(
                        selected = currentNavigationIndex == index,
                        onClick = { currentNavigationIndex = index },
                        icon = {
                            Image(
                                painter = if (index == currentNavigationIndex) {
                                    painterResource(id = it.selectedIcon)
                                } else painterResource(it.unselectedIcon),
                                contentDescription = it.title,
                                modifier = Modifier.size(20.dp),
                            )
                        },
                        label = {
                            Text(text = it.title)
                        },
                        modifier = Modifier.background(AppTheme.colors.navigationBarContainerColor),
                        colors = NavigationBarItemDefaults.colors(
                            selectedTextColor = AppTheme.colors.navigationBarTextSelectedColor,
                            unselectedTextColor = AppTheme.colors.navigationBarTextUnSelectedColor,
                            indicatorColor = AppTheme.colors.navigationBarIndicatorSelectColor

                        ),
                    )
                }
            }
        },
    ) {
        Box(
            modifier = Modifier
                .padding(bottom = it.calculateBottomPadding())
                .fillMaxSize()
        ) {
            when (currentNavigationIndex) {
                0 -> HomeScreen(navController)
                1 -> MineScreen(navController)
            }
        }
    }
}

@Preview
@Composable
fun MainFramePreview() {
    MainFrame(rememberNavController())
}