package com.example.engo_app.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.engo_app.R

val InterBold = FontFamily(
    Font(R.font.inter_bold)
)
val Inter = FontFamily(
    Font(R.font.inter)
)

val Nunito = FontFamily(
    Font(R.font.nunito_regular)
)

val NunitoBold = FontFamily(
    Font(R.font.nunito_bold)
)

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ) ,

    displayLarge = TextStyle(
        fontFamily = NunitoBold,
        fontWeight = FontWeight.W900,
        fontSize = 32.sp
    ),
    displayMedium = TextStyle(
        fontFamily = NunitoBold,
        fontWeight = FontWeight.Bold,
        fontSize = 30.sp
    ),
    labelMedium = TextStyle(
        fontFamily = NunitoBold,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp
    ),
    displaySmall = TextStyle(
        fontFamily = NunitoBold,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp
    )
)