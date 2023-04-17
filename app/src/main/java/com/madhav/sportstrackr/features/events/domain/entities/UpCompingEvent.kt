package com.madhav.sportstrackr.features.events.domain.entities

data class UpCompingEvent(
    val id: String,
    val homeTeam: String,
    val awayTeam: String,
    val date: String,
//    val thumbUrl: String
)
