package com.madhav.sportstrackr.features.events.domain.use_cases

import javax.inject.Inject

class EventUseCases @Inject constructor(
    val getPastEventUseCase: GetPastEventUseCase,
    val getUpcomingEventUseCase: GetUpcomingEventUseCase
) {
}