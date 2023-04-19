package com.madhav.sportstrackr.core.ui.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.madhav.sportstrackr.features.favorite.data.repositories.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import timber.log.Timber
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(
    val app: Application,
    private val userRepository: UserRepository
) : AndroidViewModel(app) {
    val currentUser: StateFlow<GoogleSignInAccount?>
        get() = userRepository.currentUser

    fun setAccount(account: GoogleSignInAccount) {
        userRepository.setAccount(account)
    }

    fun signOut() {
        userRepository.signOut()
    }

}