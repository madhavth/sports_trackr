package com.madhav.sportstrackr.features.events.presentation.page

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.madhav.sportstrackr.core.ui.viewmodels.MainViewModel
import com.madhav.sportstrackr.core.ui.views.LoadingView
import com.madhav.sportstrackr.core.ui.views.NetworkResponseView
import com.madhav.sportstrackr.core.ui.views.NoTeamAddedView
import com.madhav.sportstrackr.features.details.presentation.views.FavoriteAvatarList
import com.madhav.sportstrackr.features.events.presentation.view_models.EventViewModel
import com.madhav.sportstrackr.features.favorite.presentation.view_models.FavoriteViewModel
import kotlinx.coroutines.launch

@Composable
fun EventScreen(modifier: Modifier = Modifier) {
    val eventViewModel = hiltViewModel<EventViewModel>()
    val favoriteViewModel = hiltViewModel<FavoriteViewModel>()
    val mainViewModel = hiltViewModel<MainViewModel>()

    val sportsState = eventViewModel.teamSportsEvents.collectAsState()
    val favoriteTeams = favoriteViewModel.favoriteTeams.collectAsState(initial = null)
    val selectedTeamId = eventViewModel.teamIdState.collectAsState(initial = null)

    val scope = rememberCoroutineScope()

    LaunchedEffect(key1 = favoriteTeams.value) {
        if (favoriteTeams.value?.isNotEmpty() == true) {
            if (eventViewModel.teamId != null) return@LaunchedEffect

            val firstTeam = favoriteTeams.value!!.first().id
            eventViewModel.getTeamSportsEvents(firstTeam)
        }
    }

    Column(modifier = Modifier.fillMaxSize()) {
        Row(modifier = Modifier.padding(8.dp)) {
            Text(
                text = "MY TEAMS", style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            )
        }

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(Color.DarkGray)
        )

        FavoriteAvatarList(
            favoriteTeams = favoriteTeams.value,
            onAddClicked =  {
                mainViewModel.navigateToAddTeam()
            },
            onClicked = { team ->
                scope.launch {
                    eventViewModel.getTeamSportsEvents(team.id)
                }
            },
            onRemoveClicked = { team ->
                favoriteViewModel.removeFavorite(team.id)
            },
            selectedTeamId = selectedTeamId.value
        )
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(Color.DarkGray)
        )

        if (favoriteTeams.value == null) {
            LoadingView()
        } else {
            if (favoriteTeams.value!!.isNotEmpty()) {
                NetworkResponseView(sportsState.value, successView = { data ->
                    PastFutureEventScreen(
                        upcomingEvents = data.upcoming,
                        pastEvents = data.past
                    )
                },
                    onRetry = {
                        scope.launch {
                            eventViewModel.getTeamSportsEvents()
                        }
                    }
                )
            } else {
                NoTeamAddedView(modifier = modifier.fillMaxSize()) {
                    mainViewModel.navigateToAddTeam()
                }
            }
        }
    }
}

@Preview
@Composable
fun EventsScreenPreview() {
    EventScreenPreview()
}