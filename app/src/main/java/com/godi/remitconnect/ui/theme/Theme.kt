package com.godi.remitconnect.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import com.godi.remitconnect.ui.theme.CustomDesignSystemTheme.CustomThemeColors
import com.godi.remitconnect.ui.theme.CustomDesignSystemTheme.LocalCustomThemeColors
import com.godi.remitconnect.ui.theme.typography.typography

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
    val colors = LightColorScheme

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = Color.White.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }
    MaterialTheme(
        colorScheme = colors,
        typography = typography,
        content = content
    )
}

object CustomDesignSystemTheme {
    /**
     * Custom theme colors used in the RemitConnect app.
     */
    @Immutable
    data class CustomThemeColors(
        val midnightBlue: Color,
        val paleMint: Color,
        val mintGreen: Color,
        val babyBlue: Color,
        val slateGray: Color,
        val duskGray: Color,
        val ceruleanBlue: Color,
        val silverGrey: Color,
        val emeraldGreen: Color,
        val forestGreen: Color,
        val slateBlue: Color,
        val silverGray: Color,
    )

    /**
     * Default instance of the custom theme colors.
     */
    val customThemeColors = CustomThemeColors(
        midnightBlue = Color(0xFF00122C),
        paleMint = Color(0xFFEDF8F5),
        mintGreen = Color(0xFFC8EAE1),
        babyBlue = Color(0xFFEAF6FC),
        slateGray = Color(0xFF7F8895),
        duskGray = Color(0xFF7F8895),
        ceruleanBlue = Color(0xFF1B98E0),
        silverGrey = Color(0xFFF2F3F4),
        emeraldGreen = Color(0xFF00A85A),
        forestGreen = Color(0xFF055C00),
        slateBlue = Color(0xFF404D61),
        silverGray = Color(0xFFBFC3CA),
    )

    /**
     * Local provider key for accessing the custom theme colors.
     */
    val LocalCustomThemeColors = staticCompositionLocalOf {
        customThemeColors
    }
}

/**
 * Composable function for applying the custom theme colors to the content.
 *
 * @param content The content to apply the custom theme to.
 */
@Composable
fun CustomTheme(
    content: @Composable () -> Unit
) {
    val customThemeColors = CustomDesignSystemTheme.customThemeColors

    CompositionLocalProvider(LocalCustomThemeColors provides customThemeColors) {
        MaterialTheme(
            content = content
        )
    }
}

/**
 * Provides access to the custom theme colors from the current composition.
 *
 * Usage: `val customThemeColors = CustomTheme.colors`
 */
object CustomTheme {
    val colors: CustomThemeColors
        @Composable
        get() = LocalCustomThemeColors.current
}