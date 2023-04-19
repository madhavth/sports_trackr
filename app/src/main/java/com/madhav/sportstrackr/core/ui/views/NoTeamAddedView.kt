package com.madhav.sportstrackr.core.ui.views

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.madhav.sportstrackr.core.ui.viewmodels.MainViewModel

@Composable
fun NoTeamAddedView(
    modifier: Modifier = Modifier,
    onNavigateClicked: () -> Unit = {}
) {
    // compose column to show no team added
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(text = "No Team Added")
        Spacer(modifier = Modifier.height(16.dp))
        MyButton(text = "Add Team Here", onClick = {
            onNavigateClicked()
        })

    }

}