package com.madhav.sportstrackr.features.search_add.domain.repositories

import com.madhav.sportstrackr.core.data.models.PlayersModel
import com.madhav.sportstrackr.core.data.models.TeamDetailsModel
import com.madhav.sportstrackr.features.search_add.data.models.SportsModel

interface SearchRepository {
    suspend fun searchTeams(query: String): TeamDetailsModel
    suspend fun getSports(): SportsModel
    suspend fun getAllPlayers(teamName: String, playerName: String): PlayersModel
}