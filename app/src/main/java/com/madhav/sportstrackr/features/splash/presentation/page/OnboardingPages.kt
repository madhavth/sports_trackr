package com.madhav.sportstrackr.features.splash.presentation.page

import androidx.annotation.RawRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.airbnb.lottie.compose.LottieConstants
import com.madhav.sportstrackr.R
import com.madhav.sportstrackr.core.ui.views.LottieAnim


@Composable
fun OnBoardingPage(
    text: String, modifier: Modifier = Modifier,
    color: Color = Color.Red,
    @RawRes resId: Int = R.raw.welcome,
    iterations: Int = LottieConstants.IterateForever,
    fontColor: Color= Color.White
) {
    val scrollState = rememberScrollState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LottieAnim(resId = resId, modifier = Modifier.fillMaxHeight(0.4f),
                iterations = iterations)
            Text(
                text,
                fontSize = 20.sp,
                color = fontColor,
                modifier = Modifier
                    .padding(24.dp)
                    .verticalScroll(scrollState),
                lineHeight = 32.sp
            )
        }
    }
}

@Composable
fun OnboardingPageOne() {
    val pageOne = stringResource(id = R.string.onboarding_page_one)
    OnBoardingPage(text = pageOne)
}

@Composable
fun OnboardingPageTwo() {
    val pageTwo = stringResource(id = R.string.onboarding_page_two)
    OnBoardingPage(
        text = pageTwo, color = Color.DarkGray,
        resId = R.raw.sports,
        iterations =  1
    )
}

@Composable
fun OnboardingPageThree() {
    val pageThree = stringResource(id = R.string.onboarding_page_three)
    OnBoardingPage(text = pageThree, color = Color.Blue,
    resId = R.raw.watch_sports,
        )
}
