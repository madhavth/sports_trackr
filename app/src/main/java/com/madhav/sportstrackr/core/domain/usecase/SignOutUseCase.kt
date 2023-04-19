package com.madhav.sportstrackr.core.domain.usecase

import com.madhav.sportstrackr.features.favorite.data.repositories.UserRepository
import javax.inject.Inject

class SignOutUseCase @Inject constructor(
    private val userRepository: UserRepository
){
    fun execute() = userRepository.signOut()
}