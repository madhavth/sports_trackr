package com.madhav.sportstrackr.features.details.data.data_sources.remote

import com.madhav.sportstrackr.features.details.data.models.TeamDetailsModel
import com.madhav.sportstrackr.features.details.data.services.TeamAPI
import javax.inject.Inject

class TeamDataSource @Inject constructor(
    private val teamAPI: TeamAPI
) {
    suspend fun getTeamDetails(teamId: String): TeamDetailsModel {
        return teamAPI.getTeamDetails(teamId)
    }
}