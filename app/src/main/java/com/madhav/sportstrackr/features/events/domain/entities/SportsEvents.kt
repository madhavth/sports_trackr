package com.madhav.sportstrackr.features.events.domain.entities

data class SportsEvents(
    val upcoming: List<UpCompingEvent>,
    val past: List<PastEvent>
)
