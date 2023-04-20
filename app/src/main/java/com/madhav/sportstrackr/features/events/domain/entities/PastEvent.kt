package com.madhav.sportstrackr.features.events.domain.entities

import com.madhav.sportstrackr.core.data.models.TeamScore

data class PastEvent(
    val id: String,
    val name: String,
    val date: String,
    val time: String,
    val homeScore: TeamScore,
    val awayScore: TeamScore,
    val bannerImage: String = "",
    val league: String = ""
)