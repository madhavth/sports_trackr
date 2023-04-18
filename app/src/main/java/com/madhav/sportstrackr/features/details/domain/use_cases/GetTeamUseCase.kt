package com.madhav.sportstrackr.features.details.domain.use_cases

import com.madhav.sportstrackr.core.domain.entity.LeagueTeam
import com.madhav.sportstrackr.core.data.models.toLeagueTeamEntity
import com.madhav.sportstrackr.features.details.domain.repositories.TeamRepository
import javax.inject.Inject

class GetTeamUseCase @Inject constructor(
    private val repository: TeamRepository
) {

    suspend fun execute(teamId: String): LeagueTeam {
        val teamDetails = repository.getTeamDetails(teamId)
        return teamDetails.teams!!.map {
            it.toLeagueTeamEntity()
        }.first()
    }
}