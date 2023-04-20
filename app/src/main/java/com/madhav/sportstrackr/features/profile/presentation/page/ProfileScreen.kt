package com.madhav.sportstrackr.features.profile.presentation.page

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.madhav.sportstrackr.core.ui.viewmodels.AuthViewModel
import com.madhav.sportstrackr.core.ui.views.SignInPromptView

@Composable
fun ProfileScreen() {
    val authViewModel = hiltViewModel<AuthViewModel>()
    val user = authViewModel.currentUser.collectAsState().value
    val userInfo = authViewModel.userInfo.collectAsState(initial = null).value

    if (user != null) {
        MyProfileView(userInfo, logoutPressed = {
            authViewModel.signOut()
        })
    } else {
        SignInPromptView(info = "Sign in to view your profile")
    }
}