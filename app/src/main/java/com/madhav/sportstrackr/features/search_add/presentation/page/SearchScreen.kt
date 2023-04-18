package com.madhav.sportstrackr.features.search_add.presentation.page

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.madhav.sportstrackr.core.ui.viewmodels.LoginViewModel
import com.madhav.sportstrackr.core.ui.views.*
import com.madhav.sportstrackr.features.search_add.presentation.view_models.SearchViewModel
import com.madhav.sportstrackr.features.search_add.presentation.views.SearchView

@Composable
fun SearchScreen(modifier: Modifier = Modifier) {
    val searchViewModel = hiltViewModel<SearchViewModel>()
    val loginViewModel = hiltViewModel<LoginViewModel>()

    Column(modifier = Modifier.fillMaxSize()) {
        SearchView(hint = "search here", onSearch = {
            searchViewModel.searchTeam(it)
        })

        val teamsState = searchViewModel.teamSearchResult.collectAsState()
        LazyNetworkResponseView(
            state = teamsState.value,
            modifier = Modifier.fillMaxSize(),
            successView = {
                          leagueTeam ->
                TeamOverView(leagueTeam = leagueTeam)
            },
            emptyDataInfo = "No team found",
            emptyCheckCondition = {
                                  data ->
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
    SearchScreen()
}