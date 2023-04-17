package com.madhav.sportstrackr.features.events.domain.entities

data class SportsEvents(
    val upcoming: List<UpComingEvent>,
    val past: List<PastEvent>
)
