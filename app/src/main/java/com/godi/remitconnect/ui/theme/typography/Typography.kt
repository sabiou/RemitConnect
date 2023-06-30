package com.godi.remitconnect.ui.theme.typography

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.godi.remitconnect.R

// Font family
private val Outfit = FontFamily(
    listOf(
        Font(R.font.outfit_light),
        Font(R.font.outfit_medium, FontWeight.W500),
        Font(R.font.outfit_bold, FontWeight.Bold)
    )
)

val typography = Typography(

    displayMedium = TextStyle(
        fontFamily = Outfit,
        fontWeight = FontWeight.W400,
        fontSize = 45.sp,
        lineHeight = 52.sp,
        letterSpacing = 0.sp,
    ),

    displaySmall = TextStyle(
        fontFamily = Outfit,
        fontWeight = FontWeight.W400,
        fontSize = 36.sp,
        lineHeight = 44.sp,
        letterSpacing = 0.sp,
    ),

    titleLarge = TextStyle(
        fontFamily = Outfit,
        fontWeight = FontWeight.Bold,
        fontSize = 48.sp
    ),

    titleSmall = TextStyle(
        fontFamily = Outfit,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.1.sp,
    ),

    labelLarge = TextStyle(
        fontFamily = Outfit,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.1.sp,
    ),

    labelMedium = TextStyle(
        fontFamily = Outfit,
        fontWeight = FontWeight.W500,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp,
    ),

    labelSmall = TextStyle(
        fontFamily = Outfit,
        fontWeight = FontWeight.W500,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp,
    ),


    bodyLarge = TextStyle(
        fontFamily = Outfit,
        fontWeight = FontWeight.W400,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp,
    ),

    bodyMedium = TextStyle(
        fontFamily = Outfit,
        fontWeight = FontWeight.W400,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.25.sp,
    ),

    bodySmall = TextStyle(
        fontFamily = Outfit,
        fontWeight = FontWeight.W400,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.4.sp,
    ),
)