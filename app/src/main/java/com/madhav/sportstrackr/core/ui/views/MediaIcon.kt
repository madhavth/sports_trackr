package com.madhav.sportstrackr.core.ui.views

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.madhav.sportstrackr.R

@Composable
fun MediaIcon(website: String, @DrawableRes icon: Int)
{
    val context = LocalContext.current
    if (website.isNotEmpty()) {
        IconButton(
            onClick = { openUrl(website, context) },
            modifier = Modifier.size(32.dp)
        ) {
            Image(
                painter = painterResource(id = icon),
                contentDescription = "Website Icon",
                colorFilter = ColorFilter.tint(MaterialTheme.colors.primary),
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MediaIconPreview()
{
    MediaIcon("Testing", R.drawable.ic_instagram)
}
