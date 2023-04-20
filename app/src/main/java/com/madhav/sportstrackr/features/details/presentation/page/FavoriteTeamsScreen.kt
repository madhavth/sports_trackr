package com.madhav.sportstrackr.features.details.presentation.page

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.navArgument
import com.madhav.sportstrackr.core.constants.MyConstants
import com.madhav.sportstrackr.features.favorite.domain.entities.FavoriteTeam
import com.madhav.sportstrackr.features.favorite.presentation.view_models.FavoriteViewModel

@Composable
fun FavoriteTeamsScreen(
    favoriteTeams: List<FavoriteTeam>,
    modifier: Modifier = Modifier,
    navController: NavController,
    favoriteViewModel: FavoriteViewModel
) {
    Scaffold(
        topBar = {
            TopAppBar() {
                Text(
                    text = "FAVORITE TEAMS",
                    style = MaterialTheme.typography.body1.copy(
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    ),
                    modifier = Modifier.padding(vertical = 16.dp, horizontal = 16.dp)
                )
            }
        }
    ) {
        it ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            TeamListView(
                modifier = modifier, teams = favoriteTeams,
                onClick = {
                    navController.navigate(MyConstants.DETAILS_ROUTE.TEAM_DETAILS + "/${it.id}/${it.name}") {
                        navArgument("arg") {
                            defaultValue = it.id
                        }
                    }
                },
                onToggleFavorite = {
                    favoriteViewModel.removeFavorite(it)
                },
                searchIconClicked = {
                    navController.navigate(MyConstants.DETAILS_ROUTE.PLAYER_SEARCH + "/${it.id}/${it.name}")
                }
            )
        }
    }

}