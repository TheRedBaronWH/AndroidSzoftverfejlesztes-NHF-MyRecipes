package hu.bme.aut.android.myreceipts.ui.screens.create_screen

import android.content.Context
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.FileProvider
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import hu.bme.aut.android.myreceipts.ui.screens.common.RecipeEditor
import hu.bme.aut.android.myreceipts.ui.screens.common.SimpleTopBar
import hu.bme.aut.android.myreceipts.ui.screens.edit_screens.create_screen.RecipeCreateViewModel
import hu.bme.aut.android.myreceipts.ui.screens.edit_screens.RecipeEditScreenEvent
import kotlinx.coroutines.launch
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.Objects

@Composable
fun RecipeCreateScreen(
    onBackClicked: ()->Unit,
    viewModel: RecipeCreateViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    val scope = rememberCoroutineScope()
    val hostState = remember{ SnackbarHostState() }
    val scrollState = rememberScrollState()

    val context = LocalContext.current
    val file = context.createImageFile()
    val uri = FileProvider.getUriForFile(
        Objects.requireNonNull(context),
        context.packageName + ".provider", file
    )
    val cameraLauncher = rememberLauncherForActivityResult(ActivityResultContracts.TakePicture()) { success ->
        if (success) {
            viewModel.onEvent(RecipeEditScreenEvent.TakeAndChangeThumbnail(file.absolutePath))
        }
    }

    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { uiEvent ->
            when (uiEvent) {
                is RecipeEditScreenUIEvent.Success -> {
                    onBackClicked()
                }

                is RecipeEditScreenUIEvent.Failure -> {
                    scope.launch {
                        hostState.showSnackbar(
                            message = uiEvent.error
                        )
                    }
                }
            }
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        snackbarHost = { SnackbarHost(hostState) },
        topBar = {
            SimpleTopBar(
                onBackClicked = onBackClicked
            )
        }
    ) { innerPadding ->
        Box(
            Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(scrollState),
            contentAlignment = Alignment.Center
        ) {
            RecipeEditor(
                recipeThumbnail = state.recipe.urlThumbnail,
                onRecipeThumbnailChanged = {
                    cameraLauncher.launch(uri)
                },
                recipeName = state.recipe.name,
                onRecipeNameChanged = {
                    viewModel.onEvent(RecipeEditScreenEvent.ChangeName(it))
                },
                recipeType = state.recipe.type.name,
                onRecipeTypeChanged = {
                    viewModel.onEvent(RecipeEditScreenEvent.ChangeType(it))
                },
                recipeIngredients = state.recipe.ingredients,
                onRecipeIngredientsChanged = {
                    viewModel.onEvent(RecipeEditScreenEvent.ChangeIngredients(it))
                },
                recipeInstructions = state.recipe.instructions,
                onRecipeInstructionsChanged = {
                    viewModel.onEvent(RecipeEditScreenEvent.ChangeInstructions(it))
                },
                recipeTimeToMake = state.recipe.timeToMake,
                onRecipeTimeToMakeChanged = {
                    viewModel.onEvent(RecipeEditScreenEvent.ChangeTimeToMake(it))
                },
                recipeYoutubeLink = state.recipe.youtubeLink,
                onRecipeYoutubeLinkChanged = {
                    viewModel.onEvent(RecipeEditScreenEvent.ChangeYoutubeLink(it))
                },
                onRecipeSave ={
                    viewModel.onEvent(RecipeEditScreenEvent.SaveRecipe)
                },
                modifier = Modifier
                    .fillMaxSize()
            )
        }
    }
}

private fun Context.createImageFile(): File {
    // Create an image file name
    val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
    val imageFileName = "JPEG_" + timeStamp + "_"
    val image = File.createTempFile(
        imageFileName, /* prefix */
        ".jpg", /* suffix */
        externalCacheDir /* directory */
    )
    return image
}