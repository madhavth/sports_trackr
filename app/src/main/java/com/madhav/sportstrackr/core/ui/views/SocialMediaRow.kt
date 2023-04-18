package com.madhav.sportstrackr.core.ui.views

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.madhav.sportstrackr.R
import com.madhav.sportstrackr.core.models.SocialMediaInfo

@Composable
fun SocialMediaRow(socialMediaInfo: SocialMediaInfo) {
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        MediaIcon(website = socialMediaInfo.website, icon = R.drawable.ic_website)
        MediaIcon(website = socialMediaInfo.facebook, icon = R.drawable.ic_facebook)
        MediaIcon(website = socialMediaInfo.twitter, icon = R.drawable.ic_twitter)
        MediaIcon(website = socialMediaInfo.instagram, icon = R.drawable.ic_instagram)
    }
}

fun openUrl(url: String, context: Context) {

    var webpage = Uri.parse(url)

    if (!url.startsWith("http://") && !url.startsWith("https://")) {
        webpage = Uri.parse("http://$url")
    }

    val intent = Intent(Intent.ACTION_VIEW, webpage)
    ContextCompat.startActivity(context, intent, null)
}

@Preview
@Composable
fun SocialMediaRowPreview() {
    SocialMediaRow(
        SocialMediaInfo(
            website = "https://www.google.com",
            facebook = "https://www.facebook.com",
            twitter = "https://www.twitter.com",
            instagram = "https://www.instagram.com"
        )
    )
}