package com.madhav.sportstrackr.features.search_add.presentation.page

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.internal.composableLambda
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.madhav.sportstrackr.core.constants.MyConstants
import com.madhav.sportstrackr.core.data.models.MyResponse
import com.madhav.sportstrackr.features.search_add.presentation.view_models.SportsSearchViewModel
import com.madhav.sportstrackr.features.search_add.presentation.view_models.TeamSearchViewModel
import kotlinx.coroutines.flow.flow

@Composable
fun SearchScreen(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val searchViewModel = hiltViewModel<TeamSearchViewModel>()

    NavHost(
        navController = navController, startDestination = MyConstants.SEARCH_ROUTE.SPORT_SEARCH,
        modifier = Modifier.fillMaxSize()
    ) {
        composable(MyConstants.SEARCH_ROUTE.TEAM_SEARCH) {
            val teamsState = searchViewModel.teamSearchResult.collectAsState()
            TeamSearchScreen(teamsState = teamsState.value)
        }

        composable(MyConstants.SEARCH_ROUTE.SPORT_SEARCH) {
            val sportsSearchViewModel = hiltViewModel<SportsSearchViewModel>()
            val sportsState = sportsSearchViewModel.sportSearchResult.collectAsState(
                initial = MyResponse.Loading
            )
            SportsSearchScreen(sportsState.value)
        }

        composable(MyConstants.SEARCH_ROUTE.COUNTRY_SEARCH) {
        }

        composable(MyConstants.SEARCH_ROUTE.LEAGUE_SEARCH) {

        }
    }
}