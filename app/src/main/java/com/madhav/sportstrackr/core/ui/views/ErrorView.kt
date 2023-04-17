package com.madhav.sportstrackr.core.ui.views

import androidx.compose.runtime.Composable

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.madhav.sportstrackr.R

@Composable
fun ErrorView(
    message: String,
    onRetry: () -> Unit = {},
    errorMessageStyle: TextStyle = MaterialTheme.typography.body2,
) {
        Box(
            Modifier
                .padding(horizontal = 16.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                LottieAnim(resId = R.raw.something_went_wrong, iterations = 1,
                modifier = Modifier.fillMaxWidth()
                    .height(300.dp)
                    )
                Button(
                    onClick = onRetry,
                ) {
                    Text("Retry")
                }
            }
        }
}

@Preview
@Composable
fun ErrorViewPreview() {
    ErrorView(
        message = "Something went wrong!"
    )
}