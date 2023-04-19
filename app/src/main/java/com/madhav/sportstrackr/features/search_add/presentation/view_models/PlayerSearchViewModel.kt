package com.madhav.sportstrackr.features.search_add.presentation.view_models

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PlayerSearchViewModel @Inject constructor(
    val savedStateHandle: SavedStateHandle,
) : ViewModel() {

}