package com.madhav.sportstrackr.features.details.domain.repositories

import com.madhav.sportstrackr.core.models.TeamDetailsModel

interface TeamRepository {
    suspend fun getTeamDetails(teamId: String): TeamDetailsModel
}