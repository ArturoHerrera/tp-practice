package com.arthur.totalplaytest.ui.screens.bank_references

import android.widget.Toast
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Logout
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.arthur.totalplaytest.R
import com.arthur.totalplaytest.ui.components.BankReferenceList
import com.arthur.totalplaytest.ui.components.LogoutDialog
import com.arthur.totalplaytest.ui.components.TitledTopAppBar
import kotlinx.coroutines.ExperimentalCoroutinesApi

@OptIn(ExperimentalFoundationApi::class)
@ExperimentalAnimationApi
@ExperimentalMaterialApi
@ExperimentalCoroutinesApi
@Composable
fun BankReferencesScreen(
    navigateToLogin: () -> Unit,
    viewModel: BankReferencesViewModel = hiltViewModel()
) {
    val scaffoldState = rememberScaffoldState()
    val context = LocalContext.current

    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TitledTopAppBar(
                iconImage = Icons.Filled.Logout,
                iconContentDescription = null,
                iconAction = { viewModel.setLogoutDialogVisibility(isVisible = true) },
                titleId = R.string.bank_ref_tittle
            )
        }
    ) {
        Box {
            if (uiState.loading) {
                LinearProgressIndicator(
                    color = MaterialTheme.colors.secondary,
                    modifier = Modifier.fillMaxWidth().height(5.dp)
                )
            }
        }
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            BankReferenceList(
                bankReferenceList = uiState.bankRefList,
                reference = { bankRef -> Toast.makeText(context, "Este es el banco: ${bankRef.aliasbank}", Toast.LENGTH_SHORT).show() }
            )
        }

        if(uiState.showLogoutDialog){
            LogoutDialog(
                onLogoutCanceled = { viewModel.setLogoutDialogVisibility(isVisible = false) } ,
                onLogoutClicked = viewModel::logOut
            )
        }

        if(uiState.logout){
            navigateToLogin()
        }

        //TODO Show something on error
    }

}