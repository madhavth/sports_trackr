package com.madhav.sportstrackr.features.search_add.domain.entities

import com.madhav.sportstrackr.core.data.models.League
import com.madhav.sportstrackr.core.domain.entity.LeagueTeam

sealed class SearchResult {
    data class Countries(val countries: List<Country>)
    data class Teams(val teams: List<LeagueTeam>)
    data class Sports(val sports: List<Sport>)
    data class Leagues(val leagues: List<League>)
}