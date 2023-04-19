package com.madhav.sportstrackr.features.search_add.data.data_sources.remote

import com.madhav.sportstrackr.core.data.models.PlayersModel
import com.madhav.sportstrackr.features.search_add.data.services.SearchTeamAPI
import javax.inject.Inject

class SearchDataSource @Inject constructor(
    private val searchTeamAPI: SearchTeamAPI
) {
    suspend fun searchTeams(query: String) = searchTeamAPI.searchTeamByName(query)

    suspend fun getSports() = searchTeamAPI.getSports()

    suspend fun getAllPlayers(teamName: String, playerName: String): PlayersModel = searchTeamAPI
        .getAllPlayers(teamName, playerName)
}