package com.madhav.sportstrackr.core.models

sealed class MyResponse<out T> {
    data class Success<T>(val data: T, val isInitial: Boolean = false) : MyResponse<T>()
    data class Error(val error: String) : MyResponse<Nothing>()
    object Loading: MyResponse<Nothing>()
}
