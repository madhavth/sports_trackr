package com.madhav.sportstrackr.features.search_add.data.services

import com.madhav.sportstrackr.core.data.models.TeamDetailsModel
import com.madhav.sportstrackr.features.search_add.data.models.SportsModel
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchTeamAPI {

    @GET("searchteams.php")
    suspend fun searchTeamByName(@Query("t") teamName: String): TeamDetailsModel

    @GET("all_sports.php")
    suspend fun getSports(): SportsModel
}