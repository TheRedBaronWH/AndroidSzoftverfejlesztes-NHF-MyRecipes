package hu.bme.aut.android.myreceipts.ui.screens.details_screen

import android.content.Intent
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import hu.bme.aut.android.myreceipts.R
import hu.bme.aut.android.myreceipts.ui.model.toShareString
import hu.bme.aut.android.myreceipts.ui.screens.common.RecipeDetailTopBar
import hu.bme.aut.android.myreceipts.ui.screens.common.SimpleTopBar
import hu.bme.aut.android.myreceipts.ui.screens.details_screen.parts.RecipeDetailBottomBar
import hu.bme.aut.android.myreceipts.ui.screens.details_screen.parts.RecipeDetailDeleteDialog
import hu.bme.aut.android.myreceipts.ui.screens.details_screen.parts.RecipeDetails
import kotlinx.coroutines.launch

@Composable
fun RecipeDetailScreen(
    recipeId: Int,
    onBackClicked: () -> Unit,
    onEditClicked: () -> Unit,
    viewModel: RecipeDetailViewModel = hiltViewModel()
) {
    val state = viewModel.state.collectAsStateWithLifecycle().value

    var deleteDialogueOpened by remember{ mutableStateOf(false) }
    var recipeToDelete by remember{ mutableIntStateOf(0) }

    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val hostState = remember { SnackbarHostState() }
    val scrollState = rememberScrollState()

    LaunchedEffect(key1 = recipeId) {
        viewModel.onEvent(RecipeDetailScreenEvent.LoadRecipe(recipeId))
    }

    if(deleteDialogueOpened) {
        RecipeDetailDeleteDialog(
            onDismiss = {
                deleteDialogueOpened = false
            },
            onConfirm = {
                deleteDialogueOpened = false
                viewModel.onEvent(RecipeDetailScreenEvent.DeleteRecipe(recipeToDelete))
                onBackClicked()
            }
        )
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        snackbarHost = { SnackbarHost(hostState) },
        topBar = {
//            SimpleTopBar(
//                onBackClicked = onBackClicked
//            )
            RecipeDetailTopBar(
                onBackClicked = onBackClicked,
                onShareClicked = {
                    if(state is RecipeDetailState.Success) {
                        val sendIntent: Intent = Intent().apply {
                            action = Intent.ACTION_SEND
                            putExtra(Intent.EXTRA_TEXT, state.recipe.toShareString())
                            type = "text/plain"
                        }

                        val shareIntent = Intent.createChooser(sendIntent, null)
                        context.startActivity(shareIntent)
                    }
                    else {
                        scope.launch {
                            hostState.showSnackbar(
                                message = "Cannot delete: Recipe not loaded"
                            )
                        }
                    }
                },
                onEditClicked = onEditClicked,
                onDeleteClicked = {
                    if(state is RecipeDetailState.Success) {
                        recipeToDelete = state.recipe.id
                        deleteDialogueOpened = true
                    }
                    else {
                        scope.launch {
                            hostState.showSnackbar(
                                message = "Cannot delete: Recipe not loaded"
                            )
                        }
                    }
                }
            )
        },
//        bottomBar = {
//            RecipeDetailBottomBar(
//                onShareClicked = {
//                    if(state is RecipeDetailState.Success) {
//                        val sendIntent: Intent = Intent().apply {
//                            action = Intent.ACTION_SEND
//                            putExtra(Intent.EXTRA_TEXT, state.recipe.toShareString())
//                            type = "text/plain"
//                        }
//
//                        val shareIntent = Intent.createChooser(sendIntent, null)
//                        context.startActivity(shareIntent)
//                    }
//                    else {
//                        scope.launch {
//                            hostState.showSnackbar(
//                                message = "Cannot delete: Recipe not loaded"
//                            )
//                        }
//                    }
//                },
//                onEditClicked = onEditClicked,
//                onDeleteClicked = {
//                    if(state is RecipeDetailState.Success) {
//                        recipeToDelete = state.recipe.id
//                        deleteDialogueOpened = true
//                    }
//                    else {
//                        scope.launch {
//                            hostState.showSnackbar(
//                                message = "Cannot delete: Recipe not loaded"
//                            )
//                        }
//                    }
//                }
//            )
//        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(scrollState),
            contentAlignment = Alignment.Center
        ) {
            when (state) {
                is RecipeDetailState.Loading -> {
                    CircularProgressIndicator()
                }

                is RecipeDetailState.Error -> {
                    Text(
                        text = state.error.message ?: stringResource(R.string.unknown_error)
                    )
                }

                is RecipeDetailState.Success -> {
                    RecipeDetails(state.recipe)
                }
            }
        }
    }
}