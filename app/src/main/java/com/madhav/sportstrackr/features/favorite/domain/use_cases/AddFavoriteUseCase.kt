package com.madhav.sportstrackr.features.favorite.domain.use_cases

import com.madhav.sportstrackr.features.favorite.domain.entities.FavoriteTeam
import com.madhav.sportstrackr.features.favorite.domain.repositories.FavoriteRepository
import javax.inject.Inject

class AddFavoriteUseCase @Inject constructor(
    private val favoriteRepository: FavoriteRepository
) {
    fun execute(favoriteTeam: FavoriteTeam) =
        favoriteRepository.addFavoriteTeam(favoriteTeam)
}