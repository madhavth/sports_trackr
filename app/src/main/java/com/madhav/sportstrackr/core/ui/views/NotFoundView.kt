package com.madhav.sportstrackr.core.ui.views

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.madhav.sportstrackr.R

@Composable
fun NotFoundView(info: String, modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LottieAnim(
            resId = R.raw.result_not_found, modifier = Modifier
                .weight(1f)
                .height(200.dp),
            iterations = 1
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = info)
    }
}