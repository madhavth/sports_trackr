@file:OptIn(FlowPreview::class)

package com.madhav.sportstrackr.features.favorite.presentation.view_models

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.madhav.sportstrackr.core.domain.entity.LeagueTeam
import com.madhav.sportstrackr.core.domain.entity.toFavoriteTeam
import com.madhav.sportstrackr.features.details.domain.use_cases.TeamUseCases
import com.madhav.sportstrackr.features.favorite.data.repositories.UserRepository
import com.madhav.sportstrackr.features.favorite.domain.entities.FavoriteTeam
import com.madhav.sportstrackr.features.favorite.domain.use_cases.FavoritesUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapConcat
import javax.inject.Inject

@OptIn(FlowPreview::class)
@HiltViewModel
class FavoriteViewModel @Inject constructor(
    val savedStateHandle: SavedStateHandle,
    private val favoritesUseCases: FavoritesUseCases,
    private val teamUseCase: TeamUseCases,
    private val userRepository: UserRepository
) :ViewModel(){
    val favoriteTeams = userRepository.currentUser.filter {
        it != null
    }.flatMapConcat {
        favoritesUseCases.getFavoritesUseCase.execute()
    }

    init {

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