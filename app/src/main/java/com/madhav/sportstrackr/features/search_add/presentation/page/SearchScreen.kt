package com.madhav.sportstrackr.features.search_add.presentation.page

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.madhav.sportstrackr.core.constants.MyConstants
import com.madhav.sportstrackr.core.data.models.MyResponse
import com.madhav.sportstrackr.core.ui.viewmodels.AuthViewModel
import com.madhav.sportstrackr.core.ui.viewmodels.MainViewModel
import com.madhav.sportstrackr.features.favorite.presentation.view_models.FavoriteViewModel
import com.madhav.sportstrackr.features.search_add.presentation.view_models.LeagueSearchViewModel
import com.madhav.sportstrackr.features.search_add.presentation.view_models.SportsSearchViewModel
import com.madhav.sportstrackr.features.search_add.presentation.view_models.TeamSearchViewModel

@Composable
fun SearchScreen(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val teamSearchViewModel = hiltViewModel<TeamSearchViewModel>()
    val mainViewModel = hiltViewModel<MainViewModel>()
    val favoriteViewModel = hiltViewModel<FavoriteViewModel>()
    val authViewModel: AuthViewModel = hiltViewModel()

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
                navigateRequest = { route ->
                    navController.navigate(route)
                }
            )
        }

        composable(MyConstants.SEARCH_ROUTE.LEAGUES_TEAM_SEARCH + "/{league}") {
            val league = it.arguments?.getString("league")
            val userState = authViewModel.currentUser.collectAsState()

            LeagueTeamSearchScreen(
                onBackPressed = {
                    navController.navigateUp()
                },
                league = league,
                favoriteViewModel = favoriteViewModel,
                isLoggedIn = userState.value != null
            )
        }

        composable(MyConstants.SEARCH_ROUTE.SPORT_SEARCH + "/{country}") {
            val country = it.arguments?.getString("country")

            val sportsSearchViewModel = hiltViewModel<SportsSearchViewModel>()
            val sportsState = sportsSearchViewModel.sportSearchResult.collectAsState(
                initial = MyResponse.Loading
            )
            SportsSearchScreen(sportsState.value, onBackPressed = {
                navController.navigateUp()
            },
                onClicked = { sport ->
                    if (country == null) {
                        navController.navigate(MyConstants.SEARCH_ROUTE.COUNTRY_SEARCH + "/${sport.name}")
                    } else {
                        navController.navigate(MyConstants.SEARCH_ROUTE.LEAGUE_SEARCH + "/${country}/${sport.name}") {
                            navArgument("country") {
                                defaultValue = country
                            }
                            navArgument("sports") {
                                defaultValue = sport.name
                            }
                        }
                    }
                }
            )
        }

        composable(MyConstants.SEARCH_ROUTE.COUNTRY_SEARCH + "/{sports}") {
            val sports = it.arguments?.getString("sports")

            CountriesSearchScreen(onCountrySelected = { country ->
                if (sports == null) {
                    navController.navigate(MyConstants.SEARCH_ROUTE.SPORT_SEARCH)
                } else {
                    navController.navigate(MyConstants.SEARCH_ROUTE.LEAGUE_SEARCH + "/${country.name}/${sports}") {
                        navArgument("country") {
                            defaultValue = country
                        }
                        navArgument("sports") {
                            defaultValue = sports
                        }
                    }
                }
            },
                onBackPressed = {
                    navController.navigateUp()
                }
            )
        }

        composable(MyConstants.SEARCH_ROUTE.LEAGUE_SEARCH + "/{country}/{sports}") {
            val country = it.arguments?.getString("country")
            val sports = it.arguments?.getString("sports")

            LeagueSearchScreen(
                onBackPressed = {
                    navController.navigateUp()
                },
                country = country,
                sports = sports,
                onLeagueClicked = { league ->
                    navController.navigate(MyConstants.SEARCH_ROUTE.LEAGUES_TEAM_SEARCH + "/${league.name}") {
                        navArgument("league") {
                            defaultValue = league.name
                        }
                    }
                }
            )

        }
    }
}