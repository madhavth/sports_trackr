package com.madhav.sportstrackr.core.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NamedNavArgument
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.madhav.sportstrackr.core.constants.MyConstants
import com.madhav.sportstrackr.core.ui.viewmodels.MainViewModel
import com.madhav.sportstrackr.core.ui.views.MyBottomNavigation
import com.madhav.sportstrackr.features.splash.presentation.page.SplashScreen

@Composable
fun MyApp() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = MyConstants.ROUTE.MAIN_SCREEN
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
            SplashScreen()
        }
    }
}