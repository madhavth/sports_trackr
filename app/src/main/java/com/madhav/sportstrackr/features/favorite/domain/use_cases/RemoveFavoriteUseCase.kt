package com.madhav.sportstrackr.features.favorite.domain.use_cases

import com.madhav.sportstrackr.features.favorite.domain.entities.FavoriteTeam
import com.madhav.sportstrackr.features.favorite.domain.repositories.FavoriteRepository
import javax.inject.Inject

class RemoveFavoriteUseCase @Inject constructor(
    private val favoriteRepository: FavoriteRepository
) {
    fun execute(teamId: String) =
        favoriteRepository.removeFavoriteTeam(teamId)
}
