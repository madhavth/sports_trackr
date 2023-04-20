package com.madhav.sportstrackr.core.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.madhav.sportstrackr.core.constants.MyConstants
import com.madhav.sportstrackr.core.ui.viewmodels.MainViewModel
import com.madhav.sportstrackr.core.ui.views.MyBottomNavigation
import com.madhav.sportstrackr.features.splash.presentation.page.SplashScreen

@Composable
fun MyApp() {
    val navController = rememberNavController()
    val mainVM = hiltViewModel<MainViewModel>()

    NavHost(
        navController = navController,
        startDestination = if(mainVM.isOnBoardingCompleted()) MyConstants.ROUTE.MAIN_SCREEN
    else MyConstants.ROUTE.SPLASH_SCREEN
    ) {
        composable(MyConstants.ROUTE.MAIN_SCREEN) {
            val mainViewModel = hiltViewModel<MainViewModel>()
            val selectedIndex = mainViewModel.selectedIndex.collectAsState()

            Scaffold(
                backgroundColor = Color.White,
                content = {
                    Column() {
                        MainScreen(selectedIndex.value, it)
                    }
                },
                bottomBar = {
                    MyBottomNavigation(
                        selectedIndex = selectedIndex.value,
                        onSelectedIndexChanged =
                        {
                            mainViewModel.setSelectedIndex(it)
                        },
                    )
                }
            )
        }

        composable(MyConstants.ROUTE.SPLASH_SCREEN) {
            SplashScreen {
                mainVM.saveOnBoardingCompleted(true)
                navController.navigate(MyConstants.ROUTE.MAIN_SCREEN)
            }
        }
    }
}