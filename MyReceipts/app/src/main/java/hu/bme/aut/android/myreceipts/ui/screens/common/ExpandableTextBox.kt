package hu.bme.aut.android.myreceipts.ui.screens.common

import android.R.attr.text
import android.R.attr.textSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.nio.file.Files.size

@Composable
fun ExpandableTextBox(
    expandedShortText: String,
    collapsedShortText: String,
    longText: String,
    fontSize: TextUnit = 15.sp,
    startExpanded: Boolean = false,
    modifier: Modifier = Modifier
) {
    val expanded = remember { mutableStateOf(startExpanded) }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .clickable(onClick = {
                expanded.value = !expanded.value
            }),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = modifier
                    .height(30.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (expanded.value) {
                    Icon(
                        modifier = modifier.padding(start = 5.dp, end = 5.dp),
                        imageVector = Icons.Default.KeyboardArrowUp, contentDescription = null
                    )
                    Text(
                        modifier = modifier.padding(end = 5.dp),
                        text = expandedShortText, fontSize = fontSize
                    )
                } else {
                    Icon(
                        modifier = modifier.padding(start = 5.dp, end = 5.dp),
                        imageVector = Icons.Default.KeyboardArrowDown, contentDescription = null
                    )
                    Text(
                        modifier = modifier.padding(end = 5.dp),
                        text = collapsedShortText, fontSize = fontSize
                    )
                }
            }
            if(expanded.value) {
                HorizontalDivider(thickness = 2.dp)
                Text(
                    modifier = modifier.padding(5.dp),
                    text = longText, fontSize = fontSize
                )
                HorizontalDivider(thickness = 2.dp)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ExpandableTextBoxPreview1() {
    ExpandableTextBox(
        expandedShortText = "Hide Lorem ipsum",
        collapsedShortText = "Show Lorem ipsum",
        longText = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
        startExpanded = false
    )
}

@Preview(showBackground = true)
@Composable
fun ExpandableTextBoxPreview2() {
    ExpandableTextBox(
        expandedShortText = "Hide Lorem ipsum",
        collapsedShortText = "Show Lorem ipsum",
        longText = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
        startExpanded = true
    )
}
