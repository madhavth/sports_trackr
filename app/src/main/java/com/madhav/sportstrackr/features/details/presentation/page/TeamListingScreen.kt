package com.madhav.sportstrackr.features.details.presentation.page

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.madhav.sportstrackr.core.constants.MyConstants
import com.madhav.sportstrackr.core.ui.viewmodels.AuthViewModel
import com.madhav.sportstrackr.core.ui.viewmodels.MainViewModel
import com.madhav.sportstrackr.core.ui.views.LoadingView
import com.madhav.sportstrackr.core.ui.views.NoTeamAddedView
import com.madhav.sportstrackr.features.favorite.presentation.view_models.FavoriteViewModel
import com.madhav.sportstrackr.features.search_add.presentation.view_models.PlayerSearchViewModel

@Composable
fun TeamListingScreen(modifier: Modifier = Modifier) {
    val favoriteViewModel: FavoriteViewModel = hiltViewModel()
    val favoriteTeams = favoriteViewModel.favoriteTeams.collectAsState(initial = null)
    val mainViewModel: MainViewModel = hiltViewModel()
    val navController = rememberNavController()
    val authViewModel = hiltViewModel<AuthViewModel>()

    NavHost(
        navController = navController, startDestination = MyConstants.DETAILS_ROUTE.TEAM_LIST,
        modifier = Modifier.fillMaxSize()
    ) {
        composable(MyConstants.DETAILS_ROUTE.TEAM_LIST) {

            if (favoriteTeams.value == null) {
                LoadingView()
            } else {
                if (favoriteTeams.value!!.isNotEmpty()) {

                    FavoriteTeamsScreen(
                        favoriteTeams = favoriteTeams.value!!, navController = navController,
                        favoriteViewModel = favoriteViewModel
                    )

                } else {
                    NoTeamAddedView(modifier = modifier.fillMaxSize()) {
                        mainViewModel.navigateToAddTeam()
                    }
                }
            }
        }

        composable(MyConstants.DETAILS_ROUTE.TEAM_DETAILS + "/{arg}/{teamName}") {
            val teamId = it.arguments?.getString("arg")
            val myTeamName = it.arguments?.getString("teamName")
            val currentUser = authViewModel.currentUser.collectAsState()

            DetailsScreen(
                backPressed = {
                    navController.navigateUp()
                },
                teamId = teamId,
                teamName = myTeamName,
                favoriteViewModel = favoriteViewModel,
                onClickedSearch = { teamName ->
                    navController.navigate(MyConstants.DETAILS_ROUTE.PLAYER_SEARCH + "/${teamId}/${teamName}")
                },
                isLoggedIn = currentUser.value != null
            )
        }

        composable(MyConstants.DETAILS_ROUTE.PLAYER_SEARCH + "/{arg}/{teamName}") {
            val teamId = it.arguments?.getString("arg")
            val teamName = it.arguments?.getString("teamName")

            val playersSearchViewModel: PlayerSearchViewModel = hiltViewModel()

            LaunchedEffect(key1 = true, block = {
                playersSearchViewModel.setTeamId(teamId)
                playersSearchViewModel.setTeamName(teamName)
                playersSearchViewModel.performSearch("")
            })

            val playersList = playersSearchViewModel.teamPlayers.collectAsState()

            PlayerSearchScreen(
                backPressed = {
                    navController.navigateUp()
                },
                teamId = teamId,
                teamName = teamName,
                playersList = playersList.value,
                performSearch = { query ->
                    playersSearchViewModel.performSearch(query)
                }
            )
        }

    }

}