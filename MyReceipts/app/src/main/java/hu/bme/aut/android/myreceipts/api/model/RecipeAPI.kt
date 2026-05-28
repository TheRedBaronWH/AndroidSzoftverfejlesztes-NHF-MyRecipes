package hu.bme.aut.android.myreceipts.api.model

import android.R.attr.name
import hu.bme.aut.android.myreceipts.model.Recipe
import hu.bme.aut.android.myreceipts.model.RecipeTypes
import hu.bme.aut.android.myreceipts.ui.model.RecipeUI

data class RecipeAPI(
    val strMeal: String,
    val strCategory: String,
    val strInstructions: String,
    val ingredients: List<String?>,
    val measurements: List<String?>,
    val strYoutube: String
)

fun RecipeAPI.asRecipeUI(): RecipeUI {
    return RecipeUI(
        id = (Math.random() * Int.MAX_VALUE).toInt(),
        name = strMeal,
        type = RecipeTypes.valueOf(strCategory),
        instructions = strInstructions,
        ingredients = this.getIngredients(),
        timeToMake = "0 minutes",
        youtubeLink = strYoutube
    )
}

fun RecipeAPI.getIngredients(): String {
    var string = ""
    var i = 0
    while (ingredients[i]!=null) {
        string += measurements[i] + " of " + ingredients[i] + "\n"
        i++
    }
    return string
}