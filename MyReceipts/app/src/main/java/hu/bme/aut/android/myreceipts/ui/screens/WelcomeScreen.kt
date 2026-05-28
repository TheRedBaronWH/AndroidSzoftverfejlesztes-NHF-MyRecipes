package hu.bme.aut.android.myreceipts.ui.screens

import android.R.attr.onClick
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import hu.bme.aut.android.myreceipts.R

@Composable
fun WelcomeScreen(
    onReceiptsClicked: () -> Unit = {},
    onRandomClicked: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Column(
            Modifier.weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom) {
            Text(fontSize = 50.sp, text = stringResource(R.string.app_name))
        }
        Column(
            Modifier
                .weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ){
            Button(modifier = Modifier.width(300.dp).height(65.dp), onClick = onReceiptsClicked) {
                Text(fontSize = 20.sp, text = stringResource(R.string.view_recipes))
            }
            Spacer(Modifier.height(50.dp))
            Button(modifier = Modifier.width(300.dp).height(65.dp), onClick = onRandomClicked) {
                Text(fontSize = 20.sp, text = stringResource(R.string.random_recipe))
            }
        }
        Column(Modifier.weight(1f)) {
            Box(Modifier.weight(9f))
            Box(Modifier.weight(1f), contentAlignment = Alignment.Center) {
                Text(fontSize = 12.sp, text = stringResource(R.string.author))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun WelcomeScreenPreview() {
    WelcomeScreen(onReceiptsClicked = {}, onRandomClicked = {})
}