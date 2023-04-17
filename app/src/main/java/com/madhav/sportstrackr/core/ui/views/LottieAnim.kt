package com.madhav.sportstrackr.core.ui.views

import androidx.annotation.RawRes
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.*
import com.madhav.sportstrackr.R

@Composable
fun LottieAnim(@RawRes resId: Int, modifier: Modifier = Modifier) {
    val composition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(resId)
    )

    val progress by animateLottieCompositionAsState(
        composition,
        iterations = LottieConstants.IterateForever,
        isPlaying = true,
        speed = 1f,
        restartOnPlay = false
    )

    LottieAnimation(composition = composition, progress =progress,
        modifier= modifier
    )

}