package com.madhav.sportstrackr.features.search_add.data.data_sources.remote

import com.madhav.sportstrackr.features.search_add.data.services.SearchTeamAPI
import javax.inject.Inject

class SearchDataSource @Inject constructor(
    private val searchTeamAPI: SearchTeamAPI
) {
    suspend fun searchTeams(query: String) = searchTeamAPI.searchTeamByName(query)
}