package com.madhav.sportstrackr.features.search_add.data.services

import com.madhav.sportstrackr.core.models.TeamDetailsModel
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchTeamAPI {

    @GET("searchteams.php")
    suspend fun searchTeamByName(@Query("t") teamName: String): TeamDetailsModel
}