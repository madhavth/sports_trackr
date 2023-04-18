package com.madhav.sportstrackr.core.ui.views

import androidx.annotation.DrawableRes
import androidx.annotation.RawRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.android.gms.auth.api.identity.SignInPassword
import com.google.android.gms.auth.api.signin.GoogleSignInAccount

@Composable
fun SignInPromptView(
    info: String,
    modifier: Modifier = Modifier,
    @RawRes resId: Int = com.madhav.sportstrackr.R.raw.locked,
    onSignedIn: (GoogleSignInAccount) -> Unit = {},
) {
    Box(
        modifier = modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            
            LottieAnim(resId = resId, modifier = Modifier.fillMaxSize(0.4f))
            
            Text(
                text = info,
                style = MaterialTheme.typography.h6,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(16.dp))

            GoogleSignInButton(onSignInComplete = onSignedIn)
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SignInPromptViewPreview() {
    SignInPromptView(info = "Sign in to continue",
    resId = com.madhav.sportstrackr.R.raw.locked
        )
}
