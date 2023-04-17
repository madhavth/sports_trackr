package com.madhav.sportstrackr.features.search.presentation.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun SearchView(
    hint: String,
    onSearch: (query: String) -> Unit,
    modifier: Modifier = Modifier
) {
    var query by remember { mutableStateOf("") }
    val submitSearch = { onSearch(query) }

    Box(
        modifier = modifier
            .background(Color.White, RoundedCornerShape(16.dp))
            .padding(horizontal = 12.dp, vertical = 8.dp)
    ) {
        // Search input field
        OutlinedTextField(
            value = query,
            onValueChange = { query = it },
            label = { Text(hint) },
            leadingIcon = null,
            trailingIcon = {
                IconButton(
                    onClick = submitSearch,
                    content = {
                        Icon(
                            imageVector = Icons.Filled.Search,
                            contentDescription = "Search",
                            tint = Color.Gray
                        )
                    }
                )
            },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(onSearch = { submitSearch() }),
            modifier = Modifier.fillMaxWidth()
        )

        // Invisible button that captures clicks and submits the search query
        TextButton(
            onClick = submitSearch,
            colors = ButtonDefaults.textButtonColors(contentColor = Color.Transparent)
        ) {}
    }
}

@Preview(showSystemUi = true)
@Composable
fun SearchPreview() {
    SearchView(hint = "enter a name", onSearch = {})
}