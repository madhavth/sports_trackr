package com.madhav.sportstrackr.features.details.presentation.page

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.madhav.sportstrackr.core.ui.views.BackButton
import com.madhav.sportstrackr.core.ui.views.NetworkResponseView
import com.madhav.sportstrackr.features.details.presentation.view_models.DetailsViewModel
import com.madhav.sportstrackr.features.favorite.presentation.view_models.FavoriteViewModel

@Composable
fun DetailsScreen(
    backPressed: () -> Unit = {},
    teamId: String?,
    favoriteViewModel: FavoriteViewModel,
    onClickedSearch: (String) -> Unit
) {
    val detailsViewModel = hiltViewModel<DetailsViewModel>()
    val detailsState = detailsViewModel.teamDetailsState.collectAsState()

    LaunchedEffect(key1 = true, block = {
        detailsViewModel.getTeamDetails(teamId)
    })

    Column(modifier = Modifier.fillMaxSize()) {

        BackButton(backPressed = backPressed)

        NetworkResponseView(state = detailsState.value,
            successView = { data ->
                val isFavorite =
                    favoriteViewModel.isFavoriteTeam(data.idTeam).collectAsState(initial = false)

                LeagueTeamDetailsLoadedScreen(
                    team = data,
                    isFavorite = isFavorite.value,
                    onFavoriteToggle = {
                        favoriteViewModel.toggleFavorite(!it, data)
                    },
                    onClickSearch = onClickedSearch
                )
            },
            onRetry = {
                detailsViewModel.getTeamDetails(detailsViewModel.teamId)
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DetailsScreenPreview() {
    DetailsScreen(teamId = "123454", favoriteViewModel = hiltViewModel(),
    onClickedSearch =  {}
        )
}
