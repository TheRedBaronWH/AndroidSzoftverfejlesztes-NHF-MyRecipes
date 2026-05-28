package hu.bme.aut.android.myreceipts.api.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import hu.bme.aut.android.myreceipts.model.RecipeTypes
import hu.bme.aut.android.myreceipts.ui.model.RecipeUI

@JsonClass(generateAdapter = true)
data class Meals (
  @Json(name = "idMeal"                      ) var idMeal                      : String? = null,
  @Json(name = "strMeal"                     ) var strMeal                     : String? = null,
  @Json(name = "strMealAlternate"            ) var strMealAlternate            : String? = null,
  @Json(name = "strCategory"                 ) var strCategory                 : String? = null,
  @Json(name = "strArea"                     ) var strArea                     : String? = null,
  @Json(name = "strInstructions"             ) var strInstructions             : String? = null,
  @Json(name = "strMealThumb"                ) var strMealThumb                : String? = null,
  @Json(name = "strTags"                     ) var strTags                     : String? = null,
  @Json(name = "strYoutube"                  ) var strYoutube                  : String? = null,
  @Json(name = "strIngredient1"              ) var strIngredient1              : String? = null,
  @Json(name = "strIngredient2"              ) var strIngredient2              : String? = null,
  @Json(name = "strIngredient3"              ) var strIngredient3              : String? = null,
  @Json(name = "strIngredient4"              ) var strIngredient4              : String? = null,
  @Json(name = "strIngredient5"              ) var strIngredient5              : String? = null,
  @Json(name = "strIngredient6"              ) var strIngredient6              : String? = null,
  @Json(name = "strIngredient7"              ) var strIngredient7              : String? = null,
  @Json(name = "strIngredient8"              ) var strIngredient8              : String? = null,
  @Json(name = "strIngredient9"              ) var strIngredient9              : String? = null,
  @Json(name = "strIngredient10"             ) var strIngredient10             : String? = null,
  @Json(name = "strIngredient11"             ) var strIngredient11             : String? = null,
  @Json(name = "strIngredient12"             ) var strIngredient12             : String? = null,
  @Json(name = "strIngredient13"             ) var strIngredient13             : String? = null,
  @Json(name = "strIngredient14"             ) var strIngredient14             : String? = null,
  @Json(name = "strIngredient15"             ) var strIngredient15             : String? = null,
  @Json(name = "strIngredient16"             ) var strIngredient16             : String? = null,
  @Json(name = "strIngredient17"             ) var strIngredient17             : String? = null,
  @Json(name = "strIngredient18"             ) var strIngredient18             : String? = null,
  @Json(name = "strIngredient19"             ) var strIngredient19             : String? = null,
  @Json(name = "strIngredient20"             ) var strIngredient20             : String? = null,
  @Json(name = "strMeasure1"                 ) var strMeasure1                 : String? = null,
  @Json(name = "strMeasure2"                 ) var strMeasure2                 : String? = null,
  @Json(name = "strMeasure3"                 ) var strMeasure3                 : String? = null,
  @Json(name = "strMeasure4"                 ) var strMeasure4                 : String? = null,
  @Json(name = "strMeasure5"                 ) var strMeasure5                 : String? = null,
  @Json(name = "strMeasure6"                 ) var strMeasure6                 : String? = null,
  @Json(name = "strMeasure7"                 ) var strMeasure7                 : String? = null,
  @Json(name = "strMeasure8"                 ) var strMeasure8                 : String? = null,
  @Json(name = "strMeasure9"                 ) var strMeasure9                 : String? = null,
  @Json(name = "strMeasure10"                ) var strMeasure10                : String? = null,
  @Json(name = "strMeasure11"                ) var strMeasure11                : String? = null,
  @Json(name = "strMeasure12"                ) var strMeasure12                : String? = null,
  @Json(name = "strMeasure13"                ) var strMeasure13                : String? = null,
  @Json(name = "strMeasure14"                ) var strMeasure14                : String? = null,
  @Json(name = "strMeasure15"                ) var strMeasure15                : String? = null,
  @Json(name = "strMeasure16"                ) var strMeasure16                : String? = null,
  @Json(name = "strMeasure17"                ) var strMeasure17                : String? = null,
  @Json(name = "strMeasure18"                ) var strMeasure18                : String? = null,
  @Json(name = "strMeasure19"                ) var strMeasure19                : String? = null,
  @Json(name = "strMeasure20"                ) var strMeasure20                : String? = null,
  @Json(name = "strSource"                   ) var strSource                   : String? = null,
  @Json(name = "strImageSource"              ) var strImageSource              : String? = null,
  @Json(name = "strCreativeCommonsConfirmed" ) var strCreativeCommonsConfirmed : String? = null,
  @Json(name = "dateModified"                ) var dateModified                : String? = null
)

fun Meals.asRecipeUI(): RecipeUI {
  return RecipeUI(
    id = (Math.random() * Int.MAX_VALUE).toInt(),
    name = strMeal!!,
    type = RecipeTypes.valueOf(strCategory!!),
    //thumbnail = null,
    urlThumbnail = strMealThumb!!,
    instructions = strInstructions!!,
    ingredients = this.getIngredients(),
    timeToMake = "0 minutes",
    youtubeLink = strYoutube!!
  )
}

fun Meals.getIngredientsList(): List<String?> = listOf(strIngredient1, strIngredient2, strIngredient3, strIngredient4, strIngredient5, strIngredient6, strIngredient7, strIngredient8, strIngredient9, strIngredient10, strIngredient11, strIngredient12, strIngredient13, strIngredient14, strIngredient15, strIngredient16, strIngredient17, strIngredient18, strIngredient19, strIngredient20)
fun Meals.getMeasuresList(): List<String?> = listOf(strMeasure1, strMeasure2, strMeasure3, strMeasure4, strMeasure5, strMeasure6, strMeasure7, strMeasure8, strMeasure9, strMeasure10, strMeasure11, strMeasure12, strMeasure13, strMeasure14, strMeasure15, strMeasure16, strMeasure17, strMeasure18, strMeasure19, strMeasure20)

fun Meals.getIngredients(): String {
  var i = 0
  var string = ""
  val ingredients = this.getIngredientsList()
  val measures = this.getMeasuresList()
  while(i<20 && ingredients[i]!=null && ingredients[i]!="") {
    string += measures[i] + " of " + ingredients[i] + "\n"
    i++
  }
  return string.trim()
}