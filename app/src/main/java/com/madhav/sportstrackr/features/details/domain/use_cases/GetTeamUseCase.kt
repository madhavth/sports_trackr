package com.madhav.sportstrackr.features.details.domain.use_cases

import com.madhav.sportstrackr.core.models.SocialMediaInfo
import com.madhav.sportstrackr.features.details.domain.entities.LeagueTeam
import com.madhav.sportstrackr.features.details.domain.repositories.TeamRepository
import javax.inject.Inject

class GetTeamUseCase @Inject constructor(
    private val repository: TeamRepository
) {

    suspend fun execute(teamId: String): LeagueTeam {
        val teamDetails = repository.getTeamDetails(teamId)
        return teamDetails.teams.map {
            LeagueTeam(
                idTeam = it.idTeam,
                idSoccerXML = it.idSoccerXML,
                idAPIfootball = it.idAPIfootball,
                intLoved = it.intLoved,
                strTeam = it.strTeam,
                strTeamShort = it.strTeamShort,
                strAlternate = it.strAlternate,
                intFormedYear = it.intFormedYear,
                strSport = it.strSport,
                strLeague = it.strLeague,
                idLeague = it.idLeague,
                strManager = it.strManager,
                strStadium = it.strStadium,
                strKeywords = it.strKeywords,
                strRSS = it.strRSS,
                strStadiumThumb = it.strStadiumThumb,
                strStadiumDescription = it.strStadiumDescription,
                strStadiumLocation = it.strStadiumLocation,
                intStadiumCapacity = it.intStadiumCapacity,
                socialMediaInfo = SocialMediaInfo(
                    facebook = it.strFacebook,
                    twitter = it.strTwitter,
                    instagram = it.strInstagram,
                    website = it.strWebsite
                ),
                strCountry = it.strCountry,
                strDescriptionEN = it.strDescriptionEN
            )
        }.first()
    }
}