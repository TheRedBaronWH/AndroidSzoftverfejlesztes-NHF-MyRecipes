package hu.bme.aut.android.myreceipts.ui.screens.random_screen.parts

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import hu.bme.aut.android.myreceipts.R
import hu.bme.aut.android.myreceipts.ui.screens.details_screen.parts.RecipeDetailDeleteDialog

@Composable
fun RecipeRandomSaveDialog(
    onDismiss: () -> Unit,
    onConfirm: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            Button(
                onClick = onConfirm
            ) {
                Text(stringResource(R.string.yes))
            }
        },
        dismissButton = {
            Button(
                onClick = onDismiss
            ) {
                Text(stringResource(R.string.no))
            }
        },
        text = {
            Text(stringResource(R.string.save_dialog_text))
        }
    )
}

@Preview(showBackground = true)
@Composable
fun RecipeDetailDeleteDialogPreview() {
    RecipeDetailDeleteDialog({}, {})
}