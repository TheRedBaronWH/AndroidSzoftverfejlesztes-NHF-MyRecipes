package hu.bme.aut.android.myreceipts.model

class Recipe (
    val id: Int,
    val name: String,
    val type: RecipeTypes,
    val urlThumbnail: String,
    val instructions: String,
    val ingredients: String,
    val timeToMake: Int = 0,
    val youtubeLink: String = ""
)