package hu.bme.aut.android.myreceipts.model.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import hu.bme.aut.android.myreceipts.model.Recipe
import hu.bme.aut.android.myreceipts.model.RecipeTypes

@Entity(tableName = "recipes",
    indices = [
        Index(value = ["name"], unique = true),
        Index(value = ["type"])
    ]
)
data class RecipeEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name="name") val name: String,
    @ColumnInfo(name="type") val type: RecipeTypes,
    val urlThumbnail: String,
    val instructions: String,
    val ingredients: String,
    val timeToMake: Int,
    val youtubeLink: String
)

fun RecipeEntity.asRecipe(): Recipe = Recipe(
    id = id,
    name = name,
    type = type,
    urlThumbnail = urlThumbnail,
    instructions = instructions,
    ingredients = ingredients,
    timeToMake = timeToMake,
    youtubeLink = youtubeLink
)

fun Recipe.asRecipeEntity(): RecipeEntity = RecipeEntity(
    id = id,
    name = name,
    type = type,
    urlThumbnail = urlThumbnail,
    instructions = instructions,
    ingredients = ingredients,
    timeToMake = timeToMake,
    youtubeLink = youtubeLink
)