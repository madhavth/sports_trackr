package com.madhav.sportstrackr.core.ui.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.madhav.sportstrackr.core.data.models.Screen
import com.madhav.sportstrackr.core.ui.viewmodels.LoginViewModel
import com.madhav.sportstrackr.core.ui.views.SignInPromptView
import com.madhav.sportstrackr.features.details.presentation.page.DetailsScreen
import com.madhav.sportstrackr.features.events.presentation.page.EventScreen
import com.madhav.sportstrackr.features.profile.presentation.page.ProfileScreen
import com.madhav.sportstrackr.features.search_add.presentation.page.SearchScreen

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainScreen(selectedIndex: Int, padding: PaddingValues) {

    val pagerState = rememberPagerState()

    LaunchedEffect(key1 = selectedIndex, block = {
        pagerState.animateScrollToPage(selectedIndex)
    })

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(padding)) {
        HorizontalPager(
            pageCount = Screen.items.size,
            userScrollEnabled = false,
            state = pagerState,
            modifier = Modifier.weight(0.8f),
        ) {
            val loginViewModel: LoginViewModel = hiltViewModel()
            val user = loginViewModel.currentUser.collectAsState().value

            Box(modifier = Modifier.fillMaxSize()) {
                when (Screen.items[it]) {
                    Screen.Details -> {
                        if(user!= null) {
                            DetailsScreen()
                        }
                        else {
                            SignInPromptView(info = "Sign in to view your favorite teams")
                        }
                    }
                    Screen.Events -> {
                        if(user!= null) {
                            EventScreen()
                        }
                        else {
                            SignInPromptView(info = "Sign in to add/view your favorite teams next/recent matches")
                        }
                    }
                    Screen.Profile -> {
                        ProfileScreen()
                    }
                    Screen.Search -> {
                        SearchScreen()
                    }
                }
            }
        }
    }
}
