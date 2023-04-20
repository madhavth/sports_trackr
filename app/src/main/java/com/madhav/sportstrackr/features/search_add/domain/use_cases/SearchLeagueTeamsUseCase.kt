package com.madhav.sportstrackr.features.search_add.domain.use_cases

import com.madhav.sportstrackr.core.data.models.toLeagueTeamEntity
import com.madhav.sportstrackr.core.domain.entity.LeagueTeam
import com.madhav.sportstrackr.features.search_add.domain.repositories.SearchRepository
import javax.inject.Inject

class SearchLeagueTeamsUseCase @Inject constructor(
    private val searchRepository: SearchRepository
) {
    suspend fun execute(leagueName: String): List<LeagueTeam> =
        searchRepository.getLeagueTeams(leagueName).teams?.map {
            it.toLeagueTeamEntity()
        } ?: listOf()
}
