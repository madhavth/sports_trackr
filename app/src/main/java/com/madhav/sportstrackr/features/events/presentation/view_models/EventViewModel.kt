package com.madhav.sportstrackr.features.events.presentation.view_models

import android.app.Activity
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.madhav.sportstrackr.core.helpers.DateHelper
import com.madhav.sportstrackr.core.data.models.MyResponse
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
    private val app: Application,
    private val eventUseCases: EventUseCases
) : AndroidViewModel(app) {
    private val _teamSportsEvent = MutableStateFlow<MyResponse<SportsEvents>>(MyResponse.Loading)

    private var teamId: String = "133604"

    val teamSportsEvents = _teamSportsEvent.asStateFlow()

    private val _snackBarMessage: MutableStateFlow<String> = MutableStateFlow("")
    val snackBarMessage = _snackBarMessage.asStateFlow()

    init {
        CoroutineExceptionHandler(handler = { _, throwable ->
            _teamSportsEvent.value = MyResponse.Error(throwable.message.toString())
            throwable.printStackTrace()
        })

        viewModelScope.launch {
            getTeamSportsEvents()
        }
    }

    suspend fun getTeamSportsEvents() {
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

    fun addEventToCalendar(upComingEvent: UpComingEvent, context: Activity) {
        DateHelper.openCalendar(
            event = upComingEvent,
            context = context,
        )
    }

}

