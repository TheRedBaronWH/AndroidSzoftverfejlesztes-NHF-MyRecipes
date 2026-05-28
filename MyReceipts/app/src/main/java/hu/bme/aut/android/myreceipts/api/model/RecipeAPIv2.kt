package hu.bme.aut.android.myreceipts.api.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import hu.bme.aut.android.myreceipts.ui.model.RecipeUI

@JsonClass(generateAdapter = true)
data class RecipeAPIv2 (
  @Json(name = "meals") var meals : List<Meals> = listOf()
)

fun RecipeAPIv2.getFirstAsRecipeUI(): RecipeUI {
  return meals[0].asRecipeUI()
}