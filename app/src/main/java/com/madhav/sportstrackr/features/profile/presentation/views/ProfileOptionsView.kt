package com.madhav.sportstrackr.features.profile.presentation.views

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.madhav.sportstrackr.core.ui.viewmodels.AuthViewModel
import com.madhav.sportstrackr.features.profile.domain.entities.ProfileSelectedOption

@Composable
fun ProfileOptionsView(modifier: Modifier = Modifier, logoutPressed: () -> Unit) {
    val scrollState = rememberScrollState()
    var selectedOption by remember {
        mutableStateOf<ProfileSelectedOption>(ProfileSelectedOption.Initial)
    }
    val showShowDialog = selectedOption !is ProfileSelectedOption.Initial

    if (showShowDialog) {
        AlertDialog(
            onDismissRequest = {
                selectedOption = ProfileSelectedOption.Initial
            },
            text = {
                when (selectedOption) {
                    is ProfileSelectedOption.AboutDeveloper -> {
                        AboutDeveloperView()
                    }
                    else -> {}
                }
            },
            buttons = {}
        )
    }

    Card(
        modifier = Modifier
            .padding(24.dp)
            .fillMaxWidth()
            .border(1.dp, MaterialTheme.colors.onSurface, RoundedCornerShape(8.dp)),
        shape = RoundedCornerShape(16.dp),
        elevation = 8.dp,
    ) {
        Column(
            modifier = Modifier
                .verticalScroll(scrollState)
                .padding(12.dp)
        ) {
            ProfileOption("About Developer", onClick = {
                selectedOption = ProfileSelectedOption.AboutDeveloper
            })
            ProfileOption("Logout", onClick = {
                logoutPressed()
            })
        }
    }
}

@Preview
@Composable
fun ProfileOptionsPreview() {
    ProfileOptionsView(
        logoutPressed = {},

        )
}