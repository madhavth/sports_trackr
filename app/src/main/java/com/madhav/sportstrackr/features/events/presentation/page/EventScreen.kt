package com.madhav.sportstrackr.features.events.presentation.page

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.madhav.sportstrackr.core.ui.views.LoadingView
import com.madhav.sportstrackr.core.ui.views.NetworkResponseView
import com.madhav.sportstrackr.core.ui.views.NoTeamAddedView
import com.madhav.sportstrackr.features.events.presentation.view_models.EventViewModel
import com.madhav.sportstrackr.features.favorite.presentation.view_models.FavoriteViewModel

@Composable
fun EventScreen(modifier: Modifier = Modifier) {
    val eventViewModel = hiltViewModel<EventViewModel>()
    val favoriteViewModel = hiltViewModel<FavoriteViewModel>()

    val sportsState = eventViewModel.teamSportsEvents.collectAsState()
    val favoriteTeams = favoriteViewModel.favoriteTeams.collectAsState(initial =null)

    if(favoriteTeams.value == null) {
        LoadingView()
    }
    else {
        if (favoriteTeams.value!!.isNotEmpty()) {
            NetworkResponseView(sportsState.value, successView = { data ->
                PastFutureEventScreen(
                    upcomingEvents = data.upcoming,
                    pastEvents = data.past
                )
            },
                onRetry = {
                    eventViewModel.getTeamSportsEvents()
                }
            )
        }
        else {
            NoTeamAddedView(modifier = modifier.fillMaxSize())
        }
    }
}

@Preview
@Composable
fun EventsScreenPreview() {
    EventScreenPreview()
}