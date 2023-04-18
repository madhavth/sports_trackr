package com.madhav.sportstrackr.core.ui.views

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.madhav.sportstrackr.core.domain.entity.LeagueTeam
import com.madhav.sportstrackr.core.data.models.SocialMediaInfo

@Composable
fun TeamOverView(leagueTeam: LeagueTeam, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier.fillMaxWidth()
            .padding(vertical = 8.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = 8.dp,
        backgroundColor = Color.White
    ) {
        Text(
            text = leagueTeam.strTeam,
            modifier = Modifier.padding(16.dp),
            style = MaterialTheme.typography.h6,
        )
    }
}

@Preview
@Composable
fun SportsTeamSectionPreview() {
    TeamOverView(leagueTeam = LeagueTeam(
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
        strStadiumLocation =  "Manchester",
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
    ))
}