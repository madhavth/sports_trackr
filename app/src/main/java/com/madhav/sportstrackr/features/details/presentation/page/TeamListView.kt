package com.madhav.sportstrackr.features.details.presentation.page

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.madhav.sportstrackr.core.ui.viewmodels.MainViewModel
import com.madhav.sportstrackr.features.favorite.domain.entities.FavoriteTeam

@Composable
fun TeamListView(modifier: Modifier = Modifier, teams: List<FavoriteTeam>,
onClick: (FavoriteTeam) -> Unit = {}) {
    LazyColumn(modifier = modifier
        .fillMaxSize()
        .padding(16.dp)) {
        item {
            Text(
                text = "FAVORITE TEAMS",
                style = MaterialTheme.typography.body1.copy(
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }

        if (teams.isEmpty()) {
            item {
                Text(text = "No teams found")
            }
        }

        items(teams.size) { index ->
            val team = teams[index]
            TeamRow(team = team, onClick = onClick)
        }
    }
}

@Composable
fun TeamRow(team: FavoriteTeam, onClick: (FavoriteTeam) -> Unit = {}) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable {
                onClick(team)
            },
        shape = RoundedCornerShape(8.dp),
        elevation = 8.dp
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
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