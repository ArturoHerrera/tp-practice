package com.arthur.totalplaytest.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.arthur.totalplaytest.R

@Composable
fun LogoutDialog(
    onLogoutClicked: () -> Unit,
    onLogoutCanceled: () -> Unit
) {
    Dialog(onDismissRequest = onLogoutCanceled) {
        Card {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = stringResource(id = R.string.logout_title),
                    style = MaterialTheme.typography.h6,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.padding(10.dp))
                Text(
                    text = stringResource(id = R.string.logout_message),
                    style = MaterialTheme.typography.body1
                )
                Spacer(modifier = Modifier.padding(10.dp))
                Row {
                    OutlinedButton(
                        onClick = onLogoutCanceled,
                        border = BorderStroke(
                            width = 2.dp,
                            color = MaterialTheme.colors.primary
                        )
                    ) {
                        Text(text = stringResource(id = R.string.action_cancel))
                    }
                    Spacer(modifier = Modifier.padding(10.dp))
                    Button(onClick = onLogoutClicked) {
                        Text(text = stringResource(id = R.string.action_continue))
                    }
                }
            }
        }
    }
}