package com.arthur.totalplaytest.ui.screens.login

data class LoginUiState(
    val loading: Boolean = false,
    val errorMsg: String? = null,
    val succeeded: Boolean = false,
    val showErrorSnack: Boolean = false
)