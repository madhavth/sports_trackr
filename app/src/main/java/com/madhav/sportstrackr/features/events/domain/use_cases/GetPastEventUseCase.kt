package com.madhav.sportstrackr.features.events.domain.use_cases

import com.madhav.sportstrackr.core.models.TeamScore
import com.madhav.sportstrackr.features.events.domain.entities.PastEvent
import com.madhav.sportstrackr.features.events.domain.repositories.EventRepository
import javax.inject.Inject


class GetPastEventUseCase @Inject constructor(
    private val repository: EventRepository
) {
    suspend fun execute(teamId: String): List<PastEvent> {
        return repository.getPastEvents(teamId).results.map {
            PastEvent(
                homeScore = TeamScore(
                    score = it.intHomeScore,
                    teamName = it.strHomeTeam
                ),
                time = it.strTime,
                awayScore = TeamScore(
                    score = it.intAwayScore,
                    teamName = it.strAwayTeam
                ),
                id = it.idEvent,
                name = it.strFilename,
                date = it.dateEvent
            )
        }.toList()
    }
}