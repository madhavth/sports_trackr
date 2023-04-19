package com.madhav.sportstrackr.features.details.presentation.page

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.madhav.sportstrackr.R
import com.madhav.sportstrackr.core.ui.views.NetworkResponseView
import com.madhav.sportstrackr.features.details.presentation.view_models.DetailsViewModel
import com.madhav.sportstrackr.features.favorite.presentation.view_models.FavoriteViewModel

@Composable
fun DetailsScreen(backPressed: ()-> Unit = {}) {
    val detailsViewModel = hiltViewModel<DetailsViewModel>()
    val detailsState = detailsViewModel.teamDetailsState.collectAsState()

    Column(modifier = Modifier.fillMaxSize()) {
        Row() {
            IconButton(onClick = {
                // navigate back to previous screen
                backPressed()
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_back),
                    contentDescription = "back button"
                )
            }
        }

        NetworkResponseView(state = detailsState.value,
            successView = { data ->
                val favoriteViewModel = hiltViewModel<FavoriteViewModel>()
                val isFavorite =
                    favoriteViewModel.isFavoriteTeam(data.idTeam).collectAsState(initial = false)

                LeagueTeamDetailsLoadedScreen(
                    team = data,
                    isFavorite = isFavorite.value,
                    onFavoriteToggle = {
                        favoriteViewModel.toggleFavorite(!it, data)
                    }
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
    DetailsScreen()
}
