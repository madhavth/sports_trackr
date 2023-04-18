package com.madhav.sportstrackr.features.details.presentation.view_models

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.madhav.sportstrackr.core.models.MyResponse
import com.madhav.sportstrackr.features.details.domain.entities.LeagueTeam
import com.madhav.sportstrackr.features.details.domain.use_cases.TeamUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val teamUseCases: TeamUseCases
) : ViewModel() {
    private val _teamDetailsState = MutableStateFlow<MyResponse<LeagueTeam>>(MyResponse.Loading)
    val teamDetailsState = _teamDetailsState.asStateFlow()

    init {
        viewModelScope.launch {
            getTeamDetails("133604")
        }
    }

    private suspend fun getTeamDetails(teamId: String) {
        _teamDetailsState.value = MyResponse.Loading
        _teamDetailsState.value = MyResponse.Success(
            teamUseCases.getTeamUseCase
                .execute(teamId)
        )
    }

    fun toggleFavorite(data: LeagueTeam) {

    }

    val isFavorite
        get() = true

}