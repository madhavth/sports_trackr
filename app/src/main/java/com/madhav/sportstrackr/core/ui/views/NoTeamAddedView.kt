package com.madhav.sportstrackr.core.ui.views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.madhav.sportstrackr.core.ui.viewmodels.MainViewModel
import java.util.*

@Composable
fun NoTeamAddedView(
    modifier: Modifier = Modifier,
    onNavigateClicked: () -> Unit = {}
) {
    // compose column to show no team added
    Scaffold(
        topBar = {
            TopAppBar() {
                Text(
                    "No Teams Added",
                    style = TextStyle(
                        fontSize = 18.sp,
                        textAlign = TextAlign.Center,
                        color = Color.White
                    ),
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(it),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(text = "No Team Added click below to add")
            Spacer(modifier = Modifier.height(16.dp))

            LottieAnim(resId = com.madhav.sportstrackr.R.raw.add_data,
                modifier = Modifier.fillMaxHeight(0.5f)
                    .clickable {
                        onNavigateClicked()
                    }.clipToBounds()
                )

        }
    }
}