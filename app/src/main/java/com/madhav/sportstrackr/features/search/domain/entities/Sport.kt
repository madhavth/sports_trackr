package com.madhav.sportstrackr.features.search.domain.entities

import androidx.annotation.DrawableRes

data class Sport(
    val name: String,
    @DrawableRes val icon: Int
)