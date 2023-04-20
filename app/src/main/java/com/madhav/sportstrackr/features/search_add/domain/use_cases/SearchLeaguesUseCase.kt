package com.madhav.sportstrackr.features.search_add.domain.use_cases

import com.madhav.sportstrackr.core.data.models.LeaguesModel
import com.madhav.sportstrackr.core.domain.entity.League
import com.madhav.sportstrackr.features.search_add.domain.repositories.SearchRepository
import javax.inject.Inject

class SearchLeaguesUseCase @Inject constructor(
    private val searchRepository: SearchRepository
) {
    suspend fun execute(sport: String, country:String): List<League> {
        return searchRepository.searchLeagues(sport, country).leagues?.map {
           it. toDomain()
        } ?: listOf()
    }
}
