package com.madhav.sportstrackr.features.search_add.domain.use_cases

import com.madhav.sportstrackr.core.domain.entity.PlayerInfo
import com.madhav.sportstrackr.features.search_add.domain.repositories.SearchRepository
import javax.inject.Inject


class SearchPlayerUseCase @Inject constructor(
    private val searchPlayerRepository: SearchRepository,
) {
    suspend fun execute(teamName: String, playerName:String): List<PlayerInfo> {
        return searchPlayerRepository.getAllPlayers(teamName, playerName).player?.map {
            it.toDomain()
        } ?: listOf()
    }
}
