package com.madhav.sportstrackr.features.events.presentation.view_models

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.madhav.sportstrackr.core.models.MyResponse
import com.madhav.sportstrackr.features.events.data.models.PastEventsModel
import com.madhav.sportstrackr.features.events.domain.entities.PastEvent
import com.madhav.sportstrackr.features.events.domain.entities.SportsEvents
import com.madhav.sportstrackr.features.events.domain.entities.UpComingEvent
import com.madhav.sportstrackr.features.events.domain.use_cases.EventUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EventViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val eventUseCases: EventUseCases
) : ViewModel() {
    private val _teamSportsEvent = MutableStateFlow<MyResponse<SportsEvents>>(MyResponse.Loading)
    val teamSportsEvents = _teamSportsEvent.asStateFlow()

    init {
        CoroutineExceptionHandler(handler = { _, throwable ->
            _teamSportsEvent.value = MyResponse.Error(throwable.message.toString())
            throwable.printStackTrace()
        })

        getTeamSportsEvents("133604")
    }

    fun getTeamSportsEvents(teamId: String) {
        viewModelScope.launch {
            try {
                val upcomingEvents: List<UpComingEvent> =
                    eventUseCases.getUpcomingEventUseCase.execute(teamId)
                val pastEvents: List<PastEvent> = eventUseCases.getPastEventUseCase.execute(teamId)

                _teamSportsEvent.value =
                    MyResponse.Success(SportsEvents(upcomingEvents, pastEvents))

            } catch (e: Exception) {
                _teamSportsEvent.value = MyResponse.Error("Something went wrong")
                e.printStackTrace()
            }
        }
    }
}