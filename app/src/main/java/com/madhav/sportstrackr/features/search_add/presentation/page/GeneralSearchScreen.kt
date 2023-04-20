package com.madhav.sportstrackr.features.search_add.presentation.page

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import com.madhav.sportstrackr.R
import com.madhav.sportstrackr.core.data.models.MyResponse
import com.madhav.sportstrackr.core.ui.views.LazyNetworkResponseView
import com.madhav.sportstrackr.core.ui.views.MyTopAppBar
import com.madhav.sportstrackr.features.search_add.presentation.views.SearchView

@Composable
fun <T> GeneralSearchScreen(
    listState: MyResponse<List<T>>, modifier: Modifier = Modifier,
    successView: @Composable (T) -> Unit,
    onBackPressed: () -> Unit = {},
    checkEmptyCondition: (List<T>) -> Boolean,
    fetchData: suspend () -> Unit,
    appBarTitle: String = "Search",
    performSearch: (String) -> Unit
) {

    LaunchedEffect(key1 = true, block = {
        fetchData()
    })

    Scaffold(
        topBar = {
            MyTopAppBar(text = appBarTitle,
                backPressed = onBackPressed
            )
        },
        modifier = Modifier
    ) { padding ->
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(padding)) {
            SearchView(hint = "search here", onQueryChanged = {
                performSearch(it)
            }, onSearch = {
                performSearch(it)
            })

            LazyNetworkResponseView(
                state = listState,
                modifier = Modifier.fillMaxSize(),
                successView = successView,
                emptyDataInfo = "No such sport found",
                emptyCheckCondition = checkEmptyCondition,
                onErrorRetry = {
                    fetchData()
                },
                loadingAnim = R.raw.searching
            )
        }
    }
}
