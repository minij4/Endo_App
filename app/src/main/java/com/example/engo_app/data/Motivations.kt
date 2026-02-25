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
