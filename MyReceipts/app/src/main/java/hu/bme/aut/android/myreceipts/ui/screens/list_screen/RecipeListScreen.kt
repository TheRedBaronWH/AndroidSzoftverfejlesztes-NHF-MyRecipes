package hu.bme.aut.android.myreceipts.ui.screens.list_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import hu.bme.aut.android.myreceipts.R
import hu.bme.aut.android.myreceipts.ui.screens.common.RecipeTypeDropdown
import hu.bme.aut.android.myreceipts.ui.screens.common.SimpleTopBar
import hu.bme.aut.android.myreceipts.ui.screens.list_screen.parts.RecipeList
import kotlinx.coroutines.launch

@Composable
fun RecipeListScreen(
    onBackClicked: () -> Unit,
    onNewRecipeClicked: () -> Unit,
    onRecipeClicked: (Int) -> Unit,
    viewModel: RecipeListViewModel = hiltViewModel()
) {
    val state = viewModel.state.collectAsStateWithLifecycle().value

    val hostState = remember { SnackbarHostState() }

    LaunchedEffect(key1 = true) {
        viewModel.onEvent(RecipeListScreenEvent.LoadRecipes)
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        snackbarHost = { SnackbarHost(hostState) },
        floatingActionButton = {
            LargeFloatingActionButton(
                onClick = onNewRecipeClicked
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = null)
            }
        },
        topBar = {
            SimpleTopBar(
                onBackClicked = onBackClicked,
                text = stringResource(R.string.recipes)
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentAlignment = Alignment.Center
        ) {
            when (state) {
                is RecipeListState.Loading -> CircularProgressIndicator()

                is RecipeListState.Error -> Text(
                    text = state.error.message ?: stringResource(R.string.unknown_error)
                )

                is RecipeListState.Success -> {
                    if (state.recipeList.isEmpty()) {
                        Text(text = stringResource(R.string.no_recipes))
                    } else {
                        RecipeList(
                            recipeList = state.recipeList,
                            onRecipeClicked = onRecipeClicked
                        )
                    }
                }

            }
        }
    }
}

@Preview
@Composable
fun RecipeListScreenPreview() {
    RecipeListScreen({}, {}, {})
}