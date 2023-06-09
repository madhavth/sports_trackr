package com.madhav.sportstrackr.core.ui.views

import androidx.annotation.RawRes
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.madhav.sportstrackr.core.data.models.MyResponse
import com.madhav.sportstrackr.features.search_add.presentation.views.StartSearchingView

@Composable
fun <T> LazyNetworkResponseView(
    state: MyResponse<List<T>>,
    modifier: Modifier = Modifier,
    successView: @Composable (data: T) -> Unit,
    emptyCheckCondition: (data: List<T>) -> Boolean,
    emptyDataInfo: String = "No data found",
    onErrorRetry: suspend () -> Unit,
    @RawRes loadingAnim: Int = com.madhav.sportstrackr.R.raw.loading,
) {
    LazyColumn(
        contentPadding = PaddingValues(8.dp),
        modifier = modifier
    ) {
        item {
            Spacer(modifier = Modifier.height(8.dp))
        }

        when (state) {
            is MyResponse.Loading -> {
                item {
                    LoadingView(
                        modifier = Modifier
                            .padding(
                                top = 120.dp
                            ),
                        loadingAnim = loadingAnim
                    )
                }
            }
            is MyResponse.Success -> {
                if(state.data.isEmpty() && state.isInitial) {
                    item {
                        StartSearchingView()
                    }
                }
                else if (emptyCheckCondition(state.data)) {
                    item {
                        NotFoundView(info = emptyDataInfo)
                    }
                } else {
                    items(state.data.size) {
                        successView(state.data[it])
                    }
                }
            }
            is MyResponse.Error -> {
                item {
                    ErrorView(message = state.error, modifier = Modifier
                        .padding(top = 120.dp),
                        onRetry = {
                            onErrorRetry()
                        }
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun LazyNetworkResponseViewPreview() {
    LazyNetworkResponseView<String>(state = MyResponse.Success(listOf("a", "b", "c")),
        successView = {
            Text(text = it)
        },
        emptyCheckCondition = { it -> it.isEmpty() },
        onErrorRetry = {}
    )
}