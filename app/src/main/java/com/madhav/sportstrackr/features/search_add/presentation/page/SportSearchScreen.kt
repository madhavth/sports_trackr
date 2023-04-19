package com.madhav.sportstrackr.features.search_add.presentation.page

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.madhav.sportstrackr.R
import com.madhav.sportstrackr.core.data.models.MyResponse
import com.madhav.sportstrackr.core.domain.entity.LeagueTeam
import com.madhav.sportstrackr.core.ui.viewmodels.AuthViewModel
import com.madhav.sportstrackr.core.ui.views.LazyNetworkResponseView
import com.madhav.sportstrackr.features.search_add.domain.entities.Sport
import com.madhav.sportstrackr.features.search_add.presentation.view_models.SportsSearchViewModel
import com.madhav.sportstrackr.features.search_add.presentation.views.SearchView
import com.madhav.sportstrackr.features.search_add.presentation.views.SportItemView

@Composable
fun SportsSearchScreen(teamsState: MyResponse<List<Sport>>, modifier: Modifier = Modifier) {
    val searchViewModel = hiltViewModel<SportsSearchViewModel>()

    Column(modifier = Modifier.fillMaxSize()) {
        SearchView(hint = "search sports here", onQueryChanged = {
            searchViewModel.performSearch(it)
        }, onSearch =  {
            searchViewModel.performSearch(it)
        })

        LazyNetworkResponseView(
            state = teamsState,
            modifier = Modifier.fillMaxSize(),
            successView = { sport ->
                SportItemView(sport = sport)
            },
            emptyDataInfo = "No such sport found",
            emptyCheckCondition = { data ->
                data.isEmpty() && searchViewModel.searchQuery.isNotEmpty()
            },
            onErrorRetry = {
                searchViewModel.getSports()
            },
            loadingAnim = R.raw.searching
        )
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun SportsSearchScreenPreview() {
    TeamSearchScreen(MyResponse.Loading)
}