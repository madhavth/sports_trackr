package com.madhav.sportstrackr.features.splash.presentation.page

import android.window.SplashScreen
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.madhav.sportstrackr.features.splash.domain.entities.SwipeScreen

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SplashScreen(modifier: Modifier= Modifier) {
    HorizontalPager(pageCount = 3) {
        Text("Page $it")
    }
}

@Preview
@Composable
fun SplashScreenPreview() {
    SplashScreen()
}
