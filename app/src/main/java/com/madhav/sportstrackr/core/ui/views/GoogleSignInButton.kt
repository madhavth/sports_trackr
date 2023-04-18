package com.madhav.sportstrackr.core.ui.views

import android.app.Activity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.madhav.sportstrackr.R
import com.madhav.sportstrackr.core.ui.viewmodels.LoginViewModel
import timber.log.Timber

const val RC_SIGN_IN = 100

@Composable
fun GoogleSignInButton(onSignInComplete: (GoogleSignInAccount) -> Unit) {
    val context = LocalContext.current
    val loginViewModel: LoginViewModel = hiltViewModel()

    val activityResultLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
            val account = task.getResult(ApiException::class.java)
            loginViewModel.setAccount(account!!)
            onSignInComplete(account)
        } else {
            // Google Sign-In failed
            Timber.e(context.resources.getString(R.string.failed_signin))
        }
    }

    val googleSignInClient = remember {
        GoogleSignIn.getClient(context, GoogleSignInOptions.DEFAULT_SIGN_IN)
    }

    Button(onClick = {
        activityResultLauncher.launch(googleSignInClient.signInIntent)
    }) {
        Text(text = stringResource(R.string.sign_in_google))
    }


//    DisposableEffect(key1 = googleSignInClient) {
//        val task = googleSignInClient.silentSignIn()
//        if (task.isSuccessful) {
//            // User already signed in, pass the account to the callback
//            loginViewModel.setAccount(task.result)
//            onSignInComplete(task.result!!)
//        } else {
//            // User not signed in, show the Sign-In button
//           activityResultLauncher.launch(googleSignInClient.signInIntent)
//        }
//        onDispose { }
//    }
}