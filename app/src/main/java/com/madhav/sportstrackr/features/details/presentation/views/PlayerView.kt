package com.madhav.sportstrackr.features.details.presentation.views

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.madhav.sportstrackr.core.data.models.SocialMediaInfo
import com.madhav.sportstrackr.core.domain.entity.PlayerInfo

@Composable
fun PlayerInfoCardView(playerInfo: PlayerInfo) {
    var isExpanded by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                isExpanded = !isExpanded
            }
            .padding(8.dp)
            .border(1.dp, MaterialTheme.colors.onSurface, RoundedCornerShape(8.dp))
            .animateContentSize(),
        shape = RoundedCornerShape(8.dp),
        elevation = 8.dp,
    ) {
        Column(modifier = Modifier.padding(16.dp)) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = playerInfo.playerName, style = MaterialTheme.typography.h5,
                    modifier = Modifier.weight(0.8f)
                )
                if (!isExpanded)
                    Icon(
                        imageVector = Icons.Filled.Info, contentDescription = "info",
                        modifier = Modifier.weight(0.2f)
                            .offset(x= 24.dp),

                    )
            }

            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Position: ${playerInfo.playerPosition}")
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Nationality: ${playerInfo.nationality}")

            if (isExpanded) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Date of Birth: ${playerInfo.dateOfBirth}")
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Player Number: ${playerInfo.playerNumber}")
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Player Signing: ${playerInfo.playerSigning}")
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Description: ${playerInfo.description}",
                    style = TextStyle(
                        lineHeight = 20.sp
                    )
                )
                Spacer(modifier = Modifier.height(12.dp))
                Text(text = "Agent: ${playerInfo.agent}")
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PlayerViewPreview() {
    PlayerInfoCardView(
        playerInfo = PlayerInfo(
            playerName = "Cristiano Ronaldo",
            socialMediaInfo = SocialMediaInfo("", "", "", ""),
            playerPosition = "Forward",
            agent = "Mino Raiola",
            playerId = "123",
            dateOfBirth = "5 February 1985",
            description = "Cristiano Ronaldo ",
            playerNumber = "7",
            playerSigning = "$123 ml",
            nationality = "Portugal"
        )
    )
}