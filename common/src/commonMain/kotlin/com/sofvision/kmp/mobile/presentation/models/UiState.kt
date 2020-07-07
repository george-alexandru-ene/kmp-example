package com.sofvision.kmp.mobile.presentation.models

sealed class UiState {
    object Success : UiState()
    object Complete : UiState()
    object Loading : UiState()
    class Error(throwable: Throwable) : UiState()
}