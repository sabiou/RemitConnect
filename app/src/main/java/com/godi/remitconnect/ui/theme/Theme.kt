package com.godi.remitconnect.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import com.godi.remitconnect.ui.theme.typography.typography

private val DarkColorScheme = darkColorScheme(
    primary = Primary100,
    secondary = Gray100,
    tertiary = Accent100,
    background = Gray00,
    onPrimary = Color.White,
)

private val LightColorScheme = lightColorScheme(
    primary = Primary100,
    primaryContainer = Primary70,
    secondary = Primary06,
    tertiary = Accent100,
    background = Gray00,
    onPrimary = Color.White,
    surfaceVariant = Primary05,
    surface = Primary06
)

@Composable
fun RemitConnectTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) DarkColorScheme else LightColorScheme

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colors.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colors,
        typography = typography,
        content = content
    )
}