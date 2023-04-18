package com.madhav.sportstrackr.core.ui.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.madhav.sportstrackr.core.models.Screen
import com.madhav.sportstrackr.core.ui.views.MyBottomNavigation
import com.madhav.sportstrackr.features.details.presentation.page.DetailsScreen
import com.madhav.sportstrackr.features.events.presentation.page.EventScreen
import com.madhav.sportstrackr.features.events.presentation.page.PastFutureEventScreen
import com.madhav.sportstrackr.features.profile.presentation.page.ProfileScreen
import com.madhav.sportstrackr.features.search.presentation.page.SearchScreen
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainScreen(selectedIndex: Int, padding: PaddingValues) {

    val pagerState = rememberPagerState()

    LaunchedEffect(key1 = selectedIndex, block = {
        pagerState.animateScrollToPage(selectedIndex)
    })

    Column(modifier = Modifier.fillMaxSize().padding(padding)) {
        HorizontalPager(
            pageCount = Screen.items.size,
            userScrollEnabled = false,
            state = pagerState,
            modifier = Modifier.weight(0.8f),
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                when (Screen.items[it]) {
                    Screen.Details -> DetailsScreen()
                    Screen.Events -> EventScreen()
                    Screen.Profile -> ProfileScreen()
                    Screen.Search -> SearchScreen()
                }
            }
        }
    }
}
