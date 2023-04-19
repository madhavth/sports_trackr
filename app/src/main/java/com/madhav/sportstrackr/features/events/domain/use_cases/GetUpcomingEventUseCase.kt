package com.madhav.sportstrackr.features.events.domain.use_cases

import com.madhav.sportstrackr.features.events.domain.entities.UpComingEvent
import com.madhav.sportstrackr.features.events.domain.repositories.EventRepository
import javax.inject.Inject

class GetUpcomingEventUseCase @Inject constructor(
    private val repository: EventRepository
){
    suspend fun execute(teamId: String): List<UpComingEvent> {
        return repository.getUpcomingEvents(teamId).events?.map {
            UpComingEvent(
                id = it.idEvent,
                date = it.strTimestamp,
                homeTeam = it.strHomeTeam,
                awayTeam = it.strAwayTeam,
                bannerImage = it.strThumb + "/preview",
            )
        } ?: listOf()
    }
}