/*
 * Copyright (C) 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.engo_app.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.engo_app.R

/**
 * A data class to represent the information presented in the dog card
 */
data class Language(
    @DrawableRes val languageIconResourceId: Int,
    @StringRes val languageNameId: Int,
)
val learningLanguage = Language(R.drawable.gb, R.string.english)
val languages = listOf(
    Language(R.drawable.fr, R.string.french),
    Language(R.drawable.de, R.string.german),
    Language(R.drawable.es, R.string.spanish),
    Language(R.drawable.lt, R.string.lithuanian),
    Language(R.drawable.lv, R.string.latvian),
    Language(R.drawable.ua, R.string.ukrainian)
)
