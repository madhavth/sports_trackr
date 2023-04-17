package com.madhav.sportstrackr.core.ui.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
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
fun MainScreen(padding: PaddingValues) {

    val pagerState = rememberPagerState()
    var selectedIndex by remember { mutableStateOf(0) }

    LaunchedEffect(key1 = selectedIndex, block = {
        pagerState.animateScrollToPage(selectedIndex)
    })

    Column(modifier = Modifier.fillMaxSize()) {
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

        MyBottomNavigation(
            selectedIndex = selectedIndex,
            onSelectedIndexChanged =
            {
                selectedIndex = it
            },
        )
    }
}
