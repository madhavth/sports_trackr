package com.madhav.sportstrackr.features.search_add.presentation.page

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.madhav.sportstrackr.core.constants.MyConstants
import com.madhav.sportstrackr.core.data.models.MyResponse
import com.madhav.sportstrackr.core.ui.viewmodels.MainViewModel
import com.madhav.sportstrackr.features.search_add.presentation.view_models.SportsSearchViewModel
import com.madhav.sportstrackr.features.search_add.presentation.view_models.TeamSearchViewModel

@Composable
fun SearchScreen(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val teamSearchViewModel = hiltViewModel<TeamSearchViewModel>()
    val mainViewModel = hiltViewModel<MainViewModel>()

    NavHost(
        navController = navController, startDestination = MyConstants.SEARCH_ROUTE.TEAM_SEARCH,
        modifier = Modifier.fillMaxSize()
    ) {
        composable(MyConstants.SEARCH_ROUTE.TEAM_SEARCH) {
            val teamsState = teamSearchViewModel.teamSearchResult.collectAsState()
            val showAlertDialog = mainViewModel.showAddAlertDialog.collectAsState()

            TeamSearchScreen(
                teamsState = teamsState.value, teamSearchViewModel,
                showAddAlertDialog = showAlertDialog.value,
                mainViewModel = mainViewModel,
                navigateRequest = {
                    route ->
                    navController.navigate(route)
                }
            )
        }

        composable(MyConstants.SEARCH_ROUTE.SPORT_SEARCH) {
            val sportsSearchViewModel = hiltViewModel<SportsSearchViewModel>()
            val sportsState = sportsSearchViewModel.sportSearchResult.collectAsState(
                initial = MyResponse.Loading
            )
            SportsSearchScreen(sportsState.value, onBackPressed = {
                navController.navigateUp()
            })
        }

        composable(MyConstants.SEARCH_ROUTE.COUNTRY_SEARCH) {
        }

        composable(MyConstants.SEARCH_ROUTE.LEAGUE_SEARCH) {

        }
    }
}