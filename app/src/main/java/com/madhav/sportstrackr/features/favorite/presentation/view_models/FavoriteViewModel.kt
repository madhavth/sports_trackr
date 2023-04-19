@file:OptIn(FlowPreview::class)

package com.madhav.sportstrackr.features.favorite.presentation.view_models

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.madhav.sportstrackr.core.domain.entity.LeagueTeam
import com.madhav.sportstrackr.core.domain.entity.toFavoriteTeam
import com.madhav.sportstrackr.core.domain.usecase.UserUseCases
import com.madhav.sportstrackr.core.ui.viewmodels.AuthViewModel
import com.madhav.sportstrackr.features.details.domain.use_cases.TeamUseCases
import com.madhav.sportstrackr.features.favorite.data.repositories.UserRepository
import com.madhav.sportstrackr.features.favorite.domain.entities.FavoriteTeam
import com.madhav.sportstrackr.features.favorite.domain.use_cases.FavoritesUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    val savedStateHandle: SavedStateHandle,
    private val favoritesUseCases: FavoritesUseCases,
    private val teamUseCase: TeamUseCases,
    private val userRepository: UserRepository
) : ViewModel() {
    private val _favoriteTeams: MutableStateFlow<List<FavoriteTeam>?> = MutableStateFlow(null)
    val favoriteTeams: StateFlow<List<FavoriteTeam>?> = _favoriteTeams

    private val currentUser = userRepository.currentUser

    private var coroutineScope: CoroutineScope? = null
    private var job = Job()

    private lateinit var favoriteTeamsFlow: Flow<List<FavoriteTeam>>

    private fun initNewCoroutineScope() {
        job.cancel()
        job = Job()
        coroutineScope = CoroutineScope(Dispatchers.IO + job)
    }

    private fun initFavoriteTeamFlow() {
        favoriteTeamsFlow = currentUser.value?.id?.let {
            favoritesUseCases.getFavoritesUseCase.execute(
                it
            )
        } ?: flowOf(emptyList())
    }

    private fun startCollectingFavorites() {
        initNewCoroutineScope()
        initFavoriteTeamFlow()
        coroutineScope?.launch {
            favoriteTeamsFlow.collect {
                _favoriteTeams.value = it
            }
        }
    }

    private fun onUserChanged() {
        viewModelScope.launch {
            currentUser.collectLatest {
                if (it?.id == null) {
                    _favoriteTeams.value = emptyList()
                } else {
                    startCollectingFavorites()
                }
            }
        }

    }

    init {
        onUserChanged()
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

    fun checkIfFavoriteTeam(teamId: String): Flow<Boolean> {
        return favoriteTeams.map {
            it?.find { favoriteTeam -> favoriteTeam.id == teamId } != null
        }
    }

    fun toggleFavorite(it: Boolean, data: LeagueTeam) {
        if (it) {
            removeFavoriteTeam(data.idTeam)
        } else {
            addFavoriteTeam(data.toFavoriteTeam())
        }
    }

    fun removeFavorite(teamId: String) {
        removeFavoriteTeam(teamId)
    }

}