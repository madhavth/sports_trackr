package com.madhav.sportstrackr.core.ui.views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.madhav.sportstrackr.core.domain.entity.LeagueTeam
import com.madhav.sportstrackr.core.data.models.SocialMediaInfo

@Composable
fun TeamOverView(
    leagueTeam: LeagueTeam, modifier: Modifier = Modifier,
    isFavorite: Boolean = false,
    onToggleFavorite: (Boolean) -> Unit = {}
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = 8.dp,
        backgroundColor = Color.White
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .weight(0.8f)
            ) {
                Text(
                    text = leagueTeam.strTeam,
                    style = MaterialTheme.typography.h6,
                )
                Text(text = leagueTeam.strSport)
                Text(text = leagueTeam.strCountry)
            }

            Column(modifier = Modifier.weight(0.2f),
            horizontalAlignment = Alignment.End
                ) {
                FavoriteButton(isFavorite = isFavorite, onToggleFavorite = {
                    onToggleFavorite(!isFavorite)
                })
                AsyncImage(
                    model = leagueTeam.teamBadge,
                    contentDescription = leagueTeam.teamBadge,
                    modifier = Modifier
                        .padding(8.dp)
                )
            }
        }
    }
}

@Preview
@Composable
fun SportsTeamSectionPreview() {
    TeamOverView(
        leagueTeam = LeagueTeam(
            strTeam = "Manchester United",
            idTeam = "123",
            socialMediaInfo = SocialMediaInfo(
                instagram = "https://www.instagram.com/manutd/",
                facebook = "https://www.facebook.com/manutd/",
                twitter = "https://twitter.com/ManUtd",
                website = "https://www.manutd.com/"
            ),
            strCountry = "England",
            idLeague = "123",
            strLeague = "Premier League",
            strSport = "Soccer",
            idAPIfootball = "123",
            strStadiumLocation = "Manchester",
            strStadiumThumb = "https://www.manutd.com/",
            strStadiumDescription = "Old Trafford",
            strStadium = "Old Trafford",
            strDescriptionEN = "Manchester United Football Club is a professional football club based in Old Trafford, Greater Manchester, England, that competes in the Premier League, the top flight of English football. Nicknamed \"the Red Devils\", the club was founded as Newton Heath LYR Football Club in 1878, changed its name to Manchester United in 1902 and moved to Old Trafford in 1910.",
            intStadiumCapacity = "75000",
            idSoccerXML = "123",
            intFormedYear = "1878",
            intLoved = "1",
            strAlternate = "",
            strKeywords = "",
            strManager = "",
            strRSS = ""
        )
    )
}