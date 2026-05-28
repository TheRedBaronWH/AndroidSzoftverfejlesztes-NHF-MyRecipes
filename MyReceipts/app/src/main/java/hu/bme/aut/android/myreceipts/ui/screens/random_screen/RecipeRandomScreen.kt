package hu.bme.aut.android.myreceipts.ui.screens.random_screen

import android.R.attr.contentDescription
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Save
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import hu.bme.aut.android.myreceipts.R
import hu.bme.aut.android.myreceipts.ui.model.RecipeUI
import hu.bme.aut.android.myreceipts.ui.screens.common.SimpleTopBar
import hu.bme.aut.android.myreceipts.ui.screens.details_screen.RecipeDetailScreenEvent
import hu.bme.aut.android.myreceipts.ui.screens.details_screen.RecipeDetailState
import hu.bme.aut.android.myreceipts.ui.screens.details_screen.RecipeDetailViewModel
import hu.bme.aut.android.myreceipts.ui.screens.details_screen.parts.RecipeDetailDeleteDialog
import hu.bme.aut.android.myreceipts.ui.screens.details_screen.parts.RecipeDetails
import hu.bme.aut.android.myreceipts.ui.screens.random_screen.parts.RecipeRandomSaveDialog

@Composable
fun RecipeRandomScreen(
    onBackClicked: () -> Unit,
    viewModel: RecipeRandomViewModel = hiltViewModel(),
    recipe: RecipeUI? = null
) {
    val state = viewModel.state.collectAsStateWithLifecycle().value

    var saveDialogueOpened by remember{ mutableStateOf(false) }

    val scrollState = rememberScrollState()

    LaunchedEffect(key1 = null) {
        if(recipe == null) {
            viewModel.loadRandomRecipe()
        }
        else {
            viewModel.setState(recipe)
        }
    }

    if(saveDialogueOpened) {
        RecipeRandomSaveDialog(
            onDismiss = {
                saveDialogueOpened = false
            },
            onConfirm = {
                saveDialogueOpened = false
                viewModel.saveRecipe()
                onBackClicked()
            }
        )
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            SimpleTopBar(
                onBackClicked = onBackClicked
            )
        },
        floatingActionButton = {
            Column() {
                LargeFloatingActionButton(
                    onClick = {
                        saveDialogueOpened = true
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Save,
                        contentDescription = stringResource(R.string.save)
                    )
                }
                Spacer(Modifier.size(10.dp))
                LargeFloatingActionButton(
                    onClick = {
                        viewModel.loadRandomRecipe()
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Refresh,
                        contentDescription = stringResource(R.string.refresh)
                    )
                }
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(scrollState),
            contentAlignment = Alignment.Center
        ) {
            when(state) {
                is RecipeRandomState.Loading -> {
                    CircularProgressIndicator()
                }

                is RecipeRandomState.Error -> {
                    Text(
                        text = state.error.message ?: stringResource(R.string.unknown_error)
                    )
                }

                is RecipeRandomState.Success -> {
                    RecipeDetails(
                        r = state.recipe
                    )
                }
            }
        }
    }
}