package com.arthur.totalplaytest.ui.components

import androidx.compose.material.ScaffoldState
import androidx.compose.material.SnackbarDuration
import androidx.compose.material.SnackbarResult
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect

@Composable
fun ErrorSnackBar(
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    errorMessage: String,
    actionLabel: String? = null,
    onActionClicked: () -> Unit = {},
    onDismissed: () -> Unit = {},
    duration: SnackbarDuration = SnackbarDuration.Short
) {
    LaunchedEffect(scaffoldState.snackbarHostState) {
        val snackbarResult = scaffoldState.snackbarHostState.showSnackbar(
            message = errorMessage,
            actionLabel = actionLabel,
            duration = duration
        )
        when(snackbarResult) {
            SnackbarResult.ActionPerformed -> onActionClicked()
            SnackbarResult.Dismissed -> onDismissed()
        }
    }
}