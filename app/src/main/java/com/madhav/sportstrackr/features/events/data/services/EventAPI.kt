package com.madhav.sportstrackr.features.events.data.services

import com.madhav.sportstrackr.features.events.data.models.PastEventsModel
import com.madhav.sportstrackr.features.events.data.models.UpcomingEventsModel
import retrofit2.http.GET
import retrofit2.http.Query

interface EventAPI {
    @GET("eventslast.php")
    suspend fun getPastEvents(@Query("id") id: String): PastEventsModel

    @GET("eventsnext.php")
    suspend fun getUpcomingEvents(@Query("id") id: String): UpcomingEventsModel
}