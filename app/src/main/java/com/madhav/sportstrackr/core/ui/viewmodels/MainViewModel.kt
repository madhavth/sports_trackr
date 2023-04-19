package com.madhav.sportstrackr.core.ui.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val _selectedIndex = MutableStateFlow(1)
    val selectedIndex: StateFlow<Int> = _selectedIndex

    private val _addAlertDialog = MutableStateFlow(false)
    val showAddAlertDialog: StateFlow<Boolean> = _addAlertDialog

    fun setSelectedIndex(index: Int) {
        _selectedIndex.value = index
    }

    fun navigateToAddTeam() {
        _selectedIndex.value = 2
        _addAlertDialog.value = true
    }

    fun showAddAlertDialog() {
        _addAlertDialog.value = true
    }

    fun hideAddAlertDialog() {
        _addAlertDialog.value = false
    }

    fun toggleAlertDialog() {
        _addAlertDialog.value = !_addAlertDialog.value
    }


}