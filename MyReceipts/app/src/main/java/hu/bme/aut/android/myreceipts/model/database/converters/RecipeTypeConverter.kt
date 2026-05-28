package hu.bme.aut.android.myreceipts.model.database.converters

import androidx.room.TypeConverter
import hu.bme.aut.android.myreceipts.model.RecipeTypes

object RecipeTypeConverter {

    @TypeConverter
    fun typeToString(type: RecipeTypes): String {
        return type.name
    }

    @TypeConverter
    fun typeFromString(string: String): RecipeTypes {
        return when(string) {
            RecipeTypes.Beef.name -> RecipeTypes.Beef
            RecipeTypes.Chicken.name -> RecipeTypes.Chicken
            RecipeTypes.Dessert.name -> RecipeTypes.Dessert
            RecipeTypes.Lamb.name -> RecipeTypes.Lamb
            RecipeTypes.Miscellaneous.name -> RecipeTypes.Miscellaneous
            RecipeTypes.Pasta.name -> RecipeTypes.Pasta
            RecipeTypes.Pork.name -> RecipeTypes.Pork
            RecipeTypes.Seafood.name -> RecipeTypes.Seafood
            RecipeTypes.Side.name -> RecipeTypes.Side
            RecipeTypes.Starter.name -> RecipeTypes.Starter
            RecipeTypes.Vegan.name -> RecipeTypes.Vegan
            RecipeTypes.Vegetarian.name -> RecipeTypes.Vegetarian
            RecipeTypes.Breakfast.name -> RecipeTypes.Breakfast
            RecipeTypes.Goat.name -> RecipeTypes.Goat
            else -> RecipeTypes.Other
        }
    }
}