package com.naufalabdullah0109.assessment3.ui.screen

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.naufalabdullah0109.assessment3.R

@Composable
fun HapusDialog(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit
) {
    AlertDialog(
        onDismissRequest = { onDismissRequest() },
        title = { Text(text = stringResource(R.string.konfirmasi_hapus)) },
        text = { Text(text = stringResource(R.string.konfirmasi_hapus_pesan)) },
        confirmButton = {
            OutlinedButton(onClick = { onConfirmation() }) {
                Text(text = stringResource(R.string.hapus))
            }
        },
        dismissButton = {
            OutlinedButton(onClick = { onDismissRequest() }) {
                Text(text = stringResource(R.string.batal))
            }
        }
    )
}