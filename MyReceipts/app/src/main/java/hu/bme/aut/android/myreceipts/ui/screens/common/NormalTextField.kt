package hu.bme.aut.android.myreceipts.ui.screens.common

import androidx.compose.foundation.text.KeyboardActionScope
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ErrorOutline
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun NormalTextField(
    modifier: Modifier = Modifier,
    value: String,
    label: String,
    onValueChange: (String) -> Unit,
    onValueCantChange: () -> Unit = {},
    enabled: Boolean = true,
    isError: Boolean = false,
    onDone: (KeyboardActionScope.() -> Unit)?,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions(
        keyboardType = KeyboardType.Text,
        imeAction = ImeAction.Done
    ),
    singleLine: Boolean = true,
) {
    OutlinedTextField(
        value = value,
        onValueChange = { newText ->
            if(isError) {
                onValueCantChange()
            }
            else {
                onValueChange(newText)
            }
        },
        label = { Text(text = label) },
        leadingIcon = leadingIcon,
        trailingIcon = {
            if (isError) {
                Icon(
                    imageVector = Icons.Default.ErrorOutline,
                    contentDescription = null
                )
            } else {
                if (trailingIcon != null) {
                    trailingIcon()
                }
            }
        },
        modifier = modifier,
        singleLine = singleLine,
        isError = isError,
        enabled = enabled,
        keyboardOptions = keyboardOptions,
        keyboardActions = KeyboardActions(
            onDone = onDone
        )
    )
}

@Preview(showBackground = true)
@Composable
fun NormalTextFieldPreview() {
    NormalTextField(value = "Value", label = "Label", onValueChange = {}, onDone = {})
}