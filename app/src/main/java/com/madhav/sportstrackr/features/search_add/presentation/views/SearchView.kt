package com.madhav.sportstrackr.features.search_add.presentation.views

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch


@Composable
fun SearchView(
    hint: String,
    onSearch: suspend (query: String) -> Unit,
    modifier: Modifier = Modifier,
    onQueryChanged: (query: String) -> Unit = { },
) {
    val scope = rememberCoroutineScope()
    var query by rememberSaveable { mutableStateOf("") }

    val showClearText = query.isNotEmpty()

    val submitSearch = {
        scope.launch {
            onSearch(query)
        }
    }

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .padding(horizontal = 12.dp, vertical = 8.dp)
    ) {
        // Search input field
        OutlinedTextField(
            value = query,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.Gray,
                unfocusedBorderColor = Color.DarkGray
            ),
            onValueChange = {
                query = it
                onQueryChanged(it)
            },
            label = { Text(hint) },
            leadingIcon = null,
            trailingIcon = {
                IconButton(
                    onClick = {
                        if (showClearText) {
                            query = ""
                        } else {
                            submitSearch()
                        }
                    },
                    content = {
                        Icon(
                            imageVector = if (showClearText) Icons.Filled.Clear else Icons.Filled.Search,
                            contentDescription = if (showClearText) "Clear" else "Search",
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
            onClick = { submitSearch() },
            colors = ButtonDefaults.textButtonColors(contentColor = Color.Transparent)
        ) {}
    }
}

@Preview(showBackground = true)
@Composable
fun SearchPreview() {
    SearchView(hint = "enter a name", onSearch = {})
}