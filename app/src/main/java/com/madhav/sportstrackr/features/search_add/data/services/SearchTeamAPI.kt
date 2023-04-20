package com.madhav.sportstrackr.features.search_add.data.services

import com.madhav.sportstrackr.core.data.models.LeagueTeamsModel
import com.madhav.sportstrackr.core.data.models.LeaguesModel
import com.madhav.sportstrackr.core.data.models.PlayersModel
import com.madhav.sportstrackr.core.data.models.TeamDetailsModel
import com.madhav.sportstrackr.features.search_add.data.models.CountriesModel
import com.madhav.sportstrackr.features.search_add.data.models.SportsModel
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchTeamAPI {

    @GET("searchteams.php")
    suspend fun searchTeamByName(@Query("t") teamName: String): TeamDetailsModel

    @GET("all_sports.php")
    suspend fun getSports(): SportsModel

    @GET("searchplayers.php")
    suspend fun getAllPlayers(@Query("t") teamName: String, @Query("p") playerName: String): PlayersModel

    @GET("all_countries.php")
    suspend fun getAllCountries(): CountriesModel

    @GET("search_all_leagues.php")
    suspend fun getAllLeagues(@Query("s") sport: String,@Query("c") country: String): LeagueTeamsModel

    @GET("all_leagues.php")
    suspend fun getEveryLeague(): LeaguesModel

    @GET("search_all_teams.php")
    suspend fun getLeagueTeams(@Query("l") leagueName: String): TeamDetailsModel
}