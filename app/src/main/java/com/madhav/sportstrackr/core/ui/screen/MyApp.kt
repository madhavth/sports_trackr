package com.madhav.sportstrackr.core.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.madhav.sportstrackr.core.constants.Constants
import com.madhav.sportstrackr.features.splash.presentation.page.SplashScreen

@Composable
fun MyApp() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Constants.ROUTE.MAIN_SCREEN
    ) {
       composable(Constants.ROUTE.MAIN_SCREEN) {
           Scaffold(
               backgroundColor = Color.White,
           ) {
               MainScreen(it)
           }

       }

        composable(Constants.ROUTE.SPLASH_SCREEN) {
            SplashScreen()
        }
    }
}