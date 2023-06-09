package com.madhav.sportstrackr.features.details.presentation.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.LinearGradient
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.madhav.sportstrackr.core.ui.views.CircleAvatar
import com.madhav.sportstrackr.core.ui.views.CircleAvatarWithRemoveButton
import com.madhav.sportstrackr.core.ui.views.ImageType
import com.madhav.sportstrackr.features.favorite.domain.entities.FavoriteTeam

@Composable
fun FavoriteAvatarList(
    favoriteTeams: List<FavoriteTeam>? = listOf(),
    onRemoveClicked: (FavoriteTeam) -> Unit = {},
    onClicked: (FavoriteTeam) -> Unit = {},
    onAddClicked: () -> Unit = {},
    selectedTeamId: String? = null
) {
    Surface(
        modifier = Modifier.fillMaxWidth()
        // add gradient background
//        .background(
//            brush = Brush.linearGradient(
//                colors = listOf(
//                    Color.Black,
//                    Color.White
//                ),
//                start = Offset(0f, 0f),
//                end = Offset(1f, 1f)
//            )
//        )
    ) {
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.LightGray.copy(0.8f))
//                .background(
//                    brush = Brush.linearGradient(
//                        colors = listOf(
//                            Color.Green,
//                            Color.White
//                        ),
//                        start = Offset(0f, 0f),
//                        end = Offset(1f, 1f)
//                    )
//                )
                .padding(8.dp)
        ) {

            item {
                CircleAvatarWithRemoveButton(
                    image = "https://media.istockphoto.com/id/688550958/vector/black-plus-sign-positive-symbol.jpg?s=612x612&w=0&k=20&c=0tymWBTSEqsnYYXWeWmJPxMotTGUwaGMGs6BMJvr7X4=",
                    onRemoveClicked = {},
                    onClicked = { onAddClicked() },
                    modifier = Modifier.padding(horizontal = 8.dp),
                    showRemoveButton = false,
                    isSelected = true
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
