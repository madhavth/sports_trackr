package com.madhav.sportstrackr.features.details.data.services

import com.madhav.sportstrackr.core.models.TeamDetailsModel
import retrofit2.http.GET
import retrofit2.http.Query

interface TeamAPI {
    @GET("lookupteam.php")
    suspend fun getTeamDetails(@Query("id") id: String): TeamDetailsModel
}