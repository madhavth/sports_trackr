package com.madhav.sportstrackr.features.search_add.presentation.page

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.madhav.sportstrackr.core.ui.views.TeamOverView
import com.madhav.sportstrackr.features.favorite.presentation.view_models.FavoriteViewModel
import com.madhav.sportstrackr.features.search_add.presentation.view_models.TeamLeaguesSearchViewModel

@Composable
fun LeagueTeamSearchScreen(
    modifier: Modifier = Modifier,
    onBackPressed: () -> Unit,
    league: String? = null,
    favoriteViewModel: FavoriteViewModel,
    isLoggedIn: Boolean
) {
    val leagueTeamSearchViewModel : TeamLeaguesSearchViewModel = hiltViewModel()
    val leagueTeamsState = leagueTeamSearchViewModel.leagueSearchResult.collectAsState()

    GeneralSearchScreen(
        listState = leagueTeamsState.value,
        appBarTitle = "Search League Teams",
        onBackPressed = onBackPressed,
        successView = {
            leagueTeam ->
            val isFavorite =
                favoriteViewModel.checkIfFavoriteTeam(leagueTeam.idTeam).collectAsState(
                    initial = false
                )

            TeamOverView(
                leagueTeam = leagueTeam, isFavorite = isFavorite.value,
                onToggleFavorite = {
                    favoriteViewModel.toggleFavorite(isFavorite.value, leagueTeam)
                },
                isLoggedIn = isLoggedIn,
                onClick =  {

                }
            )
        },
        checkEmptyCondition = {
                              it.isEmpty() &&  leagueTeamSearchViewModel.searchQuery.isEmpty()
        },
        fetchData = {
            leagueTeamSearchViewModel.getLeagueTeams(league)
        },
        performSearch = {
            leagueTeamSearchViewModel.performSearch(it)
        },
    )
}
