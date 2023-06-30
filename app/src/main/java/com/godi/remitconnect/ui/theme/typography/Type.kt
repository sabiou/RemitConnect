package com.godi.remitconnect.ui.theme.typography

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.godi.remitconnect.R

// Set of Material typography styles to start with

// Font family
private val Outfit = FontFamily(
    Font(R.font.outfit_light),
    Font(R.font.outfit_medium, FontWeight.W500),
    Font(R.font.outfit_bold, FontWeight.Bold)
)

data class AppTypography(

    val bigTitle: TextStyle = TextStyle(
        fontFamily = Outfit,
        fontWeight = FontWeight.Bold,
        fontSize = 48.sp
    ),

    val h1: TextStyle = TextStyle(
        fontFamily = Outfit,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp,
    ),

    val h2: TextStyle = TextStyle(
        fontFamily = Outfit,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp
    ),

    val subtitle: TextStyle = TextStyle(
        fontFamily = Outfit,
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp
    ),

    val body: TextStyle = TextStyle(
        fontFamily = Outfit,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 24.sp
    ),

    val button: TextStyle = TextStyle(
        fontFamily = Outfit,
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp
    ),

    val caption: TextStyle = TextStyle(
        fontFamily = Outfit,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    ),

    val overline: TextStyle = TextStyle(
        fontFamily = Outfit,
        fontWeight = FontWeight.SemiBold,
        fontSize = 10.sp,
        letterSpacing = 1.25.sp
    )

)

internal val LocalTypography = staticCompositionLocalOf { AppTypography() }