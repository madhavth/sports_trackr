package com.madhav.sportstrackr.features.search_add.domain.use_cases

import com.madhav.sportstrackr.core.domain.entity.LeagueTeam
import com.madhav.sportstrackr.core.data.models.toLeagueTeamEntity
import com.madhav.sportstrackr.features.search_add.domain.repositories.SearchRepository
import javax.inject.Inject

class SearchTeamUseCase @Inject constructor(
    private val repository: SearchRepository
) {
    suspend fun execute(query: String): List<LeagueTeam> {
        return repository.searchTeams(query).teams?.map {
            it.toLeagueTeamEntity()
        } ?: listOf()
    }
}