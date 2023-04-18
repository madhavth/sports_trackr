package com.madhav.sportstrackr.features.details.data.repositories

import com.madhav.sportstrackr.features.details.data.data_sources.remote.TeamDataSource
import com.madhav.sportstrackr.features.details.data.models.TeamDetailsModel
import com.madhav.sportstrackr.features.details.data.models.TeamModel
import com.madhav.sportstrackr.features.details.domain.repositories.TeamRepository
import javax.inject.Inject

class TeamRepositoryImpl  @Inject constructor(
    private val teamDataSource: TeamDataSource
) : TeamRepository {
    override suspend fun getTeamDetails(teamId: String): TeamDetailsModel {
        return teamDataSource.getTeamDetails(teamId)
    }
}