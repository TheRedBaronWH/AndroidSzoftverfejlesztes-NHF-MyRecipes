package hu.bme.aut.android.myreceipts.ui.screens.details_screen.parts

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import hu.bme.aut.android.myreceipts.R
import hu.bme.aut.android.myreceipts.model.RecipeTypes
import hu.bme.aut.android.myreceipts.ui.model.RecipeUI
import hu.bme.aut.android.myreceipts.ui.model.hasThumbnail
import hu.bme.aut.android.myreceipts.ui.model.hasYT
import hu.bme.aut.android.myreceipts.ui.screens.common.ExpandableHyperTextBox
import hu.bme.aut.android.myreceipts.ui.screens.common.ExpandableTextBox

@Composable
fun RecipeDetails(
    r: RecipeUI,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(top = 20.dp)
    ) {
        Column(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if(r.hasThumbnail()) {
                AsyncImage(
                    model = r.urlThumbnail,
                    contentDescription = r.name,
                    modifier = Modifier
                )
                Spacer(modifier.size(5.dp))
            }
            Text(
                modifier = Modifier
                    .wrapContentSize(),
                text = r.name,
                fontSize = 50.sp,
                lineHeight = 52.sp,
                textAlign = TextAlign.Center
            )
            Spacer(modifier.size(2.5.dp))
            Text(
                modifier = Modifier
                    .wrapContentHeight(),
                text = r.type.name,
                fontSize = 20.sp
            )
            Spacer(modifier.size(10.dp))
            ExpandableTextBox(
                modifier = Modifier,
                expandedShortText = stringResource(R.string.hide_ingredients),
                collapsedShortText = stringResource(R.string.show_ingredients),
                longText = r.ingredients,
                fontSize = 20.sp
            )
            Spacer(modifier.size(10.dp))
            ExpandableTextBox(
                modifier = Modifier,
                expandedShortText = stringResource(R.string.hide_instructions),
                collapsedShortText = stringResource(R.string.show_instructions),
                longText = r.instructions,
                fontSize = 20.sp,
                startExpanded = true
            )
            Spacer(modifier.size(10.dp))
            if(r.hasYT()) {
                ExpandableHyperTextBox(
                    modifier = Modifier,
                    expandedShortText = stringResource(R.string.hide_links),
                    collapsedShortText = stringResource(R.string.show_links),
                    longText = r.youtubeLink,
                    fontSize = 20.sp
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RecipeDetailsPreview() {
    val r = RecipeUI(
        id = 1,
        name = "Recipe",
        type = RecipeTypes.Breakfast,
        urlThumbnail = "https://www.themealdb.com/images/media/meals/72fgzj1764109947.jpg",
        ingredients = "Recipe Ingredients",
        instructions = "Recipe Instructions",
        timeToMake = "40 minutes"
    )
    RecipeDetails(r)
}
@Preview(showBackground = true)
@Composable
fun RecipeDetailsPreviewHasLink() {
    val r = RecipeUI(
        id = 1,
        name = "Roast fennel and aubergine paella",
        type = RecipeTypes.Breakfast,
        urlThumbnail = "https://www.themealdb.com/images/media/meals/72fgzj1764109947.jpg",
        ingredients = "Recipe Ingredients",
        instructions = "Recipe Instructions",
        timeToMake = "40 minutes",
        youtubeLink = "https://www.youtube.com/watch?v=dQw4w9WgXcQ"
    )
    RecipeDetails(r)
}
