package com.madhav.sportstrackr.features.details.presentation.views

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.madhav.sportstrackr.core.ui.views.CircleAvatarWithRemoveButton
import com.madhav.sportstrackr.features.favorite.domain.entities.FavoriteTeam

@Composable
fun FavoriteAvatarList(
    favoriteTeams: List<FavoriteTeam>? = listOf(),
    onRemoveClicked: (FavoriteTeam) -> Unit = {},
    onClicked: (FavoriteTeam) -> Unit = {},
    onAddClicked: () -> Unit = {},
    selectedTeamId: String? = null
) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {

        item {
            CircleAvatarWithRemoveButton(
                image = "https://media.istockphoto.com/id/688550958/vector/black-plus-sign-positive-symbol.jpg?s=612x612&w=0&k=20&c=0tymWBTSEqsnYYXWeWmJPxMotTGUwaGMGs6BMJvr7X4=",
                onRemoveClicked = {},
                onClicked = {  onAddClicked()  },
                modifier = Modifier.padding(horizontal = 8.dp),
                showRemoveButton = false,
                isSelected = false
            )
        }

        if (favoriteTeams?.isNotEmpty() == true) {
            items(favoriteTeams.size) {
                val item = favoriteTeams[it]
                CircleAvatarWithRemoveButton(
                    image = item.thumbUrl + "/tiny",
                    modifier = Modifier.padding(horizontal = 8.dp),
                    onRemoveClicked = { onRemoveClicked(item) },
                    onClicked = { onClicked(item) },
                    isSelected = selectedTeamId == item.id
                )
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun FavoriteAvatarListPreview() {
    FavoriteAvatarList(
        listOf(
            FavoriteTeam.from(
                id = "133604",
                name = "Manchester United",
                thumbUrl = "https://media.api-sports.io/football/teams/133604.png",
                bannerImage = "https://media.api-sports.io/football/teams/133604.png",
                sports = "Soccer"
            )
        )
    )
}
