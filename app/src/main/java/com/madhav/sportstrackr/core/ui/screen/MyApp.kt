package com.madhav.sportstrackr.core.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.madhav.sportstrackr.core.constants.Constants
import com.madhav.sportstrackr.core.ui.views.MyBottomNavigation
import com.madhav.sportstrackr.features.splash.presentation.page.SplashScreen

@Composable
fun MyApp() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Constants.ROUTE.MAIN_SCREEN
    ) {
       composable(Constants.ROUTE.MAIN_SCREEN) {
           var selectedIndex by rememberSaveable { mutableStateOf(1) }

           Scaffold(
               backgroundColor = Color.White,
               content = {
                     MainScreen(selectedIndex, it)
               },
               bottomBar = {
                   MyBottomNavigation(
                       selectedIndex = selectedIndex,
                       onSelectedIndexChanged =
                       {
                           selectedIndex = it
                       },
                   )
               }
           )
       }

        composable(Constants.ROUTE.SPLASH_SCREEN) {
            SplashScreen()
        }
    }
}