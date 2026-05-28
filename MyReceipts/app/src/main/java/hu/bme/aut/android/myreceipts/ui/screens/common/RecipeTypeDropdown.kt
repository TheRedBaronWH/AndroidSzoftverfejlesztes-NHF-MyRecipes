package hu.bme.aut.android.myreceipts.ui.screens.common

import android.R.attr.contentDescription
import android.R.attr.label
import android.R.attr.onClick
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import hu.bme.aut.android.myreceipts.R
import hu.bme.aut.android.myreceipts.model.RecipeTypes

@Composable
fun RecipeTypeDropdown(
    modifier: Modifier = Modifier,
    actualRecipeType: String = "Other",
    onRecipeTypeSelected: (RecipeTypes) -> Unit = {},
    startExpanded: Boolean = false
) {
    var expanded by remember { mutableStateOf(startExpanded) }

    val recipeTypes: List<RecipeTypes> = RecipeTypes.entries
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        OutlinedTextField(
            modifier = modifier
                .weight(1f),
            enabled = true,
            readOnly = true,
            value = actualRecipeType,
            label = { Text(stringResource(R.string.recipe_type)) },
            onValueChange = {}
        )
        IconButton(
            onClick = {
                expanded = !expanded
            }
        ) {
            Icon(
                imageVector =
                    if (expanded) Icons.Default.ArrowDropUp
                    else Icons.Default.ArrowDropDown,
                contentDescription = null
            )
        }
        DropdownMenu(
            modifier = modifier
                .height(275.dp),
            expanded = expanded,
            onDismissRequest = {
                expanded = false
            }
        ) {
            recipeTypes.forEach { recipeType ->
                DropdownMenuItem(
                    modifier = modifier
                        .width(TextFieldDefaults.MinWidth),
                    text = {
                        Text(text = recipeType.name, fontSize = 20.sp)
                    },
                    onClick = {
                        onRecipeTypeSelected(recipeType)
                        expanded = false
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RecipeTypeDropdownPreview() {
    RecipeTypeDropdown()
}

@Preview(showBackground = true)
@Composable
fun RecipeTypeDropdownExpandedPreview() {
    RecipeTypeDropdown(startExpanded = true)
}