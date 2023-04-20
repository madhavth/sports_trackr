package com.madhav.sportstrackr.features.search_add.presentation.page

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.madhav.sportstrackr.R
import com.madhav.sportstrackr.core.data.models.MyResponse
import com.madhav.sportstrackr.core.ui.views.LazyNetworkResponseView
import com.madhav.sportstrackr.core.ui.views.MyTopAppBar
import com.madhav.sportstrackr.features.search_add.domain.entities.Sport
import com.madhav.sportstrackr.features.search_add.presentation.view_models.SportsSearchViewModel
import com.madhav.sportstrackr.features.search_add.presentation.views.SearchView
import com.madhav.sportstrackr.features.search_add.presentation.views.SportItemView

@Composable
fun SportsSearchScreen(
    teamsState: MyResponse<List<Sport>>, modifier: Modifier = Modifier,
    onBackPressed: () -> Unit
) {
    val searchViewModel = hiltViewModel<SportsSearchViewModel>()
    GeneralSearchScreen(listState = teamsState,
        successView = { sport ->
            SportItemView(sport = sport)
        },
        onBackPressed = onBackPressed,
        appBarTitle = "Search by sports",
        performSearch = {
            searchViewModel.performSearch(it)
        },
        checkEmptyCondition = { data ->
            data.isEmpty() && searchViewModel.searchQuery.isNotEmpty()
        },
        fetchData = {
            searchViewModel.getSports()
        }
    )
}


@Preview(showSystemUi = true, showBackground = true)
@Composable
fun SportsSearchScreenPreview() {
    SportsSearchScreen(
        teamsState = MyResponse.Success(
            listOf(
                Sport(
                    name = "Football",
                    imgUrl = "https://www.thesportsdb.com/images/media/sport/txsupu1448818353.jpg"
                ),
                Sport(
                    name = "Basketball",
                    imgUrl = "https://www.thesportsdb.com/images/media/sport/txsupu1448818353.jpg"
                ),
                Sport(
                    name = "Cricket",
                    imgUrl = "https://www.thesportsdb.com/images/media/sport/txsupu1448818353.jpg"
                ),
            )
        ),
        onBackPressed = {}
    )
}