package com.madhav.sportstrackr.features.details.presentation.page

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.madhav.sportstrackr.core.ui.views.NetworkResponseView
import com.madhav.sportstrackr.features.details.presentation.view_models.DetailsViewModel

@Composable
fun DetailsScreen()
{
    val detailsViewModel = viewModel<DetailsViewModel>()
    val detailsState = detailsViewModel.teamDetailsState.collectAsState()

    NetworkResponseView(state = detailsState.value, successView = { data ->
        LeagueTeamDetailsLoadedScreen(
            team = data,
            isFavorite = detailsViewModel.isFavorite,
            onFavoriteToggle = {
                detailsViewModel.toggleFavorite(data)
            }
        )
    })

}

@Preview(showBackground = true)
@Composable
fun DetailsScreenPreview()
{
    DetailsScreen()
}