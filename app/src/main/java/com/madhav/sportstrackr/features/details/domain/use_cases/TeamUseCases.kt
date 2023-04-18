package com.madhav.sportstrackr.features.details.domain.use_cases

import javax.inject.Inject

class TeamUseCases @Inject constructor(
    val getTeamUseCase: GetTeamUseCase
)