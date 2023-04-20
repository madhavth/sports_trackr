package com.madhav.sportstrackr.features.search_add.data.repositories

import com.madhav.sportstrackr.core.data.models.LeaguesModel
import com.madhav.sportstrackr.core.data.models.PlayersModel
import com.madhav.sportstrackr.core.data.models.TeamDetailsModel
import com.madhav.sportstrackr.features.search_add.data.data_sources.remote.SearchDataSource
import com.madhav.sportstrackr.features.search_add.data.models.CountriesModel
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

    override suspend fun getAllPlayers(teamName: String, playerName:String): PlayersModel {
        return searchDataSource.getAllPlayers(teamName, playerName)
    }

    override suspend fun getAllCountries(): CountriesModel {
        return searchDataSource.getAllCountries()
    }

    override suspend fun searchLeagues(sport: String, country: String): LeaguesModel {
        return searchDataSource.getAllLeagues(sport, country)
    }

    override suspend fun getLeagueTeams(leagueName: String): TeamDetailsModel {
        return searchDataSource.getLeagueTeams(leagueName)
    }
}