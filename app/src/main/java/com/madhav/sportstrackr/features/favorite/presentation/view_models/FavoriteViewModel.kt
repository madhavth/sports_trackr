package com.madhav.sportstrackr.features.favorite.presentation.view_models

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.madhav.sportstrackr.features.favorite.domain.entities.FavoriteTeam
import com.madhav.sportstrackr.features.favorite.domain.use_cases.FavoritesUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    val savedStateHandle: SavedStateHandle,
    private val favoritesUseCases: FavoritesUseCases
) :ViewModel(){

    lateinit var favoriteTeams: Flow<FavoriteTeam>

    fun removeFavoriteTeam(teamId: String) {
        favoritesUseCases.removeFavoriteUseCase.execute(teamId)
    }

    fun addFavoriteTeam(favoriteTeam: FavoriteTeam) {
        favoritesUseCases.addFavoriteUseCase.execute(favoriteTeam)
    }

    fun isFavoriteTeam(teamId: String): Flow<Boolean> {
        return favoritesUseCases.isFavoriteUseCase.execute(teamId)
    }

}