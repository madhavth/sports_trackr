package com.madhav.sportstrackr.features.events.domain.repositories

import com.madhav.sportstrackr.features.events.data.models.PastEventsModel
import com.madhav.sportstrackr.features.events.data.models.UpcomingEventsModel

interface EventRepository {
    suspend fun getPastEvents(teamId: String): PastEventsModel
    suspend fun getUpcomingEvents(teamId: String): UpcomingEventsModel
}