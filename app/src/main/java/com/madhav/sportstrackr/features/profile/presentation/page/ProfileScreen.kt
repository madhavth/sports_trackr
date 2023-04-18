package com.madhav.sportstrackr.features.profile.presentation.page

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.madhav.sportstrackr.core.ui.viewmodels.LoginViewModel
import com.madhav.sportstrackr.core.ui.views.SignInPromptView

@Composable
fun ProfileScreen() {
    val loginViewModel= hiltViewModel<LoginViewModel>()
    val user = loginViewModel.currentUser.collectAsState().value

    if(user != null) {
        Button(onClick = {
            loginViewModel.signOut()
        }) {
            Text("Sign Out")
        }
    }
    else {
        SignInPromptView(info = "Sign in to view your profile")
    }
}