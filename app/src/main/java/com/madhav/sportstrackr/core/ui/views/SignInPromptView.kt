package com.madhav.sportstrackr.core.ui.views

import androidx.annotation.DrawableRes
import androidx.annotation.RawRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.android.gms.auth.api.identity.SignInPassword
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import java.util.*

@Composable
fun SignInPromptView(
    info: String,
    modifier: Modifier = Modifier,
    @RawRes resId: Int = com.madhav.sportstrackr.R.raw.locked,
    onSignedIn: (GoogleSignInAccount) -> Unit = {},
) {
    Scaffold(
        topBar =  {
            TopAppBar() {
                Row(
//                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        "Login Required".uppercase(Locale.ENGLISH),
                        style = TextStyle(
                            fontSize = 18.sp,
                            textAlign = TextAlign.Center,
                            color = Color.White
                        ),
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
    ) {
        Box(
            modifier = modifier
                .fillMaxSize()
                .padding(it)
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
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SignInPromptViewPreview() {
    SignInPromptView(info = "Sign in to continue",
    resId = com.madhav.sportstrackr.R.raw.locked
        )
}
