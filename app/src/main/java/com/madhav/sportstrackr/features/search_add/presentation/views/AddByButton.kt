package com.madhav.sportstrackr.features.search_add.presentation.views

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.madhav.sportstrackr.R


@Composable
fun AddByButton(modifier: Modifier = Modifier,
                @DrawableRes icon: Int,
                onClick: () -> Unit, text: String) {
    Button(onClick = onClick) {
        Row(
            modifier= modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Icon(
                painter = painterResource(id = icon),
                contentDescription = "icon",
                modifier = Modifier.size(24.dp)
            )
        Text(text)
        }
    }
}

@Preview
@Composable
fun AddByButtonPreview() {
    AddByButton(onClick = {}, text = "by team name",
    icon = R.drawable.ic_country
        )
}