package com.madhav.sportstrackr.features.details.presentation.page

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraph
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
    val favoriteTeams = favoriteViewModel.favoriteTeams.collectAsState(initial =null)
    val mainViewModel: MainViewModel = hiltViewModel()

    val navController = rememberNavController()

    NavHost(
        navController = navController, startDestination = MyConstants.DETAILS_ROUTE.TEAM_LIST,
        modifier = Modifier.fillMaxSize()
    ) {
        composable(MyConstants.DETAILS_ROUTE.TEAM_LIST) {

            if(favoriteTeams.value == null) {
                LoadingView()
            }

            else {
                if(favoriteTeams.value!!.isNotEmpty()) {
                    TeamListView(modifier = modifier, teams = favoriteTeams.value!!, onClick = {
                        navController.navigate(MyConstants.DETAILS_ROUTE.TEAM_DETAILS + "/${it.id}") {
                            navArgument("arg") {
                                defaultValue = it.id
                            }
                        }
                    })
                }
                else {
                    NoTeamAddedView(modifier = modifier.fillMaxSize()) {
                        mainViewModel.navigateToAddTeam()
                    }
                }
            }
        }

        composable(MyConstants.DETAILS_ROUTE.TEAM_DETAILS + "/{arg}") {
            val teamId = it.arguments?.getString("arg")
            DetailsScreen(
                backPressed =  {
                    navController.navigateUp()
                },
                teamId = teamId,
                favoriteViewModel = favoriteViewModel
            )
        }

    }

}