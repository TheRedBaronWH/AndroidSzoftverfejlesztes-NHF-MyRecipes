package hu.bme.aut.android.myreceipts.ui.screens.list_screen.parts

import android.graphics.Paint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import hu.bme.aut.android.myreceipts.R
import hu.bme.aut.android.myreceipts.model.RecipeTypes
import hu.bme.aut.android.myreceipts.ui.model.RecipeUI
import hu.bme.aut.android.myreceipts.ui.model.hasThumbnail

@Composable
fun RecipeListElement (
    recipe: RecipeUI,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        if(recipe.hasThumbnail()) {
            AsyncImage(
                model = recipe.urlThumbnail,
                contentDescription = null,
                modifier = Modifier
                    .padding(1.dp)
                    .height(100.dp)
                    .align(Alignment.CenterVertically)
            )
        }
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(start = 5.dp)
        ) {
            Text(fontSize = 35.sp, lineHeight = 37.sp, text = recipe.name)
            Text(
                fontSize = 15.sp,
                text = stringResource(R.string.recipe_list_element_type) + " ${recipe.type.name}"
            )
            if (recipe.timeToMake != "-") Text(
                fontSize = 15.sp,
                text = stringResource(R.string.recipe_list_element_timeToMake) + " ${recipe.timeToMake}"
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RecipeListElementPreview() {
    val r = RecipeUI(
        id = 1,
        name = "Recipe",
        type = RecipeTypes.Breakfast,
        urlThumbnail = "https://www.themealdb.com/images/media/meals/qpxvuq1511798906.jpg",
        ingredients = "Recipe Ingredients",
        instructions = "Recipe Instructions",
        timeToMake = "40 minutes"
    )
    RecipeListElement(r)
}