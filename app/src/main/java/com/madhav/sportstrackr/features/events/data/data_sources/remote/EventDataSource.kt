package com.madhav.sportstrackr.features.events.data.data_sources.remote

import com.madhav.sportstrackr.features.events.data.services.EventAPI
import javax.inject.Inject

class EventDataSource @Inject constructor(private val eventsApi: EventAPI){

    suspend fun getPastEvents(teamId: String) = eventsApi.getPastEvents(teamId)

    suspend fun getUpcomingEvents(teamId: String) = eventsApi.getUpcomingEvents(teamId)
}