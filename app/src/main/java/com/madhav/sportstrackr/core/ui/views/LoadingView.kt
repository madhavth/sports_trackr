package com.madhav.sportstrackr.core.ui.views

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.*
import com.madhav.sportstrackr.R

@Composable
fun LoadingView(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LottieAnim(resId = R.raw.loading, modifier = Modifier.size(140.dp))
        LottieAnim(resId = R.raw.loading_text, modifier = Modifier.size(140.dp))
    }
}

@Preview
@Composable
fun LoadingPreview() {
    LoadingView()
}