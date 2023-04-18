package com.madhav.sportstrackr.features.search_add.presentation.page

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.madhav.sportstrackr.core.models.MyResponse
import com.madhav.sportstrackr.core.ui.views.ErrorView
import com.madhav.sportstrackr.core.ui.views.LoadingView
import com.madhav.sportstrackr.core.ui.views.TeamOverView
import com.madhav.sportstrackr.features.search_add.presentation.view_models.SearchViewModel
import com.madhav.sportstrackr.features.search_add.presentation.views.SearchView

@Composable
fun SearchScreen(modifier: Modifier = Modifier) {
    val searchViewModel = hiltViewModel<SearchViewModel>()

    Column(modifier =Modifier.fillMaxSize()) {
        SearchView(hint = "search here", onSearch = {
            searchViewModel.searchTeam(it)
        })

        val teamsState = searchViewModel.teamSearchResult.collectAsState()

        LazyColumn(contentPadding = PaddingValues(8.dp),
        modifier = Modifier.fillMaxSize()
            ) {
            item {
                Spacer(modifier = Modifier.height(8.dp))
            }

            when (val state = teamsState.value) {
                is MyResponse.Loading -> {
                    item {
                        LoadingView(modifier = Modifier.weight(1f).padding(
                            top = 120.dp
                        ))
                    }
                }
                is MyResponse.Success -> {
                    items(state.data.size) {
                        TeamOverView(leagueTeam = state.data[it])
                    }
                }
                is MyResponse.Error -> {
                    item {
                        ErrorView(message = state.error, modifier = Modifier
                            .weight(1f)
                            .padding(top = 120.dp),
                        onRetry =  {
                            searchViewModel.searchTeam(searchViewModel.searchQuery)
                        }
                            )
                    }
                }
            }
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun SearchScreenPreview() {
    SearchScreen()
}