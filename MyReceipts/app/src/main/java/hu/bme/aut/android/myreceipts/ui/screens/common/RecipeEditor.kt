package hu.bme.aut.android.myreceipts.ui.screens.common

import android.R.attr.onClick
import android.R.attr.singleLine
import android.net.Uri
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import hu.bme.aut.android.myreceipts.R
import hu.bme.aut.android.myreceipts.model.RecipeTypes
import hu.bme.aut.android.myreceipts.ui.model.RecipeUI
import java.io.File

@Composable
fun RecipeEditor(
    recipeThumbnail: String,
    onRecipeThumbnailChanged: () -> Unit,
    recipeName: String,
    onRecipeNameChanged: (String) -> Unit,
    recipeType: String,
    onRecipeTypeChanged: (RecipeTypes) -> Unit,
    recipeIngredients: String,
    onRecipeIngredientsChanged: (String) -> Unit,
    recipeInstructions: String,
    onRecipeInstructionsChanged: (String) -> Unit,
    recipeTimeToMake: String,
    onRecipeTimeToMakeChanged: (String) -> Unit,
    recipeYoutubeLink: String,
    onRecipeYoutubeLinkChanged: (String) -> Unit,
    onRecipeSave: () -> Unit,
    modifier: Modifier = Modifier
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    Box(
        modifier = modifier.fillMaxSize()
    ) {
        Column(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier
                    .wrapContentSize()
                    .padding(top = 2.dp, bottom = 2.dp),
                text = stringResource(R.string.recipe_thumbnail),
            )
            if (recipeThumbnail != "") {
                AsyncImage(
                    model = recipeThumbnail,
                    contentDescription = null,
                    modifier = Modifier
                        .padding(top = 2.dp, bottom = 2.dp)
                        .clickable(
                            onClick = onRecipeThumbnailChanged
                        )
                )
            }
            Button(
                modifier = Modifier,
                onClick = onRecipeThumbnailChanged
            ) {
                Icon(
                    imageVector = Icons.Default.CameraAlt,
                    contentDescription = null,
                    modifier = Modifier.size(ButtonDefaults.IconSize)
                )
            }
            NormalTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                value = recipeName,
                label = stringResource(R.string.recipe_name),
                onValueChange = onRecipeNameChanged,
                onDone = { keyboardController?.hide() }
            )
            //Spacer(modifier.size(10.dp))
            RecipeTypeDropdown(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                actualRecipeType = recipeType,
                onRecipeTypeSelected = onRecipeTypeChanged
            )
            //Spacer(modifier.size(10.dp))
            NormalTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                value = recipeIngredients,
                label = stringResource(R.string.recipe_ingredients),
                onValueChange = onRecipeIngredientsChanged,
                onDone = { keyboardController?.hide() },
                singleLine = false
            )
            //Spacer(modifier.size(10.dp))
            NormalTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                value = recipeInstructions,
                label = stringResource(R.string.recipe_instructions),
                onValueChange = onRecipeInstructionsChanged,
                onDone = { keyboardController?.hide() },
                singleLine = false
            )
            //Spacer(modifier.size(10.dp))
            NormalTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                value = recipeTimeToMake,
                label = stringResource(R.string.recipe_timeToMake),
                onValueChange = onRecipeTimeToMakeChanged,
                onDone = { keyboardController?.hide() },
                singleLine = false
            )
            //Spacer(modifier.size(10.dp))
            NormalTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                value = recipeYoutubeLink,
                label = stringResource(R.string.recipe_youtubeLink),
                onValueChange = onRecipeYoutubeLinkChanged,
                onDone = { keyboardController?.hide() },
                singleLine = false
            )
            Button(
                modifier = Modifier,
                onClick = onRecipeSave
            ) {
                Text(text = stringResource(R.string.recipe_save))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RecipeEditorPreview() {
    RecipeEditor("", {},
        "Recipe", {},
        RecipeTypes.Breakfast.name, {},
        "Recipe Ingredients", {},
        "Recipe Instructions", {},
        "10", {},
        "", {},
        {}
    )
}