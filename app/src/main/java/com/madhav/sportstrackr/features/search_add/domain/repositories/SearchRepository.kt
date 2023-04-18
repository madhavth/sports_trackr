package com.madhav.sportstrackr.features.search_add.domain.repositories

import com.madhav.sportstrackr.core.domain.entity.LeagueTeam
import com.madhav.sportstrackr.core.models.TeamDetailsModel

interface SearchRepository {
    suspend fun searchTeams(query: String): TeamDetailsModel
}