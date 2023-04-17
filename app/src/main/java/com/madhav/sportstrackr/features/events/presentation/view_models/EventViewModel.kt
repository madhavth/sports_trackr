package com.madhav.sportstrackr.features.events.presentation.view_models

import androidx.lifecycle.ViewModel
import com.madhav.sportstrackr.core.models.MyResponse
import com.madhav.sportstrackr.features.details.domain.entities.LeagueTeam
import com.madhav.sportstrackr.features.events.domain.entities.SportsEvents
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class EventViewModel @Inject constructor():ViewModel() {
    private val _teamSportsEvent = MutableStateFlow<MyResponse<SportsEvents>>(MyResponse.Loading)
    val teamSportsEvents = _teamSportsEvent.asStateFlow()
}