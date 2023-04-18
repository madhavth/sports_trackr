package com.madhav.sportstrackr.core.ui.views

import androidx.compose.runtime.Composable
import com.madhav.sportstrackr.core.data.models.MyResponse

@Composable
fun <T> NetworkResponseView(
    state: MyResponse<T>, successView: @Composable (T) -> Unit,
    onRetry: suspend () -> Unit = {}
) {
    when (state) {
        is MyResponse.Error -> ErrorView(message = state.error, onRetry = {
            onRetry()
        })
        is MyResponse.Loading -> LoadingView()
        is MyResponse.Success -> SuccessView<T>(state.data, successView)
    }

}

@Composable
fun <T> SuccessView(data: T, successView: @Composable (T) -> Unit) {
    successView(data)
}