package com.madhav.sportstrackr.core.ui.screen

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.madhav.sportstrackr.core.constants.Constants

@Composable
fun MyApp() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Constants.ROUTE.MAIN_SCREEN
    ) {
       composable(Constants.ROUTE.MAIN_SCREEN) {
           MainScreen()
       }

        composable(Constants.ROUTE.SPLASH_SCREEN) {
            Text("this is the splash screen")
        }
    }
}