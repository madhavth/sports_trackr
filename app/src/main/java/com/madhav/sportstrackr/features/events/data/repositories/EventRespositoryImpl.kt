package com.madhav.sportstrackr.features.events.data.repositories

import com.madhav.sportstrackr.features.events.data.data_sources.remote.EventDataSource
import com.madhav.sportstrackr.features.events.data.models.PastEventsModel
import com.madhav.sportstrackr.features.events.data.models.UpcomingEventsModel
import com.madhav.sportstrackr.features.events.domain.repositories.EventRepository
import javax.inject.Inject

class EventRepositoryImpl @Inject constructor(
    private val eventDataSource: EventDataSource
): EventRepository {
    override suspend fun getPastEvents(teamId: String): PastEventsModel {
        return eventDataSource.getPastEvents(teamId)
    }

    override suspend fun getUpcomingEvents(teamId: String): UpcomingEventsModel {
        return eventDataSource.getUpcomingEvents(teamId)
    }
}