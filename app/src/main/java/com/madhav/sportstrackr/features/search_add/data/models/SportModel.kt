package com.madhav.sportstrackr.features.search_add.data.models

import com.madhav.sportstrackr.features.search_add.domain.entities.Sport

data class SportModel(
    val idSport: String,
    val strFormat: String,
    val strSport: String,
    val strSportDescription: String,
    val strSportIconGreen: String,
    val strSportThumb: String
) {
    fun toDomain(): Sport {
        return Sport(
            name = strSport,
            imgUrl = strSportThumb
        )
    }
}