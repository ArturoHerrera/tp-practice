package com.arthur.totalplaytest.ui.screens.login

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.arthur.totalplaytest.R
import com.arthur.totalplaytest.ui.components.ErrorSnackBar
import kotlinx.coroutines.ExperimentalCoroutinesApi

@OptIn(ExperimentalFoundationApi::class)
@ExperimentalAnimationApi
@ExperimentalMaterialApi
@ExperimentalCoroutinesApi
@Composable
fun LoginScreen(
    navigateToBankReferences: () -> Unit,
    viewModel: LoginViewModel = hiltViewModel()
) {
    val scaffoldState = rememberScaffoldState()

    val uiState by viewModel.uiState.collectAsState()

    //TODO Move to login component
    val focusManager = LocalFocusManager.current
    val (user, setUser) = remember { mutableStateOf("") }
    val (password, setPassword) = remember { mutableStateOf("") }
    var passwordVisibility by remember { mutableStateOf(false) }

    Scaffold(
        scaffoldState = scaffoldState
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
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_total_play),
                contentDescription = stringResource(id = R.string.login_logo_desc),
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 100.dp, end = 100.dp, top = 24.dp)
                    .weight(3f)
            )

            Column(
                verticalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .weight(3f)
                    .wrapContentHeight(Alignment.CenterVertically)
            ) {
                //TODO Move to login component
                OutlinedTextField(
                    modifier = Modifier
                        .padding(start = 16.dp, end = 16.dp, top = 24.dp)
                        .fillMaxWidth(),
                    value = user,
                    onValueChange = setUser,
                    label = { Text(text = stringResource(id = R.string.login_label_user)) },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = MaterialTheme.colors.primary,
                        textColor = MaterialTheme.colors.primary,
                        cursorColor = MaterialTheme.colors.primary,
                        focusedLabelColor = MaterialTheme.colors.primary,
                        unfocusedLabelColor = MaterialTheme.colors.primary,
                        unfocusedBorderColor = MaterialTheme.colors.primary
                    ),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email,
                        autoCorrect = false,
                        imeAction = ImeAction.Next
                    )
                )

                //TODO Move to login component
                OutlinedTextField(
                    modifier = Modifier
                        .padding(start = 16.dp, end = 16.dp, top = 8.dp)
                        .fillMaxWidth(),
                    value = password,
                    onValueChange = setPassword,
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = MaterialTheme.colors.primary,
                        textColor = MaterialTheme.colors.primary,
                        cursorColor = MaterialTheme.colors.primary,
                        focusedLabelColor = MaterialTheme.colors.primary,
                        unfocusedLabelColor = MaterialTheme.colors.primary,
                        unfocusedBorderColor = MaterialTheme.colors.primary
                    ),
                    label = { Text(text = stringResource(id = R.string.login_label_pass)) },
                    visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password,
                        autoCorrect = false,
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(onDone = {
                        if (user.isNotBlank() && password.isNotBlank()) {
                            focusManager.clearFocus(true)
                            viewModel.login(user, password)
                        } else {
                            focusManager.clearFocus(true)
                        }
                    }),
                    trailingIcon = {
                        IconButton(
                            onClick = { passwordVisibility = !passwordVisibility }
                        ) {
                            Icon(
                                imageVector = if (passwordVisibility) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                                contentDescription = null
                            )
                        }
                    }
                )
            }

            Column(
                modifier = Modifier.weight(7f)
            ) {
                Button(
                    modifier = Modifier.padding(top = 24.dp),
                    shape = RoundedCornerShape(50),
                    enabled = user.isNotBlank() && password.isNotBlank(),
                    onClick = {
                        focusManager.clearFocus(true)
                        viewModel.login(user, password)
                    }
                ) {
                    Text(
                        text = stringResource(id = R.string.login_label_login),
                        modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp)
                    )
                }
            }

        }

        if (uiState.showErrorSnack) {
            uiState.errorMsg?.let { safeErrorMsg ->
                ErrorSnackBar(
                    scaffoldState = scaffoldState,
                    errorMessage = safeErrorMsg,
                    onDismissed = { viewModel.clearError() }
                )
            }
        }

        if (uiState.succeeded) {
            navigateToBankReferences()
        }
    }
}