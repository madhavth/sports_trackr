package com.madhav.sportstrackr.core.ui.views

import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.*
import kotlinx.coroutines.launch

@Composable
fun MyButton(text: String, onClick: suspend () -> Unit) {
    val scope = rememberCoroutineScope()
    var loading by remember { mutableStateOf(false) }

    if(!loading) {
        Button(
            onClick = {
                scope.launch {
                    loading = true
                    onClick()
                    loading = false
                }
            },
        ) {
            Text(text = text)
        }
    } else {
        CircularProgressIndicator()
    }
}