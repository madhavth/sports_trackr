package com.madhav.sportstrackr.features.search_add.presentation.views

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.madhav.sportstrackr.R
import com.madhav.sportstrackr.core.ui.views.LottieAnim

@Composable
fun StartSearchingView(modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier.fillMaxWidth().height(300.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            LottieAnim(resId = R.raw.start_searching,
            modifier= Modifier.fillMaxSize(0.65f)
                )
            Spacer(modifier = Modifier.height(14.dp))
            Text("Start your from search above")
        }

    }

}