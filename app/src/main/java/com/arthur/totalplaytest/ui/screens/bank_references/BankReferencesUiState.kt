package com.arthur.totalplaytest.ui.screens.bank_references

import com.arthur.totalplaytest.data.remote.dto.BankReference

data class BankReferencesUiState(
    val loading: Boolean = false,
    val errorMsg: String? = null,
    val showErrorSnack: Boolean = false,
    val bankRefList: List<BankReference> = listOf()
)