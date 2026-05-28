package hu.bme.aut.android.myreceipts.ui.model

import hu.bme.aut.android.myreceipts.model.Recipe
import hu.bme.aut.android.myreceipts.model.RecipeTypes
import kotlinx.serialization.Serializable

@Serializable
data class RecipeUI(
    val id: Int = 0,
    val name: String = "",
    val type: RecipeTypes = RecipeTypes.Other,
    val urlThumbnail: String = "",
    val instructions: String = "",
    val ingredients: String = "",
    val timeToMake: String = "-",
    val youtubeLink: String = ""
)

fun RecipeUI.toShareString(): String {
    val string = "Recipe: $name\n" +
            "Type: $type\n" +
            "Time to make: $timeToMake\n" +
            "Ingredients:\n$ingredients\n" +
            "Instructions:\n$instructions\n"
    return string
}

fun Recipe.asRecipeUI(): RecipeUI {
    var string: String
    if(timeToMake == 0) {
        string = "-"
    } else {
        string = "$timeToMake minutes"
    }
    return RecipeUI(
        id = id,
        name = name,
        type = type,
        urlThumbnail = urlThumbnail,
        instructions = instructions,
        ingredients = ingredients,
        timeToMake = string,
        youtubeLink = youtubeLink
    )
}

fun Recipe.asEditingRecipeUI(): RecipeUI {
    var string: String
    if(timeToMake == 0) {
        string = ""
    } else {
        string = "$timeToMake"
    }
    return RecipeUI(
        id = id,
        name = name,
        type = type,
        urlThumbnail = urlThumbnail,
        instructions = instructions,
        ingredients = ingredients,
        timeToMake = string,
        youtubeLink = youtubeLink
    )
}

fun RecipeUI.asRecipe(): Recipe {
    var int: Int
    if(timeToMake == "-") {
        int = 0
    }
    else {
        int = timeToMake.split(" ")[0].toInt()
    }

    return Recipe(
        id = id,
        name = name,
        type = type,
        urlThumbnail = urlThumbnail,
        instructions = instructions,
        ingredients = ingredients,
        timeToMake = int,
        youtubeLink = youtubeLink
    )
}

fun RecipeUI.hasYT(): Boolean {
    return youtubeLink != ""
}

fun RecipeUI.hasThumbnail(): Boolean {
    return urlThumbnail != ""
}