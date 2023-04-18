package com.madhav.sportstrackr.features.details.domain.repositories

import com.madhav.sportstrackr.features.details.data.models.TeamDetailsModel
import com.madhav.sportstrackr.features.details.data.models.TeamModel

interface TeamRepository {
    suspend fun getTeamDetails(teamId: String): TeamDetailsModel
}