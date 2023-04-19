package com.madhav.sportstrackr.core.ui.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.madhav.sportstrackr.R
import com.madhav.sportstrackr.core.data.models.Screen
import com.madhav.sportstrackr.core.ui.viewmodels.AuthViewModel
import com.madhav.sportstrackr.core.ui.views.SignInPromptView
import com.madhav.sportstrackr.features.details.presentation.page.TeamListingScreen
import com.madhav.sportstrackr.features.events.presentation.page.EventScreen
import com.madhav.sportstrackr.features.profile.presentation.page.ProfileScreen
import com.madhav.sportstrackr.features.search_add.presentation.page.SearchScreen
import com.madhav.sportstrackr.features.search_add.presentation.view_models.SearchViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainScreen(selectedIndex: Int, padding: PaddingValues) {
    val pagerState = rememberPagerState()

    LaunchedEffect(key1 = selectedIndex, block = {
//        pagerState.animateScrollToPage(selectedIndex)
        pagerState.scrollToPage(selectedIndex)
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
            val loginViewModel: AuthViewModel = hiltViewModel()
            val user = loginViewModel.currentUser.collectAsState().value

            Box(modifier = Modifier.fillMaxSize()) {
                when (Screen.items[it]) {
                    Screen.Details -> {
                        if(user!= null) {
                            TeamListingScreen()
                        }
                        else {
                            SignInPromptView(info = stringResource(R.string.sign_in_to_view_teams))
                        }
                    }
                    Screen.Events -> {
                        if(user!= null) {
                            EventScreen()
                        }
                        else {
                            SignInPromptView(info = stringResource(R.string.sign_in_to_view_recent_matches))
                        }
                    }
                    Screen.Profile -> {
                        ProfileScreen()
                    }
                    Screen.Search -> {
                        val searchViewModel = hiltViewModel<SearchViewModel>()
                        val teamsState = searchViewModel.teamSearchResult.collectAsState()
                        SearchScreen(teamsState.value)
                    }
                }
            }
        }
    }
}
