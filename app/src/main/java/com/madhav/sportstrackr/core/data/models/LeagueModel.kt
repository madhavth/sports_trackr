package com.madhav.sportstrackr.core.data.models

import com.madhav.sportstrackr.core.domain.entity.League

data class LeagueModel(
    val idLeague: String,
    val strLeague: String,
    val strLeagueAlternate: String,
    val strSport: String
) {
    fun toDomain(): League {
        return League(
            sport = strSport,
            name = strLeague,
            country = null
        )
    }
}