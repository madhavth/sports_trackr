package com.madhav.sportstrackr.features.search.presentation.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.madhav.sportstrackr.R
import com.madhav.sportstrackr.features.search.domain.entities.Country

@Composable
fun CountryCard(
    country: Country,
    onCountrySelected: (country: Country) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = 8.dp
    ) {
        Row(
            modifier = Modifier
                .clickable { onCountrySelected(country) }
                .padding(16.dp)
        ) {
            Image(
                painter = painterResource(id = country.flag),
                contentDescription = null,
                modifier = Modifier.size(56.dp)
            )
            Column(
                modifier = Modifier.padding(start = 16.dp)
            ) {
                Text(text = country.name, fontWeight = FontWeight.Bold)
                Text(text = country.region)
            }
        }
    }
}

@Preview
@Composable
fun CountryCardPreview() {
    CountryCard(
        country = Country(name = "United States"
            , region = "Americas"
            , flag = R.drawable.ic_launcher_foreground
        ),
        onCountrySelected = {   /* handle country selection */ }
    )
}