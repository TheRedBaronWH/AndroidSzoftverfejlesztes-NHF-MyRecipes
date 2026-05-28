package hu.bme.aut.android.myreceipts.ui.screens.list_screen.parts

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import hu.bme.aut.android.myreceipts.model.RecipeTypes
import hu.bme.aut.android.myreceipts.ui.model.RecipeUI

@Composable
fun RecipeList(
    recipeList: List<RecipeUI>,
    onRecipeClicked: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.fillMaxSize()
    ) {
        items(
            items = recipeList,
            key = { recipe -> recipe.id }) { recipe ->
                RecipeListElement(
                    recipe = recipe,
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(start = 5.dp, bottom = 5.dp)
                        .clickable( onClick = {
                            onRecipeClicked(recipe.id)
                        }
                    ))
                if(recipe != recipeList.last()) {
                    HorizontalDivider(
                        thickness = 2.dp
                    )
                }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RecipeListPreview() {
    val r1 = RecipeUI(
        id = 1,
        name = "Recipe 1",
        type = RecipeTypes.Breakfast,
        ingredients = "Recipe Ingredients",
        instructions = "Recipe Instructions",
        timeToMake = "40 minutes"
    )
    val r2 = RecipeUI(
        id = 2,
        name = "Recipe 2",
        type = RecipeTypes.Beef,
        ingredients = "Recipe Ingredients",
        instructions = "Recipe Instructions",
        timeToMake = "60 minutes"
    )
    val r3 = RecipeUI(
        id = 3,
        name = "Recipe 3",
        type = RecipeTypes.Goat,
        ingredients = "Recipe Ingredients",
        instructions = "Recipe Instructions",
        timeToMake = "180 minutes"
    )
    RecipeList(listOf(r1, r2, r3), {})
}