package com.madhav.sportstrackr.features.events.presentation.view_models

import android.app.Activity
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.SavedStateHandle
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
import javax.inject.Inject

@HiltViewModel
class EventViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val app: Application,
    private val eventUseCases: EventUseCases
) : AndroidViewModel(app) {
    private val _teamSportsEvent = MutableStateFlow<MyResponse<SportsEvents>>(MyResponse.Loading)

    private var _teamId: MutableStateFlow<String?> = MutableStateFlow(null)
    val teamIdState = _teamId.asStateFlow()

    val teamId get() = _teamId.value

    val teamSportsEvents = _teamSportsEvent.asStateFlow()

    init {
        CoroutineExceptionHandler(handler = { _, throwable ->
            _teamSportsEvent.value = MyResponse.Error(throwable.message.toString())
            throwable.printStackTrace()
        })
    }

    suspend fun getTeamSportsEvents(teamId: String) {
        _teamId.value = teamId
        getTeamSportsEvents()
    }

    suspend fun getTeamSportsEvents() {
        return
        try {
            if(_teamId.value == null) return

            _teamSportsEvent.value = MyResponse.Loading

            val upcomingEvents: List<UpComingEvent> =
                eventUseCases.getUpcomingEventUseCase.execute(_teamId.value!!)
            val pastEvents: List<PastEvent> = eventUseCases.getPastEventUseCase.execute(_teamId.value!!)

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

