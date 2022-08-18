package com.arthur.totalplaytest.ui.screens.bank_references

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arthur.totalplaytest.data.repository.BankReferencesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject


@ExperimentalCoroutinesApi
@HiltViewModel
class BankReferencesViewModel @Inject constructor(
    private val bankReferencesRepository: BankReferencesRepository
) : ViewModel() {

    private val vmUiState = MutableStateFlow(BankReferencesUiState())

    val uiState = vmUiState.stateIn(
        viewModelScope,
        SharingStarted.Eagerly,
        vmUiState.value
    )

    init {
        viewModelScope.launch {
            vmUiState.update { it.copy( loading = true ) }
            bankReferencesRepository.getBankReferences().collect { result ->
                val errorMsg = result.first
                errorMsg?.let{ safeErrorMsg ->
                    vmUiState.update { it.copy( loading = false,  errorMsg = safeErrorMsg, showErrorSnack = true, bankRefList = listOf()) }
                } ?: run {
                    val bankRefList = result.second
                    vmUiState.update { it.copy( loading = false,  errorMsg = null, showErrorSnack = false, bankRefList = bankRefList) }
                }

            }
        }
    }

}