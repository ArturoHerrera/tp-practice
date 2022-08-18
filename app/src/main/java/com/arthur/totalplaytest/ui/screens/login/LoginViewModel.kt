package com.arthur.totalplaytest.ui.screens.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arthur.totalplaytest.data.model.AuthRequest
import com.arthur.totalplaytest.data.repository.LoginRepository
import com.arthur.totalplaytest.utils.HttpError
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject


@ExperimentalCoroutinesApi
@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginRepository: LoginRepository
) : ViewModel() {

    private val vmUiState = MutableStateFlow(LoginUiState())

    val uiState = vmUiState.stateIn(
        viewModelScope,
        SharingStarted.Eagerly,
        vmUiState.value
    )

    fun login(user: String, pass: String) {
        vmUiState.update { it.copy( loading = true ) }
        viewModelScope.launch {
            val body = AuthRequest(
                user = user,
                password = pass
            )
            loginRepository.login(body).collect { authResult ->
                val responseCode = authResult.second
                if(responseCode == HttpError.Success.code){
                    vmUiState.update { it.copy( errorMsg = null, succeeded = true, loading = false ) }
                } else {
                    val sessionMsg = authResult.first
                    vmUiState.update { it.copy( errorMsg = sessionMsg, succeeded = false, loading = false, showErrorSnack = true ) }
                }
            }
        }
    }

    fun clearError(){
        vmUiState.update { it.copy( errorMsg = null, succeeded = false, loading = false, showErrorSnack = false ) }
    }
}