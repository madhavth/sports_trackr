package com.madhav.sportstrackr.features.search_add.presentation.page

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.madhav.sportstrackr.core.domain.entity.League
import com.madhav.sportstrackr.core.ui.views.LeagueItem
import com.madhav.sportstrackr.features.search_add.presentation.view_models.LeagueSearchViewModel

@Composable
fun LeagueSearchScreen(modifier: Modifier = Modifier,
                       onBackPressed: () -> Unit,
                       sports: String? = null,
                       country: String? = null,
                       ) {

    val leagueSearchViewModel = hiltViewModel<LeagueSearchViewModel>()
    val leaguesListState = leagueSearchViewModel.leaguesSearchResult.collectAsState()

    GeneralSearchScreen(
        listState = leaguesListState.value,
        appBarTitle = "Search by league",
        successView = {
                      LeagueItem(league = it)
        },
        checkEmptyCondition = {
            it.isEmpty() && leagueSearchViewModel.searchQuery.isNotEmpty()
        },
        fetchData = {
            leagueSearchViewModel.getLeagues(sports, country)
        },
        performSearch = {
            leagueSearchViewModel.performSearch(it)
        }
    )

}