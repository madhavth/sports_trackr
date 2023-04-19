package com.madhav.sportstrackr.features.search_add.data.repositories

import com.madhav.sportstrackr.core.data.models.TeamDetailsModel
import com.madhav.sportstrackr.features.search_add.data.data_sources.remote.SearchDataSource
import com.madhav.sportstrackr.features.search_add.data.models.SportsModel
import com.madhav.sportstrackr.features.search_add.domain.repositories.SearchRepository
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val searchDataSource: SearchDataSource
): SearchRepository {
    override suspend fun searchTeams(query: String): TeamDetailsModel {
        return searchDataSource.searchTeams(query)
    }

    override suspend fun getSports(): SportsModel {
        return searchDataSource.getSports()
    }
}