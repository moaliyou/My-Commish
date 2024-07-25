@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.mycommish.core.presentation.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.mycommish.R
import com.example.mycommish.core.presentation.ui.theme.MyCommishTheme

@Composable
fun MyCommishAlertDialog(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    dialogTitle: String,
    dialogText: String,
    icon: ImageVector,
    confirmButtonText: String,
    dismissButtonText: String,
) {
    AlertDialog(
        icon = {
            Icon(icon, contentDescription = dialogTitle)
        },
        title = {
            Text(text = dialogTitle)
        },
        text = {
            Text(text = dialogText)
        },
        onDismissRequest = {
            onDismissRequest()
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirmation()
                }
            ) {
                Text(confirmButtonText)
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    onDismissRequest()
                }
            ) {
                Text(dismissButtonText)
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun AlertDialogPreview() {
    var deletePrizeConfirmed by rememberSaveable { mutableStateOf(false) }
    var prizeId by rememberSaveable { mutableLongStateOf(0) }

    MyCommishTheme {
        MyCommishAlertDialog(
            onDismissRequest = { deletePrizeConfirmed = !deletePrizeConfirmed },
            onConfirmation = { prizeId = 2 },
            dialogTitle = "Deleting prize",
            dialogText = "Are you sure to delete this prize?",
            icon = Icons.Filled.Warning,
            confirmButtonText = stringResource(R.string.delete_label_button),
            dismissButtonText = stringResource(R.string.cancel_label_button)
        )
    }
}