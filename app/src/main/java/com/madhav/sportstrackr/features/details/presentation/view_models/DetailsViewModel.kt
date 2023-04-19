package com.madhav.sportstrackr.features.details.presentation.view_models

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.madhav.sportstrackr.core.data.models.MyResponse
import com.madhav.sportstrackr.core.domain.entity.LeagueTeam
import com.madhav.sportstrackr.features.details.domain.use_cases.TeamUseCases
import com.madhav.sportstrackr.features.favorite.domain.use_cases.FavoritesUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val teamUseCases: TeamUseCases,
    private val favoriteTeamUseCases: FavoritesUseCases
) : ViewModel() {
    private var _teamId: String?= null

    val teamId get() = _teamId

    private val _teamDetailsState = MutableStateFlow<MyResponse<LeagueTeam>>(MyResponse.Loading)
    val teamDetailsState = _teamDetailsState.asStateFlow()

    suspend fun getTeamDetails(teamId: String?) {
        if(teamId == null) return

        _teamId = teamId
        _teamDetailsState.value = MyResponse.Loading
        _teamDetailsState.value = MyResponse.Success(
            teamUseCases.getTeamUseCase
                .execute(_teamId!!)
        )
    }

}