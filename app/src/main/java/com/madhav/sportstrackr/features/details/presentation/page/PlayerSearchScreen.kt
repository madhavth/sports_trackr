package com.madhav.sportstrackr.features.details.presentation.page

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.madhav.sportstrackr.core.data.models.MyResponse
import com.madhav.sportstrackr.core.domain.entity.PlayerInfo
import com.madhav.sportstrackr.core.ui.views.BackButton
import com.madhav.sportstrackr.core.ui.views.LazyNetworkResponseView
import com.madhav.sportstrackr.features.details.presentation.views.PlayerInfoCardView
import com.madhav.sportstrackr.features.search_add.presentation.views.SearchView

@Composable
fun PlayerSearchScreen(
    playersList: MyResponse<List<PlayerInfo>>,
    backPressed: () -> Unit = {},
    teamId: String?,
    teamName: String?,
    performSearch: (String) -> Unit = {}
) {
    Column(modifier = Modifier.fillMaxSize()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            BackButton(backPressed = backPressed)

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "$teamName Players",
                    style = MaterialTheme.typography.h6.copy(
                        fontWeight = FontWeight.Bold
                    )
                )
            }
        }

        SearchView(hint = "search here", onSearch = {
            performSearch(it)
        })

        LazyNetworkResponseView(
            state = playersList,
            successView =  {
                           PlayerInfoCardView(playerInfo = it)
            },
            emptyCheckCondition = {
                                  it.isEmpty()
            },
            onErrorRetry = {
                performSearch("")
            }
        )

    }

}
