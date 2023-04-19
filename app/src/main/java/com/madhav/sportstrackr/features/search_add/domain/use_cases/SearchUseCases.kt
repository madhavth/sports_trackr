package com.madhav.sportstrackr.features.search_add.domain.use_cases

import javax.inject.Inject


class SearchUseCases @Inject constructor(
    val searchTeamUseCase: SearchTeamUseCase,
    val sportsUseCase: FetchSportsUseCase
){
}