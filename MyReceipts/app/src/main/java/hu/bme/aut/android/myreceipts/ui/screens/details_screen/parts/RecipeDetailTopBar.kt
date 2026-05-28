package hu.bme.aut.android.myreceipts.ui.screens.common

import android.R.attr.onClick
import android.R.attr.text
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.sp
import hu.bme.aut.android.myreceipts.R

@Composable
fun RecipeDetailTopBar(
    onBackClicked: () -> Unit,
    onShareClicked: () -> Unit,
    onEditClicked: () -> Unit,
    onDeleteClicked: () -> Unit,
    text: String = stringResource(R.string.back),
    startOpen: Boolean = false,
    modifier: Modifier = Modifier
) {
    var openMenu by remember{ mutableStateOf(startOpen) }

    Box(
        modifier = modifier.fillMaxWidth(),
        contentAlignment = Alignment.CenterStart
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = onBackClicked
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = null
                )
            }
            Text(text, fontSize = 20.sp, modifier = modifier.weight(1f))
            Box {
                IconButton(
                    onClick = {
                        openMenu = true;
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.MoreVert,
                        contentDescription = null
                    )
                }
                DropdownMenu(
                    expanded = openMenu,
                    onDismissRequest = { openMenu = false }
                ) {
                    DropdownMenuItem(
                        text = { Text(stringResource(R.string.share)) },
                        onClick = {
                            openMenu = false
                            onShareClicked()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text(stringResource(R.string.edit)) },
                        onClick = {
                            openMenu = false
                            onEditClicked()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text(stringResource(R.string.delete)) },
                        onClick = {
                            openMenu = false
                            onDeleteClicked()
                        }
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RecipeDetailTopBarPreview() {
    RecipeDetailTopBar({}, {}, {}, {}, "TopBar")
}

@Preview(showBackground = true)
@Composable
fun RecipeDetailTopBarOpenMenuPreview() {
    RecipeDetailTopBar({}, {}, {}, {}, "TopBar", true)
}