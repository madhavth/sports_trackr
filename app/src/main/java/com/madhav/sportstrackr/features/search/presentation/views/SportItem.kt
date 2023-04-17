package com.madhav.sportstrackr.features.search.presentation.views

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.madhav.sportstrackr.features.search.domain.entities.Sport

@Composable
fun SportItem(
    sport: Sport,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = sport.icon),
            contentDescription = null,
            modifier = Modifier.size(48.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(text = sport.name, fontWeight = FontWeight.Bold)
    }
}

@Preview
@Composable
fun SportsItemPreview() {
    SportItem(sport = Sport("Soccer", icon= com.madhav.sportstrackr.R.drawable.ic_launcher_foreground))
}