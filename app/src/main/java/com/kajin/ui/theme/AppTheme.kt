package com.kajin.ui.theme

import android.app.Activity
import androidx.compose.foundation.Indication
import androidx.compose.foundation.IndicationInstance
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.unit.Density
import androidx.core.view.WindowCompat

/**
 * 亮色模式颜色配置
 */
private val LightColorPalette = AppColor(
    statusBarBg = Color.White,
    statusBarTextBg = Color.Black,
    backgroundColor = Color.White,
    navigationBarContainerColor = Color.White,
    navigationBarIndicatorColor = Color.White,
    navigationBarTextSelectedColor = Color.Black,
    navigationBarTextUnSelectedColor = Color.Gray
)

/**
 * 暗色模式颜色配置
 */
private val DarkColorPalette = AppColor(
    statusBarBg = Color.Black,
    statusBarTextBg = Color.White,
    backgroundColor = Color.White,
    navigationBarContainerColor = Color.Black,
    navigationBarIndicatorColor = Color.Black,
    navigationBarTextSelectedColor = Color.White,
    navigationBarTextUnSelectedColor = Color.Gray,
)

private val LocalAppColors = compositionLocalOf {
    LightColorPalette
}

object AppTheme {
    val colors: AppColor @Composable get() = LocalAppColors.current
}

/**
 * 主题配置
 */
@Composable
fun AppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val targetColor = if (darkTheme) DarkColorPalette else LightColorPalette
    val view = LocalView.current


    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = targetColor.statusBarBg.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
        }
    }

    val fontScale = LocalDensity.current.fontScale
    val displayMetrics = LocalContext.current.resources.displayMetrics
    val widthPixels = displayMetrics.widthPixels

    CompositionLocalProvider(
        LocalAppColors provides targetColor,
        LocalDensity provides Density(density = widthPixels / 375f, fontScale = fontScale),
        // 去除点击的水波纹效果
        LocalIndication provides NoIndication
    ) {
        content()
    }

}

// null indication
object NoIndication : Indication {
    private object NoIndicationInstance : IndicationInstance {
        override fun ContentDrawScope.drawIndication() {
            drawContent()
        }
    }

    @Composable
    override fun rememberUpdatedInstance(interactionSource: InteractionSource): IndicationInstance {
        return NoIndicationInstance
    }
}