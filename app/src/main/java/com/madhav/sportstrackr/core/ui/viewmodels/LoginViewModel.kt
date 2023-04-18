package com.madhav.sportstrackr.core.ui.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import timber.log.Timber
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(val app: Application) : AndroidViewModel(app) {
    private val googleSignClient = GoogleSignIn.getClient(
        app.applicationContext,
        GoogleSignInOptions.DEFAULT_SIGN_IN
    )

    private val _currentUser = MutableStateFlow(
        GoogleSignIn.getLastSignedInAccount(app.applicationContext)
    )
    val currentUser: StateFlow<GoogleSignInAccount?>
        get() = _currentUser

    private fun updateUser() {
        _currentUser.value = GoogleSignIn.getLastSignedInAccount(app.applicationContext)
    }

    fun signOut() {
        googleSignClient.signOut()
        updateUser()
    }

    fun setAccount(account: GoogleSignInAccount) {
        _currentUser.value = account
    }
}