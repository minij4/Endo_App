package com.example.engo_app.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.engo_app.R

data class Language(
    @DrawableRes val languageIconResourceId: Int,
    @StringRes val languageNameId: Int,
)
val learningLanguage = Language(R.drawable.gb, R.string.english)
val languages = listOf(
    Language(R.drawable.gb, R.string.english),
    Language(R.drawable.fr, R.string.french),
    Language(R.drawable.de, R.string.german),
    Language(R.drawable.es, R.string.spanish),
    Language(R.drawable.lt, R.string.lithuanian),
    Language(R.drawable.lv, R.string.latvian),
    Language(R.drawable.ua, R.string.ukrainian)
)
