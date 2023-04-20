package com.madhav.sportstrackr.features.details.presentation.page

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.madhav.sportstrackr.core.ui.views.FavoriteButton
import com.madhav.sportstrackr.core.ui.views.NetworkImage
import com.madhav.sportstrackr.core.ui.views.SearchIcon
import com.madhav.sportstrackr.features.favorite.domain.entities.FavoriteTeam

@Composable
fun TeamListView(
    modifier: Modifier = Modifier, teams: List<FavoriteTeam>,
    onClick: (FavoriteTeam) -> Unit = {},
    onToggleFavorite: (String) -> Unit = {},
    searchIconClicked: (FavoriteTeam) -> Unit
) {
    LazyVerticalGrid(
        modifier = modifier
            .fillMaxSize()
            .padding(8.dp),
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        if (teams.isEmpty()) {
            item {
                Text(text = "No teams found")
            }
        }

        items(teams.size) { index ->
            val team = teams[index]
            TeamRow(
                team = team, onClick = onClick,
                onToggleFavorite = {
                    onToggleFavorite(team.id)
                },
                searchIconClicked = searchIconClicked
            )
        }
    }
}

@Composable
fun TeamRow(
    team: FavoriteTeam, onClick: (FavoriteTeam) -> Unit = {},
    isFavorite: Boolean = true,
    onToggleFavorite: (Boolean) -> Unit = {},
    searchIconClicked: (FavoriteTeam) -> Unit = {}
) {
    Card(
        modifier = Modifier
            .padding(vertical = 8.dp)
            .clickable {
                onClick(team)
            },
        shape = RoundedCornerShape(8.dp),
        elevation = 8.dp
    ) {
        Column(
            modifier = Modifier.padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                SearchIcon(onClick =
                {
                    searchIconClicked(team)
                }
                )
                FavoriteButton(isFavorite = isFavorite, onToggleFavorite = onToggleFavorite)
            }
            NetworkImage(
                url = team.thumbUrl,
                desc = team.name,
                modifier = Modifier.fillMaxWidth().height(100.dp)
            )
            Text(team.name)
            Text(team.sports)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TeamRowPreview() {
    val favoriteTeam = FavoriteTeam()
    favoriteTeam.id = "1234"
    favoriteTeam.name = "Arsenal"
    favoriteTeam.sports = "Soccer"
    favoriteTeam.bannerImage = ""
    favoriteTeam.thumbUrl = ""

    TeamRow(team = favoriteTeam)
}