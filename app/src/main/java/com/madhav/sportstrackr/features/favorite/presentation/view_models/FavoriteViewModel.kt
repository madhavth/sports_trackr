@file:OptIn(FlowPreview::class)

package com.madhav.sportstrackr.features.favorite.presentation.view_models

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.madhav.sportstrackr.core.domain.entity.LeagueTeam
import com.madhav.sportstrackr.core.domain.entity.toFavoriteTeam
import com.madhav.sportstrackr.features.favorite.data.repositories.UserRepository
import com.madhav.sportstrackr.features.favorite.domain.entities.FavoriteTeam
import com.madhav.sportstrackr.features.favorite.domain.use_cases.FavoritesUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    val savedStateHandle: SavedStateHandle,
    private val favoritesUseCases: FavoritesUseCases,
    private val userRepository: UserRepository
) :ViewModel(){

    val currentUser = userRepository.currentUser

    val favoriteTeams = userRepository.currentUser.filter {
        it != null
    }.flatMapConcat {
        favoritesUseCases.getFavoritesUseCase.execute()
    }

    init {
        viewModelScope.launch {
            favoriteTeams.collect {
                println("Favorite Teams: $it")
            }
        }
    }

    private fun removeFavoriteTeam(teamId: String) {
        favoritesUseCases.removeFavoriteUseCase.execute(teamId)
    }

    private fun addFavoriteTeam(favoriteTeam: FavoriteTeam) {
        favoritesUseCases.addFavoriteUseCase.execute(favoriteTeam)
    }

    fun isFavoriteTeam(teamId: String): Flow<Boolean> {
        return favoritesUseCases.isFavoriteUseCase.execute(teamId)
    }

    fun toggleFavorite(it: Boolean, data: LeagueTeam) {
        if (it) {
            removeFavoriteTeam(data.idTeam)
        } else {
            addFavoriteTeam(data.toFavoriteTeam())
        }
    }

}