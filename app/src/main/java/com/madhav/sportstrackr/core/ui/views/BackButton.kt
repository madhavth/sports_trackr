package com.madhav.sportstrackr.core.ui.views

import androidx.compose.foundation.layout.Row
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import com.madhav.sportstrackr.R

@Composable
fun BackButton(backPressed: ()-> Unit = {}) {
    Row() {
        IconButton(onClick = {
            // navigate back to previous screen
            backPressed()
        }) {
            Icon(
                painter = painterResource(id = R.drawable.ic_back),
                contentDescription = "back button"
            )
        }
    }
}