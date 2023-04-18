package com.madhav.sportstrackr.features.favorite.domain.use_cases

import javax.inject.Inject


class FavoritesUseCases @Inject constructor(
    val addFavoriteUseCase: AddFavoriteUseCase,
    val removeFavoriteUseCase: RemoveFavoriteUseCase,
    val getFavoritesUseCase: GetFavoriteTeamsUseCase,
    val isFavoriteUseCase: IsFavoriteTeamUseCase
)