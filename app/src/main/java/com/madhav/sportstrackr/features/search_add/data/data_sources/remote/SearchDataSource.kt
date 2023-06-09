package com.madhav.sportstrackr.features.search_add.data.data_sources.remote

import com.madhav.sportstrackr.core.data.models.LeagueModel
import com.madhav.sportstrackr.core.data.models.LeaguesModel
import com.madhav.sportstrackr.core.data.models.PlayersModel
import com.madhav.sportstrackr.core.data.models.TeamDetailsModel
import com.madhav.sportstrackr.features.search_add.data.models.CountriesModel
import com.madhav.sportstrackr.features.search_add.data.services.SearchTeamAPI
import javax.inject.Inject

class SearchDataSource @Inject constructor(
    private val searchTeamAPI: SearchTeamAPI
) {
    suspend fun searchTeams(query: String) = searchTeamAPI.searchTeamByName(query)

    suspend fun getSports() = searchTeamAPI.getSports()

    suspend fun getAllPlayers(teamName: String, playerName: String): PlayersModel = searchTeamAPI
        .getAllPlayers(teamName, playerName)

    suspend fun getAllCountries(): CountriesModel= searchTeamAPI.getAllCountries()

    suspend fun getAllLeagues(sport: String, country: String) : List<LeagueModel>? {
        if(sport.isBlank() && country.isBlank()) {
            return searchTeamAPI.getEveryLeague().leagues
        }

        return searchTeamAPI.getAllLeagues(sport, country).countries
    }

    suspend fun getLeagueTeams(leagueName: String): TeamDetailsModel = searchTeamAPI.getLeagueTeams(leagueName)
}