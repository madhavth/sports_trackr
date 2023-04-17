package com.madhav.sportstrackr.features.search.presentation.page

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.madhav.sportstrackr.features.search.presentation.views.SearchView

@Composable
fun SearchScreen(modifier: Modifier = Modifier) {
    Column(modifier = modifier.padding(8.dp)) {
        SearchView(hint = "search here", onSearch = {
        }
        )
        Spacer(modifier = Modifier.height(8.dp))
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun SearchScreenPreview() {
    SearchScreen()
}