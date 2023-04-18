package com.madhav.sportstrackr.features.details.presentation.page

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.madhav.sportstrackr.core.ui.views.FavoriteButton
import com.madhav.sportstrackr.core.ui.views.SocialMediaRow
import com.madhav.sportstrackr.core.domain.entity.LeagueTeam
import com.madhav.sportstrackr.core.models.SocialMediaInfo
import com.madhav.sportstrackr.features.details.presentation.views.LeagueInformationView
import com.madhav.sportstrackr.features.details.presentation.views.SportsTeamSection
import com.madhav.sportstrackr.features.details.presentation.views.TeamDescription

@Composable
fun LeagueTeamDetailsLoadedScreen(
    team: LeagueTeam,
    isFavorite: Boolean,
    onFavoriteToggle: (Boolean) -> Unit
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(scrollState)
    ) {
        // Team name section
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.End

        ) {
            FavoriteButton(isFavorite = isFavorite, onToggleFavorite = onFavoriteToggle)
        }

        SportsTeamSection(name = team.strTeam)

        Spacer(modifier = Modifier.height(24.dp))
        LeagueInformationView(leagueName = team.strLeague, country = team.strCountry)

        Spacer(modifier = Modifier.height(8.dp))

        TeamDescription(description = team.strDescriptionEN?: "")

        // League information section

        SocialMediaRow(
            socialMediaInfo = team.socialMediaInfo
        )
    }

}

@Preview(showSystemUi = true)
@Composable
fun LeagueTeamScreenPreview() {
    LeagueTeamDetailsLoadedScreen(team = LeagueTeam(
        idTeam = "133604",
        idSoccerXML = "null",
        idAPIfootball = "null",
        intLoved = "null",
        strTeam = "Arsenal",
        strAlternate = "null",
        intFormedYear = "1886",
        strSport = "Soccer",
        strLeague = "English Premier League",
        idLeague = "4328",
        strManager = "Mikel Arteta",
        strStadium = "Emirates Stadium",
        strKeywords = "Gunners",
        strRSS = "http://www.arsenal.com/rss.xml",
        strStadiumThumb = "https://www.thesportsdb.com/images/media/venue/2v/xxvqyv1554818300.jpg",
        intStadiumCapacity = "60355",
        socialMediaInfo = SocialMediaInfo( website = "http://www.arsenal.com",
        facebook = "https://www.facebook.com/Arsenal",
        twitter = "https://twitter.com/Arsenal",
        instagram = "https://www.instagram.com/arsenal"
        ),
        strCountry = "England",
        strDescriptionEN = "Arsenal Football Club is a professional football club based in Highbury, London, England, that plays in the Premier League, the top flight of English football. The club has won 13 League titles, a record 13 FA Cups, two League Cups, the League Centenary Trophy, 15 FA Community Shields, one UEFA Cup Winners' Cup, and one Inter-Cities Fairs Cup. Arsenal holds the record for the longest uninterrupted period in the English top flight, and has won the second most top-flight matches in English football history.",
        strStadiumDescription = "Emirates Stadium is a football stadium in Holloway, London, England, and the home of Arsenal Football Club. It opened in 2006, replacing Highbury as the club's home ground. The stadium is located in the north London borough of Islington, and is situated 500 metres north of Highbury & Islington station. The stadium is also served by Holloway Road station, which is 0.5 miles away. The stadium is a UEFA Category 4 stadium, which means it can host UEFA Champions League and UEFA Europa League matches. The stadium is also a FIFA Category 4 stadium, which means it can host FIFA World Cup matches. The stadium is also a UEFA Elite Stadium, which means it can host UEFA Super Cup matches. The stadium is also a FIFA Elite Stadium, which means it can host FIFA Club World Cup matches. The stadium is also a UEFA Elite Stadium, which means it can host UEFA Super Cup matches. The stadium is also a FIFA Elite Stadium, which means it can host FIFA Club World Cup matches.",
        strStadiumLocation = "Holloway, London",
    ),
        isFavorite = false, onFavoriteToggle = {

        })
}