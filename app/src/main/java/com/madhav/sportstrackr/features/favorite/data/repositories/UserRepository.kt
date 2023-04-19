package com.madhav.sportstrackr.features.favorite.data.repositories

import android.content.Context
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject
import javax.inject.Singleton

class UserRepository constructor(
    @ApplicationContext private val applicationContext: Context
) {
    private val googleSignClient = GoogleSignIn.getClient(
        applicationContext,
        GoogleSignInOptions.DEFAULT_SIGN_IN
    )

    private val _currentUser = MutableStateFlow(
        GoogleSignIn.getLastSignedInAccount(applicationContext)
    )
    val currentUser: StateFlow<GoogleSignInAccount?>
        get() = _currentUser

    fun setAccount(account: GoogleSignInAccount) {
        _currentUser.value = account
    }

    private fun updateUser() {
        _currentUser.value = GoogleSignIn.getLastSignedInAccount(applicationContext)
    }

    fun signOut() {
        googleSignClient.signOut()
        updateUser()
    }

}