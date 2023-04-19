package com.madhav.sportstrackr.core.ui.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.madhav.sportstrackr.core.domain.usecase.UserUseCases
import com.madhav.sportstrackr.features.favorite.data.repositories.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject


@HiltViewModel
class AuthViewModel @Inject constructor(
    val app: Application,
    private val usersUseCases: UserUseCases
) : AndroidViewModel(app) {
    val currentUser: StateFlow<GoogleSignInAccount?>
        get() = usersUseCases.getCurrentUserUseCase.execute()

    fun setAccount(account: GoogleSignInAccount) {
        usersUseCases.setAccountUseCase.execute(account)
    }

    fun signOut() {
        usersUseCases.signOutUseCase.execute()
    }

}