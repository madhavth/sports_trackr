package com.madhav.sportstrackr.features.details.presentation.page

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.madhav.sportstrackr.core.ui.views.BackButton
import com.madhav.sportstrackr.core.ui.views.NetworkResponseView
import com.madhav.sportstrackr.core.ui.views.SearchIcon
import com.madhav.sportstrackr.features.details.presentation.view_models.DetailsViewModel
import com.madhav.sportstrackr.features.favorite.presentation.view_models.FavoriteViewModel

@Composable
fun DetailsScreen(
    backPressed: () -> Unit = {},
    teamId: String?,
    teamName: String?,
    favoriteViewModel: FavoriteViewModel,
    onClickedSearch: (String) -> Unit,
    isLoggedIn: Boolean
) {
    val detailsViewModel = hiltViewModel<DetailsViewModel>()
    val detailsState = detailsViewModel.teamDetailsState.collectAsState()

    LaunchedEffect(key1 = true, block = {
        detailsViewModel.getTeamDetails(teamId)
    })
    Scaffold(
        topBar = {
            TopAppBar() {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier =Modifier.fillMaxWidth()
                ) {
                    BackButton(backPressed = backPressed)

                    Text(
                        text = "Team Details",
                        modifier = Modifier
                            .padding(16.dp),
                        style = TextStyle(
                            color = Color.White,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )

                    SearchIcon(
                        onClick = {
                            onClickedSearch(teamName!!)
                        },
                        color = Color.White
                    )
                }
            }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {


            NetworkResponseView(state = detailsState.value,
                successView = { data ->
                    val isFavorite =
                        favoriteViewModel.isFavoriteTeam(data.idTeam)
                            .collectAsState(initial = false)

                    LeagueTeamDetailsLoadedScreen(
                        team = data,
                        isFavorite = isFavorite.value,
                        onFavoriteToggle = {
                            favoriteViewModel.toggleFavorite(!it, data)
                        },
                        onClickSearch = onClickedSearch,
                        isLoggedIn = isLoggedIn,
                    )

                },
                onRetry = {
                    detailsViewModel.getTeamDetails(detailsViewModel.teamId)
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DetailsScreenPreview() {
    DetailsScreen(teamId = "123454", teamName = "", favoriteViewModel = hiltViewModel(),
        onClickedSearch = {},
        isLoggedIn = true
    )
}
