package com.madhav.sportstrackr.features.details.presentation.view_models

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.madhav.sportstrackr.core.models.MyResponse
import com.madhav.sportstrackr.features.details.domain.entities.LeagueTeam
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    ) : ViewModel() {
    private val _teamDetailsState = MutableStateFlow<MyResponse<LeagueTeam>>(MyResponse.Loading)
    val teamDetailsState = _teamDetailsState.asStateFlow()


    fun toggleFavorite(data: LeagueTeam) {
    }

    val isFavorite
        get() = true

}