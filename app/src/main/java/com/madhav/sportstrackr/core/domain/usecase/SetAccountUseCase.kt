package com.madhav.sportstrackr.core.domain.usecase

import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.madhav.sportstrackr.features.favorite.data.repositories.UserRepository
import javax.inject.Inject

class SetAccountUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    fun execute(account: GoogleSignInAccount) {
        userRepository.setAccount(account)
    }
}