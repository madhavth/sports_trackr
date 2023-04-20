package com.madhav.sportstrackr.features.splash.presentation.page

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SplashScreen(
    modifier: Modifier = Modifier,
    onBoardingCompleted: () -> Unit
) {
    val pagerState = rememberPagerState()
    val scope = rememberCoroutineScope()
    val currentPage = pagerState.currentPage

    Scaffold(
        bottomBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .background(Color.Black.copy(0.5f)),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (currentPage > 0) {
                    TextButton(
                        onClick = {
                            scope.launch {
                                pagerState.animateScrollToPage(currentPage - 1)
                            }
                        },
                        modifier = Modifier.padding(start = 16.dp)
                    ) {
                        Text("Prev", style= TextStyle(
                            color =  Color.White
                        ))
                    }
                }

                if (currentPage < 2) {
                    Button(
                        onClick = {
                            scope.launch {
                                pagerState.animateScrollToPage(currentPage + 1)
                            }
                        },
                        modifier = Modifier.padding(end = 16.dp)
                    ) {
                        Text("Next")
                    }
                } else {
                    Button(
                        onClick = {
                            onBoardingCompleted()
                                  },
                        modifier = Modifier.padding(end = 16.dp)
                    ) {
                        Text("Complete")
                    }
                }
            }
        }
    ) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.padding(it),
            pageCount = 3
        ) { page ->
            when (page) {
                0 -> OnboardingPageOne()
                1 -> OnboardingPageTwo()
                2 -> OnboardingPageThree()
            }
        }
    }
}