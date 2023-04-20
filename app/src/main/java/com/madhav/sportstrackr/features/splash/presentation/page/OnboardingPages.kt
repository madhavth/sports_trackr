package com.madhav.sportstrackr.features.splash.presentation.page

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp

@Composable
fun OnboardingPageOne() {
    Box(modifier = Modifier.fillMaxSize().background(Color.Red)) {
        Text("Onboarding Page One", fontSize = 20.sp, color = Color.White, modifier = Modifier.align(Alignment.Center))
    }
}

@Composable
fun OnboardingPageTwo() {
    Box(modifier = Modifier.fillMaxSize().background(Color.Green)) {
        Text("Onboarding Page Two", fontSize = 20.sp, color = Color.White, modifier = Modifier.align(Alignment.Center))
    }
}

@Composable
fun OnboardingPageThree() {
    Box(modifier = Modifier.fillMaxSize().background(Color.Blue)) {
        Text("Onboarding Page Three", fontSize = 20.sp, color = Color.White, modifier = Modifier.align(
            Alignment.Center))
    }
}
