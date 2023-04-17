package com.madhav.sportstrackr.features.events.presentation.page

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.madhav.sportstrackr.core.ui.views.NetworkResponseView
import com.madhav.sportstrackr.features.events.presentation.view_models.EventViewModel

@Composable
fun EventScreen(modifier: Modifier = Modifier) {
    val eventViewModel = hiltViewModel<EventViewModel>()
    val sportsState = eventViewModel.teamSportsEvents.collectAsState()

    NetworkResponseView(sportsState.value, successView = { data ->
        PastFutureEventScreen(
            upcomingEvents = data.upcoming,
            pastEvents = data.past
        )
    })
}

@Preview
@Composable
fun EventsScreenPreview() {
    EventScreenPreview()
}