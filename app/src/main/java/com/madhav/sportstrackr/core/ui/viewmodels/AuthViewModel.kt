package com.madhav.sportstrackr.core.ui.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.firebase.auth.UserInfo
import com.madhav.sportstrackr.core.domain.entity.MyUserInfo
import com.madhav.sportstrackr.core.domain.entity.toMyUserInfo
import com.madhav.sportstrackr.core.domain.usecase.UserUseCases
import com.madhav.sportstrackr.features.favorite.data.repositories.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


@HiltViewModel
class AuthViewModel @Inject constructor(
    val app: Application,
    private val usersUseCases: UserUseCases
) : AndroidViewModel(app) {
    val currentUser: StateFlow<GoogleSignInAccount?>
        get() = usersUseCases.getCurrentUserUseCase.execute()

    val userInfo: Flow<MyUserInfo?>
        get() = currentUser.map {
            it?.toMyUserInfo()
        }


    fun setAccount(account: GoogleSignInAccount) {
        usersUseCases.setAccountUseCase.execute(account)
    }

    fun signOut() {
        usersUseCases.signOutUseCase.execute()
    }

}