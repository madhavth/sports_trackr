package com.madhav.sportstrackr.core.domain.usecase

import com.madhav.sportstrackr.features.favorite.data.repositories.UserRepository
import javax.inject.Inject

class GetCurrentUserUseCase @Inject constructor(
    private val userRepository: UserRepository
){
    fun execute() = userRepository.currentUser
}