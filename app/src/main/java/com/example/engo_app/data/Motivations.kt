package com.example.engo_app.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.engo_app.R

data class Motivation(
    @DrawableRes val motivationIconResourceId: Int,
    @StringRes val motivationNameId: Int,
)

val motivations = listOf(
    Motivation(R.drawable.icon_1, R.string.study),
    Motivation(R.drawable.icon_2, R.string.work),
    Motivation(R.drawable.icon_3, R.string.travel),
    Motivation(R.drawable.icon_4, R.string.living_broad),
    Motivation(R.drawable.icon_5, R.string.personal_development),
    Motivation(R.drawable.icon_6, R.string.friends),
    Motivation(R.drawable.icon_7, R.string.relationship)
)
