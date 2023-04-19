package com.madhav.sportstrackr.features.search_add.presentation.page

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.madhav.sportstrackr.core.data.models.MyResponse
import com.madhav.sportstrackr.core.domain.entity.LeagueTeam
import com.madhav.sportstrackr.core.ui.viewmodels.AuthViewModel
import com.madhav.sportstrackr.core.ui.views.*
import com.madhav.sportstrackr.features.favorite.presentation.view_models.FavoriteViewModel
import com.madhav.sportstrackr.features.search_add.presentation.view_models.SearchViewModel
import com.madhav.sportstrackr.features.search_add.presentation.views.SearchView

@Composable
fun SearchScreen(teamsState: MyResponse<List<LeagueTeam>>, modifier: Modifier = Modifier) {
    val searchViewModel = hiltViewModel<SearchViewModel>()
    val favoriteViewModel: FavoriteViewModel = hiltViewModel()
    val loginViewModel : AuthViewModel = hiltViewModel()

    val user = loginViewModel.currentUser.collectAsState(initial = null)

    Column(modifier = Modifier.fillMaxSize()) {
        SearchView(hint = "search here", onSearch = {
            searchViewModel.searchTeam(it)
        })

        LazyNetworkResponseView(
            state = teamsState,
            modifier = Modifier.fillMaxSize(),
            successView = { leagueTeam ->
                val isFavorite =
                    favoriteViewModel.checkIfFavoriteTeam(leagueTeam.idTeam).collectAsState(
                        initial = false
                    )

                TeamOverView(leagueTeam = leagueTeam, isFavorite = isFavorite.value,
                    onToggleFavorite = {
                        favoriteViewModel.toggleFavorite(isFavorite.value, leagueTeam)
                    },
                    isLoggedIn = user.value != null
                )
            },
            emptyDataInfo = "No team found",
            emptyCheckCondition = { data ->
                data.isEmpty() && searchViewModel.searchQuery.isNotEmpty()
            },
            onErrorRetry = {
                searchViewModel.searchTeam(searchViewModel.searchQuery)
            },
            loadingAnim = com.madhav.sportstrackr.R.raw.searching
        )
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun SearchScreenPreview() {
    SearchScreen(MyResponse.Loading)
}