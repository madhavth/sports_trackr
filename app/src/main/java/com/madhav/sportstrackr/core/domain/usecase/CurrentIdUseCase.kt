package com.madhav.sportstrackr.core.domain.usecase

import com.madhav.sportstrackr.features.favorite.data.repositories.UserRepository
import javax.inject.Inject

class CurrentIdUseCase @Inject constructor(
    val userRepository: UserRepository
){
    fun execute() = userRepository.userId
}