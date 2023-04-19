package com.madhav.sportstrackr.features.details.presentation.page

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
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
import com.madhav.sportstrackr.core.ui.viewmodels.MainViewModel
import com.madhav.sportstrackr.core.ui.views.LoadingView
import com.madhav.sportstrackr.core.ui.views.NoTeamAddedView
import com.madhav.sportstrackr.features.favorite.presentation.view_models.FavoriteViewModel

@Composable
fun TeamListingScreen(modifier: Modifier = Modifier) {
    val favoriteViewModel: FavoriteViewModel = hiltViewModel()
    val favoriteTeams = favoriteViewModel.favoriteTeams.collectAsState(initial = null)
    val mainViewModel: MainViewModel = hiltViewModel()

    val navController = rememberNavController()

    NavHost(
        navController = navController, startDestination = MyConstants.DETAILS_ROUTE.TEAM_LIST,
        modifier = Modifier.fillMaxSize()
    ) {
        composable(MyConstants.DETAILS_ROUTE.TEAM_LIST) {

            if (favoriteTeams.value == null) {
                LoadingView()
            } else {
                if (favoriteTeams.value!!.isNotEmpty()) {

                    Column(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Text(
                            text = "FAVORITE TEAMS",
                            style = MaterialTheme.typography.body1.copy(
                                fontWeight = FontWeight.Bold
                            ),
                            modifier = Modifier.padding(vertical = 16.dp, horizontal =16.dp)
                        )

                        TeamListView(
                            modifier = modifier, teams = favoriteTeams.value!!,
                            onClick = {
                                navController.navigate(MyConstants.DETAILS_ROUTE.TEAM_DETAILS + "/${it.id}") {
                                    navArgument("arg") {
                                        defaultValue = it.id
                                    }
                                }
                            },
                            onToggleFavorite = {
                                favoriteViewModel.removeFavorite(it)
                            },
                            searchIconClicked =  {
                                navController.navigate(MyConstants.DETAILS_ROUTE.PLAYER_SEARCH + "/${it.id}/${it.name}")
                            }
                        )
                    }
                } else {
                    NoTeamAddedView(modifier = modifier.fillMaxSize()) {
                        mainViewModel.navigateToAddTeam()
                    }
                }
            }
        }

        composable(MyConstants.DETAILS_ROUTE.TEAM_DETAILS + "/{arg}") {
            val teamId = it.arguments?.getString("arg")
            DetailsScreen(
                backPressed = {
                    navController.navigateUp()
                },
                teamId = teamId,
                favoriteViewModel = favoriteViewModel
            )
        }

        composable(MyConstants.DETAILS_ROUTE.PLAYER_SEARCH + "/{arg}/{teamName}") {
            val teamId = it.arguments?.getString("arg")
            val teamName = it.arguments?.getString("teamName")

            PlayerSearchScreen(
                backPressed = {
                    navController.navigateUp()
                },
                teamId = teamId,
                teamName = teamName
            )
        }

    }

}