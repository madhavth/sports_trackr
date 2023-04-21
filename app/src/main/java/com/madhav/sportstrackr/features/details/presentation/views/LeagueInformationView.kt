package com.madhav.sportstrackr.features.details.presentation.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun LeagueInformationView(
    leagueName: String,
    country: String,
    manager: String,
    stadium: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = 4.dp,
        backgroundColor = MaterialTheme.colors.surface,
        shape = RoundedCornerShape(8.dp),
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "League Information",
                style = MaterialTheme.typography.h6,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            TextInfo(label = "League", value = leagueName)
            TextInfo(label = "Country", value = country)
            TextInfo(label="Manager", value = manager)
            TextInfo(label = "Stadium", value = stadium)
        }
    }

}

@Composable
fun TextInfo(label: String, value: String) {
    if(value.isEmpty()) {
        return
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = "$label:",
            style = MaterialTheme.typography.body1.copy(
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier.weight(1f)
        )
        Text(
            text = value,
            style = MaterialTheme.typography.body2,
            modifier = Modifier.weight(1f)
        )
    }

}


@Preview
@Composable
fun LeagueInformationPreview() {
    LeagueInformationView(
        leagueName = "Premier League", country = "England",
        manager = "Jose Marinho",
        stadium = "Old Trafford"
    )
}