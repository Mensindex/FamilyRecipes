package com.example.familyrecipes.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.familyrecipes.R

// Set of Material typography styles to start with
private val Comfortaa = FontFamily(
    Font(R.font.comfortaa_regular, FontWeight.Normal),
    Font(R.font.comfortaa_semi_bold, FontWeight.SemiBold),
    Font(R.font.comfortaa_bold, FontWeight.Bold),
)

val Typography = Typography(
    headlineLarge = TextStyle(
        platformStyle = PlatformTextStyle(),
        fontFamily = Comfortaa,
        fontSize = 28.sp,
        fontWeight = FontWeight.Normal
    ),
    titleLarge = TextStyle(
        fontFamily = Comfortaa,
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold,
    ),
    titleMedium = TextStyle(
        fontFamily = Comfortaa,
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold,
    ),
    labelLarge = TextStyle(
        fontFamily = Comfortaa,
        fontSize = 16.sp,
        fontWeight = FontWeight.Normal,
    ),
    labelMedium = TextStyle(
        fontFamily = Comfortaa,
        fontSize = 14.sp,
        fontWeight = FontWeight.Normal,
    ),
    labelSmall = TextStyle(
        fontFamily = Comfortaa,
        fontSize = 12.sp,
        fontWeight = FontWeight.Normal,
    ),
    bodyLarge = TextStyle(
        fontFamily = Comfortaa,
        fontSize = 16.sp,
        fontWeight = FontWeight.Normal,
    ),
    bodyMedium = TextStyle(
        fontFamily = Comfortaa,
        fontSize = 14.sp,
        fontWeight = FontWeight.Normal,
    )
)