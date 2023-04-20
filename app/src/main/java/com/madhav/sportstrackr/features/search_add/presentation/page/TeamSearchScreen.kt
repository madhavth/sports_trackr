package com.madhav.sportstrackr.features.search_add.presentation.page

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.madhav.sportstrackr.core.constants.MyConstants
import com.madhav.sportstrackr.core.data.models.MyResponse
import com.madhav.sportstrackr.core.domain.entity.LeagueTeam
import com.madhav.sportstrackr.core.ui.viewmodels.AuthViewModel
import com.madhav.sportstrackr.core.ui.viewmodels.MainViewModel
import com.madhav.sportstrackr.core.ui.views.*
import com.madhav.sportstrackr.features.favorite.presentation.view_models.FavoriteViewModel
import com.madhav.sportstrackr.features.search_add.presentation.view_models.TeamSearchViewModel
import com.madhav.sportstrackr.features.search_add.presentation.views.SearchView

@Composable
fun TeamSearchScreen(
    teamsState: MyResponse<List<LeagueTeam>>,
    teamSearchViewModel: TeamSearchViewModel,
    mainViewModel: MainViewModel,
    showAddAlertDialog: Boolean = false,
    navigateRequest: (String) -> Unit = { _ -> },
) {
    val favoriteViewModel: FavoriteViewModel = hiltViewModel()
    val loginViewModel: AuthViewModel = hiltViewModel()

    val user = loginViewModel.currentUser.collectAsState(initial = null)

    Scaffold(
        topBar =  {
            TopAppBar() {
                Text("FIND YOUR FAVORITE TEAM",
                    style = TextStyle(
                        fontSize = 18.sp,
                        color = Color.White
                    ),
                    modifier = Modifier.padding(16.dp))
            }
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                mainViewModel.showAddAlertDialog()
            },
            backgroundColor = MaterialTheme.colors.primary,
                ) {
                Icon(Icons.Filled.Add, contentDescription = "add team")
            }
        }
    ) {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(it)) {
            SearchView(hint = "search here", onSearch = {
                teamSearchViewModel.performSearch(it)
            })

            // alert dialog view
            if (showAddAlertDialog)
                AlertDialog(onDismissRequest = {
                    mainViewModel.hideAddAlertDialog()
                }, buttons = {},
                    text = {
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.SpaceEvenly
                        ) {

                            Button(onClick = {
                                mainViewModel.hideAddAlertDialog()
                            }) {
                                Text("by team name")
                            }

                            Button(onClick = {
                                navigateRequest(MyConstants.SEARCH_ROUTE.SPORT_SEARCH)
                                mainViewModel.hideAddAlertDialog()
                            }) {
                                Text("by sport name")
                            }

                            Button(onClick = {
                                mainViewModel.hideAddAlertDialog()
                            }) {
                                Text("by country name")
                            }

                            Button(onClick = {

                            })
                            {
                                Text("by league name")
                            }

                        }
                    }
                )

            LazyNetworkResponseView(
                state = teamsState,
                modifier = Modifier.fillMaxSize(),
                successView = { leagueTeam ->
                    val isFavorite =
                        favoriteViewModel.checkIfFavoriteTeam(leagueTeam.idTeam).collectAsState(
                            initial = false
                        )

                    TeamOverView(
                        leagueTeam = leagueTeam, isFavorite = isFavorite.value,
                        onToggleFavorite = {
                            favoriteViewModel.toggleFavorite(isFavorite.value, leagueTeam)
                        },
                        isLoggedIn = user.value != null
                    )
                },
                emptyDataInfo = "No team found",
                emptyCheckCondition = { data ->
                    data.isEmpty() && teamSearchViewModel.searchQuery.isNotEmpty()
                },
                onErrorRetry = {
                    teamSearchViewModel.performSearch(teamSearchViewModel.searchQuery)
                },
                loadingAnim = com.madhav.sportstrackr.R.raw.searching
            )
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun SearchScreenPreview() {
    TeamSearchScreen(
        MyResponse.Loading,
        teamSearchViewModel = hiltViewModel(),
        mainViewModel = hiltViewModel()
    )
}