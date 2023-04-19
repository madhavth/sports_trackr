package com.madhav.sportstrackr.core.domain.usecase

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserUseCases @Inject constructor(
    val getCurrentUserUseCase: GetCurrentUserUseCase,
    val setAccountUseCase: SetAccountUseCase,
    val signOutUseCase: SignOutUseCase,
    val currentIdUseCase: CurrentIdUseCase
)