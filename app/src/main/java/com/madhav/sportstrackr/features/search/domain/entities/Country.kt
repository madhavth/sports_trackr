package com.madhav.sportstrackr.features.search.domain.entities

import androidx.annotation.DrawableRes
import androidx.annotation.IntegerRes

data class Country(
    val name: String,
    val region: String,
    @DrawableRes val flag: Int
)
