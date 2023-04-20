package com.madhav.sportstrackr.features.profile.presentation.page

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.madhav.sportstrackr.core.domain.entity.MyUserInfo

@Composable
fun MyProfileView(userInfo: MyUserInfo?, modifier: Modifier = Modifier,
            logoutPressed: () -> Unit = {}
                  ) {
    if (userInfo == null) {
        return
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Profile") }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                model = userInfo.image,
                contentDescription = "User Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(120.dp)
                    .padding(8.dp)
                    .clip(shape = MaterialTheme.shapes.medium)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = userInfo.fullName, style = MaterialTheme.typography.h5)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = userInfo.email, style = MaterialTheme.typography.subtitle1)
            Spacer(modifier = Modifier.height(8.dp))
            Divider(
                color = Color.LightGray,
                thickness = 1.dp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp)
            )
            Text(
                text = "First Name: ${userInfo.firstName}",
                style = MaterialTheme.typography.body1
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Last Name: ${userInfo.lastName}",
                style = MaterialTheme.typography.body1
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = {
                logoutPressed()
            }) {
                Text(text = "Logout")
            }
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun MyProfilePreview() {
    MyProfileView(
        userInfo = MyUserInfo(
            id = "123",
            fullName = "Madhav Thapa",
            firstName = "Madhav",
            lastName = "Thapa",
            image = "https://lh3.",
            email = "powermt2012@gmail.com"
        )
    )
}