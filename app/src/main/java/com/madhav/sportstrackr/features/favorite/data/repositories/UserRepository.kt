package com.madhav.sportstrackr.features.favorite.data.repositories

import android.content.Context
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.madhav.sportstrackr.core.app.MyApplication
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(
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

    private val _userId = MutableStateFlow(
        GoogleSignIn.getLastSignedInAccount(applicationContext)?.id
    )

    val userId: StateFlow<String?>
        get() = _userId

    fun setAccount(account: GoogleSignInAccount) {
        _currentUser.value = account
        _userId.value = account.id
    }

    private fun updateUser() {
        _currentUser.value = null
        _userId.value = null
    }

    fun signOut() {
        googleSignClient.signOut()
        updateUser()
    }

}