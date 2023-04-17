package com.madhav.sportstrackr.core.ui.views

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.madhav.sportstrackr.core.models.MyResponse
import com.madhav.sportstrackr.features.events.presentation.page.PastFutureEventScreen

@Composable
fun <T> NetworkResponseView(state: MyResponse<T>, successView: @Composable (T) -> Unit) {
    when (state) {
        is MyResponse.Error -> ErrorView(message = state.error)
        is MyResponse.Loading -> LoadingView()
        is MyResponse.Success -> SuccessView<T>(state.data, successView)
    }

}

@Composable
fun <T> SuccessView(data: T, successView: @Composable (T) -> Unit) {
    successView(data)
}